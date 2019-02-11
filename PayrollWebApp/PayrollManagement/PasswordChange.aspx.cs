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
    public partial class PasswordChange : System.Web.UI.Page
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

        protected void submit_Click(object sender, EventArgs e)
        {
            Employee employee = (Employee)Session["employee"];
            int empId = employee.EmployeeId;
            String pass = confirmPasswordTxtBox.Text;
            int flag = Database.updateTable(empId, pass);
            if (flag > 0)
            {
                statusLabel.Text = "Password Updated";
                confirmPasswordTxtBox.Text = "";
                passwordTxtBox.Text = "";
            }
            else
            {
                statusLabel.Text = "Password Update Failed";
            }
        }
    }
}