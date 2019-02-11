using PayrollManagement.Model;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Paystub_Page : System.Web.UI.Page
{
    protected override void OnPreInit(EventArgs e)
    {
        //Checks which user is entering the system and chooses the master pages for them
        int postion = PayrollManagement.Model.MiscClass.position;
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


    List<PaystubInfo> Shifts = new List<PaystubInfo>();



    private double _grossPay;
    private double _netPay;
    private double _totalHours;
    private double _overtimeHours;
    private double _regularHours;
    private DateTime _startTime;
    private DateTime _endTime;
    private int _employeeID;
    private double CPP;
    private double EI;
    private double IncomeTax;

    public List<PaystubInfo> listOfInfo = new List<PaystubInfo>();

    protected void Page_Load(object sender, EventArgs e)
    {

        Application["Province"] = "Ontario"; //temporary setting of Province Variable

        _employeeID = (int)Session["EmployeeID"];
        _startTime = (DateTime)Session["StartDate"];
        _endTime = (DateTime)Session["EndDate"];


        double employeePayRate = ConnectionClass.FindCurrentPayRate(_employeeID);


        listOfInfo = ConnectionClass.FindShifts(_employeeID);
        Employee employee = ConnectionClass.GetEmployeeInfo(_employeeID);

        List<PaystubInfo> refinedList = createListOfValidPaystubInfo(listOfInfo);
        CalculateFromPaystubInfo(refinedList);


        string prov = Application["Province"].ToString();

        CPP = TaxManagement.CalculateCPP(_grossPay, true);
        IncomeTax = TaxManagement.CalculateIncomeTax(_grossPay, prov, true);
        EI = TaxManagement.CalculateEmploymentInsurance(_grossPay, true);



        _netPay = _grossPay - (EI + IncomeTax + CPP);

        FullHoursTextBox.Text = _totalHours.ToString();
        GrossPayTextBox.Text = String.Format("{0:c}", _grossPay);
        RegularHoursTextBox.Text = _regularHours.ToString();
        OvertimeHoursTextBox.Text = _overtimeHours.ToString();
        NetPayTextBox.Text = String.Format("{0:c}", _netPay);
        PayRateTextBox.Text = String.Format("{0:c}", employee.PayRate);
        NameLabel.Text = employee.FirstName + " " + employee.LastName;
        SSnLabel.Text = "***-***-" + employee.SocialNumber.ToString().Substring(6, 3);
        IDLabel.Text = _employeeID.ToString();
        PayPeriodLabel.Text = _startTime.ToString() + "---" + _endTime.ToString();
        IncomeTaxTextBox.Text = String.Format("{0:c}", IncomeTax);
        CPPTextBox.Text = String.Format("{0:c}", CPP);
        EITextBox.Text = String.Format("{0:c}", EI);
        OverallDeductionsTextbox.Text = String.Format("{0:c}", IncomeTax + CPP + EI);


    }

    //Method Calculates Hours, Net and Gross Pay
    public void CalculateFromPaystubInfo(List<PaystubInfo> validEntries)
    {
        double calcGrossPay = 0;
        double calcOvertimeHours = 0;
        double calcRegularHours = 0;
        double calcAfterTax = 0;
        double calcAllHours = 0;

        foreach (PaystubInfo item in validEntries)
        {
            calcGrossPay = calcGrossPay + item.amountPaid;
            calcOvertimeHours = calcOvertimeHours + item.overtimeHours;
            calcRegularHours = calcRegularHours + item.amountOfHours;
        }

        calcAfterTax = calcGrossPay - EI + IncomeTax + CPP;
        calcAllHours = calcOvertimeHours + calcRegularHours;

        _grossPay = calcGrossPay;
        _netPay = calcAfterTax;
        _overtimeHours = calcOvertimeHours;
        _regularHours = calcRegularHours;
        _totalHours = calcAllHours;


    }

    //Method that only gets and returns valid paystubs
    public List<PaystubInfo> createListOfValidPaystubInfo(List<PaystubInfo> allEntries)
    {
        List<PaystubInfo> validEntries = new List<PaystubInfo>();


        foreach (PaystubInfo item in allEntries)
        {
            if (item.endTime <= _endTime && item.beginningTime >= _startTime)
            {
                validEntries.Add(item);
            }
        }


        return validEntries;
    }



    protected void Button1_Click(object sender, EventArgs e)
    {
        Button1.Visible = false;
        ScriptManager.RegisterStartupScript(this, GetType(), "Print", "Print()", true);
        Button1.Visible = true;
    }
}
