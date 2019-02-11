using PayrollManagement.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PayrollManagement
{
    public partial class Create_Employee_Schedule : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try
            {
                int id = 0;
                string employeeID = EmployeeIDTextBox.Text;

                Employee employee = ConnectionClass.GetEmployeeInfo(Convert.ToInt32(employeeID));

                string endDate = End_Calendar.SelectedDate.ToShortDateString();
                string endTime = EndTime.Text;


                string beginningTime = BeginTime.Text;
                string begginingDate = Begin_Calendar.SelectedDate.ToShortDateString();

                if (int.TryParse(employeeID, out id) == true &&
                       Begin_Calendar.SelectedDate != DateTime.MinValue
                      && End_Calendar.SelectedDate != DateTime.MinValue
                      && employee != null && employee.Active == 1)
                {
                    string start = begginingDate + " " + beginningTime;
                    string end = endDate + " " + endTime;

                    ConnectionClass.AddSchedule(start, end, id);

                    errorLabel.ForeColor = System.Drawing.Color.Green;
                    errorLabel.Text = "Schedule added successfully";
                }

                else
                {
                    errorLabel.ForeColor = System.Drawing.Color.Red;
                    errorLabel.Text = "Please enter a valid time and Employee ID";
                }
            }

            catch (Exception ex)
            {

                errorLabel.Text = ex.Message;
            }
        }
    }
    }
