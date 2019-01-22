using PayrollManagement.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PayrollManagement
{
    public partial class Calculate_Pay_Page : System.Web.UI.Page
    {
        protected override void OnPreInit(EventArgs e)
        {
            //Checks which user is entering the system and chooses the master pages for them
            int postion = MiscClass.position;
            if (postion == 3)
            {
                MasterPageFile = "~/MasterPageAdmin.Master";
            }
            else if (postion == 2)
            {
                MasterPageFile = "~/MasterPage.Master";
            }
            else
            {
                MasterPageFile = "~/MasterPageEmp.Master";
            }
        }

        public List<PaystubInfo> listOfInfo = new List<PaystubInfo>();
        private double _grossPay;
        private double _totalHours;
        DateTime startTime;
        DateTime endTime;
        private double _payrate;
        private double _shiftCount;
        private double _regularHours;
        private double _overtimeHours;


        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void AcceptButton_Click(object sender, EventArgs e)
        {
            try
            {

                startTime = Begin_Calendar.SelectedDate;
                endTime = End_Calendar.SelectedDate;

                string employeeID = Employee_ID_Textbox.Text;
                string averageHoursWorked = AverageHoursTextBox.Text;
                string averageShiftsAWeek = AverageShiftsTextBox.Text;
                Employee employee = ConnectionClass.GetEmployeeInfo(Convert.ToInt32(employeeID));

                int id = 0;
                int hoursWorked = 0;
                int averageShiftNumber = 0;

                if (int.TryParse(employeeID, out id) == true &&
                        int.TryParse(averageHoursWorked, out hoursWorked) == true &&
                        int.TryParse(averageShiftsAWeek, out averageShiftNumber) == true &&
                        startTime != DateTime.MinValue
                       && endTime != DateTime.MinValue && employee != null && employee.Active == 1)
                {
                    int.TryParse(employeeID, out id);
                    int.TryParse(averageHoursWorked, out hoursWorked);
                    int.TryParse(averageShiftsAWeek, out averageShiftNumber);


                    double employeePayRate = ConnectionClass.FindCurrentPayRate(id);

                    //Needed to calculate tax info for custom timeframe
                    double yearlyGrossIncome = (hoursWorked * averageShiftNumber * employeePayRate) * 52;


                    listOfInfo = ConnectionClass.FindShifts(id);

                    List<PaystubInfo> refinedList = createListOfValidPaystubInfo(listOfInfo);
                    CalculateGrossPayForExtendedPeriod(refinedList);

                    //Calculating appropriate tax for chosen time frame

                    double percentageOfYear = _shiftCount / 365;

                    Application["Province"] = "Ontario";
                    string prov = Application["Province"].ToString();

                    //yearly tax
                    double baseIncomeTax = TaxManagement.CalculateIncomeTax(yearlyGrossIncome, prov, false);
                    double baseCpp = TaxManagement.CalculateCPP(yearlyGrossIncome, false);
                    double baseEI = TaxManagement.CalculateEmploymentInsurance(yearlyGrossIncome, false);

                    //Altering for timeframe chosen
                    double incomeTax = baseIncomeTax * percentageOfYear;
                    double Cpp = baseCpp * percentageOfYear;
                    double eI = baseEI * percentageOfYear;

                    double allDeductions = incomeTax + eI + Cpp;

                    double netIncome = _grossPay - allDeductions;

                    //Populating Textboxes: 
                    GrossPayTextBox.Text = string.Format("{0:C}", _grossPay);
                    TotalHourWorkedTextbox.Text = string.Format("{0}", _totalHours);
                    PayRateTextBox.Text = string.Format("{0:C}", employeePayRate);
                    TotalShiftsTextbox.Text = string.Format("{0}", _shiftCount);
                    PayPeriodTextbox.Text = startTime.ToString() + "---" + endTime.ToString();
                    EITextbox.Text = string.Format("{0:C}", eI);
                    CPPTextbox.Text = string.Format("{0:C}", Cpp);
                    IncomeTaxTextbox.Text = string.Format("{0:C}", incomeTax);
                    NetPayTextbox.Text = string.Format("{0:C}", netIncome);

                    Session["Gross"] = _grossPay;
                    Session["totalHours"] = _totalHours;
                    Session["overtimeHours"] = _overtimeHours;
                    Session["regularHours"] = _regularHours;
                    Session["payRate"] = employeePayRate;
                    Session["shiftCount"] = _shiftCount;
                    Session["timeFrame"] = startTime.ToString() + "---" + endTime.ToString();
                    Session["ei"] = eI;
                    Session["cpp"] = Cpp;
                    Session["incomeTax"] = incomeTax;
                    Session["netPay"] = netIncome;
                    Session["overallDeductions"] = allDeductions;
                    Session["employee"] = employee;

                    ResultsPanel.Visible = true;

                    errorLabel.Text = "";
                }

                else { errorLabel.Text = "Please enter a valid time and Employee ID"; }
            }

            catch (Exception ex)
            {

                errorLabel.Text = ex.Message;
            }

        }

        //Method Calculates hours and gross pay for a single employee over an extended period of time.
        public void CalculateGrossPayForExtendedPeriod(List<PaystubInfo> validEntries)
        {
            double calcGrossPay = 0;
            double calcOvertimeHours = 0;
            double calcRegularHours = 0;
            double calcAllHours = 0;

            foreach (PaystubInfo item in validEntries)
            {
                calcGrossPay = calcGrossPay + item.amountPaid;
                calcOvertimeHours = calcOvertimeHours + item.overtimeHours;
                calcRegularHours = calcRegularHours + item.amountOfHours;
            }


            calcAllHours = calcOvertimeHours + calcRegularHours;

            _regularHours = calcRegularHours;
            _overtimeHours = calcOvertimeHours;
            _grossPay = calcGrossPay;
            _totalHours = calcAllHours;


        }

        //Method that gets and returns valid paystubs for the specified time period
        public List<PaystubInfo> createListOfValidPaystubInfo(List<PaystubInfo> allEntries)
        {
            List<PaystubInfo> validEntries = new List<PaystubInfo>();

            int numOfShifts = 0;

            foreach (PaystubInfo item in allEntries)
            {

                if (item.endTime <= endTime && item.beginningTime >= startTime)
                {
                    numOfShifts++;
                    validEntries.Add(item);
                }
            }

            _shiftCount = numOfShifts;

            return validEntries;
        }

        protected void AllEmployeesPayButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("Overall Employee Pay.aspx");
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("Custom Paystub Page.aspx");
        }
    }
}