using PayrollManagement.Model;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PayrollManagement
{
    public partial class EmpDisplay : System.Web.UI.Page
    {
        private int _employeeID;

        protected void Page_Load(object sender, EventArgs e)
        {
            Employee employee = (Employee)Session["employee"];
            _employeeID = employee.EmployeeId;
            String source = @"Data Source=GAMEFREAK;Initial Catalog=Software_ProjectDB;Integrated Security=True";
            SqlConnection conn = new SqlConnection(source);

            conn.Open();
            String queryDisplay = "SELECT * FROM Employee WHERE EmployeeID = " + _employeeID;
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
    }
}