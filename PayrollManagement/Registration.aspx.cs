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
    public partial class Registration : System.Web.UI.Page
    {
        public int Position;
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

        protected void Page_Load(object sender, EventArgs e)
        {

            positionsList.DataSource = System.Enum.GetValues(typeof(Employee.POSITION));
            positionsList.DataBind();

        }

        protected void confirm_Click(object sender, EventArgs e)
        {
            Employee.POSITION f = (Employee.POSITION)Enum.Parse(typeof(Employee.POSITION), positionsList.SelectedValue);
            int pos = (int)f;

            String firstName = firstNameTxtBox.Text;
            string lastName = lastNameTxtBox.Text;
            int age = int.Parse(ageTxtBox.Text);
            string address = addressTxtBox.Text;
            string email = emailTxtBox.Text;
            long phoneNumber = long.Parse(numberTxtBox.Text);
            long pay = long.Parse(payRate.Text);
            int socialNumber = int.Parse(SINTxtBox.Text);

            try
            {
                int id = Database.insertIntoTable(new Employee(
                firstName,
                lastName,
                age,
                address,
                phoneNumber,
                email,
                 pos,
                pay,
                socialNumber
                  ));

                //Disabling all the controls for further changes
                disableControls();

                EmployeeIdTxtBox.Text = id.ToString();
            }
            catch (Exception ex)
            {
                commonLabel.Text = ex.Message;
            }

        }

        protected void create_Click(object sender, EventArgs e)
        {
            int flag = Database.updateTable(int.Parse(EmployeeIdTxtBox.Text), passwordTxtBox.Text);
            if (flag > 0)
            {
                commonLabel.Text = "Employee Registered";
            }

        }

        private void disableControls()
        {
            firstNameTxtBox.Enabled = false;
            lastNameTxtBox.Enabled = false;
            ageTxtBox.Enabled = false;
            addressTxtBox.Enabled = false;
            emailTxtBox.Enabled = false;
            numberTxtBox.Enabled = false;
            payRate.Enabled = false;
            SINTxtBox.Enabled = false;
        }

        private void clearControls()
        {
            firstNameTxtBox.Text = "";
            lastNameTxtBox.Text = "";
            ageTxtBox.Text = "";
            addressTxtBox.Text = "";
            emailTxtBox.Text = "";
            numberTxtBox.Text = "";
            payRate.Text = "";
            SINTxtBox.Text = "";

        }

        protected void clear_Click(object sender, EventArgs e)
        {
            clearControls();
        }

        protected void clearPass_Click(object sender, EventArgs e)
        {
            passwordTxtBox.Text = "";
            confirmPasswordTxtBox.Text = "";
        }

    }
}