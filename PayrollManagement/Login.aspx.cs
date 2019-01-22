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
    public partial class Login : System.Web.UI.Page
    {
        protected override void OnPreInit(EventArgs e)
        {
            //Checks which user is entering the system and chooses the master pages for them
            int postion = Model.MiscClass.position;
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
            String today = DateTime.Now.ToString();
            Console.WriteLine(today);
        }

        protected void loginBtn_Click(object sender, EventArgs e)
        {
            Employee employee = Database.authenticateUser(int.Parse(employeeIdTxtBox.Text), passwordTxtBox.Text);

            if (employee.EmployeeId > 300)
            {
                Session["employee"] = employee;
                Session["loginTime"] = DateTime.Now.ToString("yyyy-M-dd h:mm:ss tt");

                Button button = (Button)Master.FindControl("login2");
                Button accountInfo = (Button)Master.FindControl("accountInfo");

                accountInfo.Text = employee.FirstName;
                button.Text = "Logout";

                //Settig up the pages for the user
                MiscClass.position = employee.Position;

                Response.Redirect("Index.aspx");
            }
            else
            {
                commonLabel.Text = "Please check Employee Id or Password";
            }

        }
    }
}