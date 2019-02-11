using PayrollManagement.Model;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PayrollManagement
{
    public partial class UpdateEmployee : System.Web.UI.Page
    {
        SqlConnection con;
        DataSet ds = new DataSet();

        protected void Page_Load(object sender, EventArgs e)
        {
            positionsList.DataSource = System.Enum.GetValues(typeof(Employee.POSITION));
            positionsList.DataBind();
        }

        protected void btnCancel_Click(object sender, EventArgs e)
        {
            ClearTextBoxes();
        }

        public void ClearTextBoxes()
        {
            txtEmployeeID.Text = "";
            txtFirstName.Text = "";
            txtLastName.Text = "";
            txtAddress.Text = "";
            txtContactNumber.Text = "";
            txtEmail.Text = "";
            positionsList.SelectedIndex = -1;
        }

        protected void btnUpdate_Click(object sender, EventArgs e)
        {
            Employee.POSITION f = (Employee.POSITION)Enum.Parse(typeof(Employee.POSITION), positionsList.SelectedValue);
            int pos = (int)f;
            con = new SqlConnection(@"Data Source=GAMEFREAK;Initial Catalog=Software_ProjectDB;Integrated Security=True");
            string firstName = txtFirstName.Text;
            string lastName = txtLastName.Text;
            string address = txtAddress.Text;
            string phoneNum = txtContactNumber.Text;
            string email = txtEmail.Text;
            int employeeID = Convert.ToInt32(txtEmployeeID.Text);

            try
            {
                con.Open();

                // Checks if there is an employee with corresponding ID
                SqlCommand cmd2 = new SqlCommand("select * from Employee where EmployeeID = @employeeID", con);
                cmd2.Parameters.AddWithValue("employeeID", employeeID);
                SqlDataAdapter da = new SqlDataAdapter(cmd2);
                da.Fill(ds);
                int i = ds.Tables[0].Rows.Count;

                // If record exists
                if (i > 0)
                {
                    // Updates employee table with corresponding values in textboxes if not null
                    SqlCommand cmd = new SqlCommand("update Employee set FirstName = IsNull(NullIf(@firstName, ''), FirstName), LastName = IsNull(NullIf(@lastName, ''), LastName), Address = IsNull(NullIf(@address, ''), Address), " +
                   "PhoneNum = IsNull(NullIf(@phoneNum, ''), PhoneNum), Email = IsNull(NullIf(@email, ''), Email), Title = IsNull(NullIf(@title, ''), Title) where EmployeeID = '" + employeeID + "'", con);
                    cmd.Parameters.AddWithValue("firstName", firstName);
                    cmd.Parameters.AddWithValue("lastName", lastName);
                    cmd.Parameters.AddWithValue("address", address);
                    cmd.Parameters.AddWithValue("phoneNum", phoneNum);
                    cmd.Parameters.AddWithValue("email", email);
                    cmd.Parameters.AddWithValue("title", pos);
                    cmd.ExecuteNonQuery();

                    lblResult.Text = "Employee information updated.";
                    lblResult.ForeColor = System.Drawing.Color.Green;
                    ClearTextBoxes();
                }
                else
                {
                    lblResult.Text = "Employee cannot be found.";
                    lblResult.ForeColor = System.Drawing.Color.IndianRed;
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine(ex.ErrorCode);
            }
            finally
            {
                con.Close();
            }
        }
    }
}