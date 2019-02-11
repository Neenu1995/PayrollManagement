using PayrollManagement.Model;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace PayrollManagement.Controller
{
    public static class Database
    {
        static SqlConnection con;

        static Database()
        {
            con = new SqlConnection(@"Data Source=GAMEFREAK;Initial Catalog=Software_ProjectDB;Integrated Security=True");
        }
        public static int insertIntoTable(Employee parameters)
        {
            int EmployeeId = 0;


            SqlCommand cmd = new SqlCommand(@"insert into [Employee] (FirstName,LastName,Age,Title,Address,PhoneNum,Email,hourlyPayRate,Social_Security_Num) values(@firstName,@lastName,@age,@title,@address,@phoneNumber,@email,@payRate,@socialNumber)", con);
            cmd.Parameters.Add("@firstName", System.Data.SqlDbType.VarChar);
            cmd.Parameters.Add("@lastName", System.Data.SqlDbType.VarChar);
            cmd.Parameters.Add("@age", System.Data.SqlDbType.Int);
            cmd.Parameters.Add("@title", System.Data.SqlDbType.Int);
            cmd.Parameters.Add("@address", System.Data.SqlDbType.VarChar);
            cmd.Parameters.Add("@phoneNumber", System.Data.SqlDbType.VarChar);
            cmd.Parameters.Add("@email", System.Data.SqlDbType.VarChar);
            cmd.Parameters.Add("@payRate", System.Data.SqlDbType.Decimal);
            cmd.Parameters.Add("@socialNumber", System.Data.SqlDbType.VarChar);

            cmd.Parameters["@firstName"].Value = parameters.FirstName;
            cmd.Parameters["@lastName"].Value = parameters.LastName;
            cmd.Parameters["@age"].Value = parameters.Age;
            cmd.Parameters["@title"].Value = parameters.Position;
            cmd.Parameters["@address"].Value = parameters.Address;
            cmd.Parameters["@phoneNumber"].Value = parameters.PhoneNumber;
            cmd.Parameters["@email"].Value = parameters.Email;
            cmd.Parameters["@payRate"].Value = parameters.PayRate;
            cmd.Parameters["@socialNumber"].Value = parameters.SocialNumber;



            try
            {
                con.Open();
                cmd.ExecuteNonQuery();

                EmployeeId = selectTable(parameters.FirstName, parameters.LastName);

            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
            }
            finally
            {
                con.Close();
            }
            return EmployeeId;
        }

        public static int updateTable(int employeeId, String password)
        {
            int flag = 0;
            try
            {
                con.Open();
                SqlCommand cmd = new SqlCommand(@"update Employee set Password=@password where EmployeeID = @employeeId", con);

                cmd.Parameters.Add("@password", System.Data.SqlDbType.VarChar);
                cmd.Parameters.Add("@employeeId", System.Data.SqlDbType.VarChar);

                cmd.Parameters["@password"].Value = password;
                cmd.Parameters["@employeeId"].Value = employeeId;

                flag = cmd.ExecuteNonQuery();

            }
            catch (SqlException e)
            {
                Console.WriteLine(e.ErrorCode);
            }
            finally
            {
                con.Close();
            }
            return flag;
        }

        public static int selectTable(String firstName, String lastName)
        {
            int EmployeeId = 0;
            try
            {
                SqlCommand cmd = new SqlCommand(@"Select EmployeeID from Employee where FirstName=@firstName and LastName=@lastName", con);
                cmd.Parameters.Add("@firstName", System.Data.SqlDbType.VarChar);
                cmd.Parameters.Add("@lastName", System.Data.SqlDbType.VarChar);

                cmd.Parameters["@firstName"].Value = firstName;
                cmd.Parameters["@lastName"].Value = lastName;

                SqlDataReader reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        EmployeeId = reader.GetInt32(0);
                    }
                }
                else
                {
                    Console.WriteLine("No rows found.");
                }
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.ErrorCode);
            }
            finally
            {
                con.Close();
            }


            return EmployeeId;
        }

        public static Employee authenticateUser(int employeeId, String password)
        {
            Employee employee = new Employee();
            try
            {
                con.Open();
                // SqlCommand cmd = new SqlCommand("Select EmployeeId, Title from Employee where EmployeeID=@employeeId and Password=@password", con);
                SqlCommand cmd = new SqlCommand("Select * from Employee where EmployeeID=@employeeId and Password=@password", con);
                cmd.Parameters.Add("@employeeId", System.Data.SqlDbType.VarChar);
                cmd.Parameters.Add("@password", System.Data.SqlDbType.VarChar);

                cmd.Parameters["@employeeId"].Value = employeeId;
                cmd.Parameters["@password"].Value = password;

                SqlDataReader reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        employee.EmployeeId = reader.GetInt32(0);
                        employee.FirstName = reader.GetString(1);
                        employee.LastName = reader.GetString(2);
                        employee.Age = reader.GetInt32(3);
                        employee.Position = reader.GetInt32(4);
                        employee.Address = reader.GetString(5);
                        employee.PhoneNumber = int.Parse(reader.GetString(6));
                        employee.Email = reader.GetString(7);
                        employee.Password = reader.GetString(8);
                        employee.PayRate = double.Parse(reader.GetValue(9).ToString());
                        employee.SocialNumber = int.Parse(reader.GetValue(10).ToString());
                    }
                }
                else
                {
                    Console.WriteLine("No rows found.");
                }
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.ErrorCode);
            }
            finally
            {
                con.Close();
            }


            return employee;
        }

        public static bool isEmployeeEmail(string email)
        {
            try
            {
                con.Open();
                SqlCommand cmd = new SqlCommand("Select * from Employee where Email=@email", con);
                cmd.Parameters.Add("@email", System.Data.SqlDbType.VarChar);
                cmd.Parameters["@email"].Value = email;
                SqlDataReader reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    con.Close();
                    return true;
                }
                else
                {
                    con.Close();
                    return false;
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                throw;
            }
        }

        public static void insertHours(int employeeId, String clockInTime, String clockOutTime)
        {


            SqlCommand cmd = new SqlCommand(@"insert into [HoursWorked] (EmployeeID,LogInDate,LogOutTime) values(@employeeId,@clockInTime,@clockOutTime)", con);

            cmd.Parameters.Add("@employeeId", System.Data.SqlDbType.Int);
            cmd.Parameters.Add("@clockInTime", System.Data.SqlDbType.VarChar);
            cmd.Parameters.Add("@clockOutTime", System.Data.SqlDbType.VarChar);

            cmd.Parameters["@employeeId"].Value = employeeId;
            cmd.Parameters["@clockInTime"].Value = clockInTime;
            cmd.Parameters["@clockOutTime"].Value = clockOutTime;

            try
            {
                con.Open();
                cmd.ExecuteNonQuery();


            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
            }
            finally
            {
                con.Close();
            }
        }
    }
}