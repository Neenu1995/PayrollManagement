using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Summary description for PaystubInfo
/// </summary>
public class PaystubInfo
{

    public DateTime beginningTime { get; set; }
    public DateTime endTime { get; set; }
    public double amountOfHours { get; set; }
    public double overtimeHours { get; set; }
    public double amountPaid { get; set; }
    public TimeSpan workedShift { get; set; }
    public double payRate { get; set; }


    public PaystubInfo(DateTime start, DateTime end, double payRate)
    {
        beginningTime = start;
        endTime = end;
        workedShift = endTime - beginningTime;
        this.payRate = payRate;
        CalculateHours();
        CalculatePay();


    }

    public void CalculateHours()
    {
        if (workedShift.Hours > 8)
        {
            this.amountOfHours = 8;
            this.overtimeHours = workedShift.TotalHours - 8;
        }

        else { this.amountOfHours = workedShift.TotalHours; }
    }


    public void CalculatePay()
    {
        double regularHourlyPay = payRate * amountOfHours;
        double overtimePay = (payRate * 2) * overtimeHours;
        amountPaid = regularHourlyPay + overtimePay;
    }


}