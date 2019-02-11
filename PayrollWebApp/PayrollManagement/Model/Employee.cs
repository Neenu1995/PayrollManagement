using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PayrollManagement.Model
{
    public class Employee
    {
        private String firstName;
        private String lastName;
        private int age;
        private int position;
        private String address;
        private long phoneNumber;
        private String email;
        private int socialNumber;
        private int employeeId;
        private String password;
        private double payRate;
        private int active;


        public double PayRate { get => payRate; set => payRate = value; }
        public int Position { get => position; set => position = value; }
        public string FirstName { get => firstName; set => firstName = value; }
        public string LastName { get => lastName; set => lastName = value; }
        public string Address { get => address; set => address = value; }
        public long PhoneNumber { get => phoneNumber; set => phoneNumber = value; }
        public string Email { get => email; set => email = value; }
        public int SocialNumber { get => socialNumber; set => socialNumber = value; }
        public int EmployeeId { get => employeeId; set => employeeId = value; }
        public string Password { get => password; set => password = value; }
        public int Active { get => active; set => active = value; }

        public int Age
        {
            get { return age; }
            set
            {
                if (value < 14 || value > 127)
                {
                    throw new InvalidAgeGroup("Age must be more than 14 and less than 127");
                }
                age = value;
            }
        }

        public Employee(String firstName, String lastName, int age, String address, long phoneNumber, String email, int position, double payRate, int socialNumber)
        {
            this.FirstName = firstName;
            this.LastName = lastName;
            this.Age = age;
            this.Address = address;
            this.PhoneNumber = phoneNumber;
            this.Email = email;
            this.Position = position;
            this.PayRate = payRate;
            this.SocialNumber = socialNumber;
        }

        //Secondary Constructor needed in the connection class to get 
        public Employee(String firstName, String lastName, int age, String address, long phoneNumber, String email)
        {
            this.FirstName = firstName;
            this.LastName = lastName;
            this.Age = age;
            this.Address = address;
            this.PhoneNumber = phoneNumber;
            this.Email = email;
        }

        public Employee()
        {
        }

        public enum POSITION
        {
            CREW = 1,
            MANAGER = 2,
            ADMIN = 3
        }

    }

}