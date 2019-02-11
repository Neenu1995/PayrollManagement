using PayrollManagement.Controller;
using PayrollManagement.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PayrollManagement
{
    public partial class MasterPage : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["employee"] != null)
            {
                login2.Text = "Logout";
                accountInfo.Text = ((Employee)Session["employee"]).FirstName;

                passChange.Visible = true;
            }
            else
            {
                MiscClass.position = 1;
                login2.Text = "Login";
            }
        }

        protected void passChange_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("PasswordChange.aspx");
        }

        protected void login2_Click(object sender, EventArgs e)
        {
            if (login2.Text.Equals("Logout"))
            {
                //Write the logout codes here

                Employee employee = (Employee)Session["employee"];
                int empId = employee.EmployeeId;
                String clockInTime = HttpContext.Current.Session["loginTime"].ToString();
                String clockOutTime = DateTime.Now.ToString("yyyy-M-dd h:mm:ss tt");
                Database.insertHours(empId, clockInTime, clockOutTime);

                HttpContext.Current.Session.Clear();
                Response.Redirect("Index.aspx");
            }
            else
            {
                Response.Redirect("Login.aspx");
            }
        }

        protected void accountInfo_Click(object sender, EventArgs e)
        {
            Response.Redirect("EmpDisplay.aspx");
        }
    }
}