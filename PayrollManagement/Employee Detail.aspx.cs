using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using PayrollManagement.Model;

namespace PayrollManagement
{
    public partial class Employee_Detail : System.Web.UI.Page
    {
        private int _employeeID;
        protected override void OnPreInit(EventArgs e)
        {
            if (Session["employee"] != null)
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
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["employee"] != null)
            {
                Employee employee = (Employee)Session["employee"];
                _employeeID = employee.EmployeeId;
                String source = @"Data Source=GAMEFREAK;Initial Catalog=Software_ProjectDB;Integrated Security=True";
                SqlConnection conn = new SqlConnection(source);

                conn.Open();
                String queryDisplay = "SELECT Title FROM Employee WHERE EMPLOYEE_ID = " + _employeeID;
                SqlCommand cmd = new SqlCommand(queryDisplay, conn);
                SqlDataReader mdr = cmd.ExecuteReader();
                if (mdr.Read())
                {
                    string title = mdr["Title"].ToString();
                    if (title == "2")
                    {
                        btnEdit.Visible = true;
                    }
                    else
                    {
                        btnEdit.Visible = false;
                        btnView.Visible = false;
                        txtEmpId.Visible = false;
                        lblEmployeeId.Visible = false;
                        btnSave.Visible = false;
                    }
                }
            }
        }

        protected void btnView_Click(object sender, EventArgs e)
        {
            try
            {
                if (!String.IsNullOrEmpty(txtEmpId.Text))
                {
                    String source = @"Data Source=GAMEFREAK;Initial Catalog=Software_ProjectDB;Integrated Security=True";
                    SqlConnection conn = new SqlConnection(source);

                    conn.Open();
                    String queryDisplay = "SELECT * FROM Employee WHERE EmployeeID = " + int.Parse(txtEmpId.Text);
                    SqlCommand cmd = new SqlCommand(queryDisplay, conn);
                    SqlDataReader mdr = cmd.ExecuteReader();

                    if (mdr.Read())
                    {
                        txtFname.Text = mdr["firstName"].ToString();
                        txtLname.Text = mdr["lastName"].ToString();
                        txtPhone.Text = mdr["phoneNum"].ToString();
                        txtEmail.Text = mdr["email"].ToString();
                        lblSSN.Visible = true;
                        lblSSN.Text = mdr["social_security_num"].ToString();
                        txtAdd1.Text = mdr["address"].ToString();
                    }
                    conn.Close();

                }
                else
                {
                    lblViewWarning.Visible = true;
                    lblViewWarning.Text = "You must enter employee ID";
                }
            }
            catch (Exception)
            {
                lblViewWarning.Visible = true;
                lblViewWarning.Text = "Please enter valid employee ID";
            }
        }

        protected void btnEdit_Click(object sender, EventArgs e)
        {
            if (!String.IsNullOrEmpty(txtEmpId.Text))
            {
                enableControls();
            }
            else
            {
                lblViewWarning.Visible = true;
                lblViewWarning.Text = "You must enter employee ID";
            }
        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            if (!String.IsNullOrEmpty(txtEmpId.Text))
            {
                String source = @"Data Source=GAMEFREAK;Initial Catalog=Software_ProjectDB;Integrated Security=True";
                SqlConnection conn = new SqlConnection(source);

                conn.Open();
                String queryDisplay = "SELECT * FROM Employee WHERE EmployeeID = " + int.Parse(txtEmpId.Text);
                String queryUpdate = "UPDATE Employee SET firstName='" + this.txtFname.Text + "', lastName='" + this.txtLname.Text + "',phoneNum='" + this.txtPhone.Text + "', email='" + this.txtEmail.Text + "', address='" + this.txtAdd1.Text + "' WHERE EmployeeID = '" + this.txtEmpId.Text + "'";
                SqlCommand cmd = new SqlCommand(queryUpdate, conn);
                SqlCommand cmd1 = new SqlCommand(queryDisplay, conn);
                SqlDataReader mdr = cmd.ExecuteReader();

                if (mdr.Read())
                {
                    txtFname.Text = mdr["firstName"].ToString();
                    txtLname.Text = mdr["lastName"].ToString();
                    txtPhone.Text = mdr["phoneNum"].ToString();
                    txtEmail.Text = mdr["email"].ToString();
                    lblSSN.Text = mdr["social_security_num"].ToString();
                    txtAdd1.Text = mdr["address"].ToString();
                }
                conn.Close();

                disableControls();
            }
            else
            {
                lblViewWarning.Visible = true;
                lblViewWarning.Text = "You must enter employee ID";
            }
        }
        void enableControls()
        {
            txtFname.Enabled = true;
            txtLname.Enabled = true;
            txtEmail.Enabled = true;
            txtPhone.Enabled = true;
            txtAdd1.Enabled = true;
        }

        void disableControls()
        {
            txtFname.Enabled = false;
            txtLname.Enabled = false;
            txtPhone.Enabled = false;
            txtEmail.Enabled = false;
            txtAdd1.Enabled = false;
        }
    }
}