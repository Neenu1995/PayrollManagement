package com.example.admin.payrollapplication;

/**
 *
 */
public class Employee {

    private String EmployeeID;
    private String FirstName;
    private String Password;
    private String LastName;
    private String Address;
    private String PhoneNumber;
    private String Email;
    private String Title;
    private Schedule LatestSchedule;

    public Employee(){

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Schedule getLatestSchedule() {
        return LatestSchedule;
    }

    public void setLatestSchedule(Schedule latestSchedule) {
        LatestSchedule = latestSchedule;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    /**
     *
     * @param employeeID
     * @param firstName
     * @param password
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param email
     * @param title
     * @param latestSchedule
     */
    public Employee(String employeeID, String firstName, String password, String lastName, String address, String phoneNumber, String email, String title, Schedule latestSchedule) {
        EmployeeID = employeeID;
        FirstName = firstName;
        Password = password;
        LastName = lastName;
        Address = address;
        PhoneNumber = phoneNumber;
        Email = email;
        Title = title;
        LatestSchedule = latestSchedule;
    }

    /**
     *
     * @param employeeID
     * @param firstName
     * @param password
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param email
     * @param title
     */
    public Employee(String employeeID, String firstName, String password, String lastName, String address, String phoneNumber, String email, String title) {
        EmployeeID = employeeID;
        FirstName = firstName;
        Password = password;
        LastName = lastName;
        Address = address;
        PhoneNumber = phoneNumber;
        Email = email;
        Title = title;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeID='" + EmployeeID + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", Password='" + Password + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Address='" + Address + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Email='" + Email + '\'' +
                ", Title='" + Title + '\'' +
                ", LatestSchedule=" + LatestSchedule.toString() +
                '}';
    }
}
