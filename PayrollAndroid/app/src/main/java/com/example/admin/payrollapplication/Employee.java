package com.example.admin.payrollapplication;

import java.util.HashMap;

/**
 *DetailsForManagerActivity displays the details of all the employees
 *
 * @author  Team6 COMP 313-001
 * @version 1.0
 * @since   10/4/2019
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
    private HashMap<String,String> LatestSchedule;


    public Employee(){

    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        Title = title;
    }


    /**
     *
     * @return
     */
    public String getEmployeeID() {
        return EmployeeID;
    }

    /**
     *
     * @param employeeID
     */
    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return Password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return LastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return Address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        Address = address;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public HashMap<String, String> getLatestSchedule() {
        return LatestSchedule;
    }

    public void setLatestSchedule(HashMap<String, String> latestSchedule) {
        LatestSchedule = latestSchedule;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Employee(String employeeID, String firstName, String password, String lastName, String address, String phoneNumber, String email, String title, HashMap<String, String> latestSchedule) {
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
