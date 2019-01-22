using PayrollManagement.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PayrollManagement
{
    public partial class Custom_Paystub_Page : System.Web.UI.Page
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

        protected void Page_Load(object sender, EventArgs e)
        {





            double gross = (double)Session["Gross"];
            double totalHours = (double)Session["totalHours"];
            double overtimeHours = (double)Session["overtimeHours"];
            double regularHours = (double)Session["regularHours"];
            double payrate = (double)Session["payRate"];
            double shiftCount = (double)Session["shiftCount"];
            string timeframe = (string)Session["timeFrame"];
            double ei = (double)Session["ei"];
            double cpp = (double)Session["cpp"];
            double incomeTax = (double)Session["incomeTax"];
            double netPay = (double)Session["netPay"];
            double allDeduction = (double)Session["overallDeductions"];
            Employee employee = (Employee)Session["employee"];






            FullHoursTextBox.Text = totalHours.ToString();
            GrossPayTextBox.Text = String.Format("{0:c}", gross);
            RegularHoursTextBox.Text = regularHours.ToString();
            OvertimeHoursTextBox.Text = overtimeHours.ToString();
            NetPayTextBox.Text = String.Format("{0:c}", netPay);
            PayRateTextBox.Text = String.Format("{0:c}", employee.PayRate);
            NameLabel.Text = employee.FirstName + " " + employee.LastName;
            SSnLabel.Text = "***-***-" + employee.SocialNumber.ToString().Substring(6, 3);
            IDLabel.Text = Convert.ToString(employee.EmployeeId);
            PayPeriodLabel.Text = timeframe;
            IncomeTaxTextBox.Text = String.Format("{0:c}", incomeTax);
            CPPTextBox.Text = String.Format("{0:c}", cpp);
            EITextBox.Text = String.Format("{0:c}", ei);
            OverallDeductionsTextbox.Text = String.Format("{0:c}", allDeduction);
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Button1.Visible = false;
            ScriptManager.RegisterStartupScript(this, GetType(), "Print", "Print()", true);
            Button1.Visible = true;
        }
    }
}