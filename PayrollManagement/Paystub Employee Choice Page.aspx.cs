using PayrollManagement.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Paystub_Employee_Choice_Page : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

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





    protected void Calculate_Button_Click(object sender, EventArgs e)
    {
        string employeeID = "";
        int id = 0;

        try
        {
            employeeID = Employee_ID_Textbox.Text;

            Employee employee = ConnectionClass.GetEmployeeInfo(Convert.ToInt32(employeeID));

            DateTime beginningDate = End_Calendar.SelectedDate.AddDays(-14);
            DateTime endDate = End_Calendar.SelectedDate;

            Session["StartDate"] = beginningDate;
            Session["EndDate"] = endDate;


            if (int.TryParse(employeeID, out id) == true && beginningDate != DateTime.MinValue
                && endDate != DateTime.MinValue && employee != null && employee.Active == 1)
            {
                Session["EmployeeID"] = id;
                Session["StartDate"] = beginningDate;
                Session["EndDate"] = endDate;

                Response.Redirect("Paystub Page.aspx");
            }

            else { errorLabel.Text = "Please enter a valid time and Employee ID"; }
        }

        catch (Exception ex)
        {

            errorLabel.Text = "Please enter a valid time and Employee ID";
        }

    }

}