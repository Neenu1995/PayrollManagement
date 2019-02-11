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
    public partial class Setup : System.Web.UI.Page
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

        protected void Page_Load(object sender, EventArgs e)
        {
            provinceList.DataSource = System.Enum.GetValues(typeof(TaxManagement.PROVINCE));
            provinceList.DataBind();
        }

        protected void confirmBtn_Click(object sender, EventArgs e)
        {
            int result = 0;
            Employee rootUser = null;
            try
            {
                rootUser = new Employee(nameBox.Text, nameBox.Text, 0, "N/A", Int32.Parse(numberTxtBox.Text), emailTxtBox.Text);
            }
            catch (Exception)
            {
                resultLabel.Text = "Failed to register!";
            }
            if (rootUser != null)
            {
                result = Database.insertIntoTable(rootUser);
            }
            if(rootUser==null||result==0)
            {
                resultLabel.Text = "Registration Succeeded!";
            }
            else
            {
                Application["Country"] = countryList.SelectedValue;
                Application["Province"] = provinceList.SelectedValue;
            }
        }
    }
}