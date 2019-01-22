using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Summary description for TaxManagement
/// </summary>
public static class TaxManagement
{

    //Calculating Canadian Pension Plan Contribution Method
    public static double CalculateCPP(double grossPay, bool biWeekly)
    {
        double CppPayment = 0;

        //CPP Tax references
        //Maximum pensionalble yearly earnings for 2018 : 55,900
        //CPP Payment Rate = 0.0495 or 4.95%;
        //Maximum yearly Contribution Cap: 2,953.80

        double pensionableIncome = 0;
        double yearlyCPPContribution = 0;

        //Estimating Yearly Income
        pensionableIncome = grossPay * 26;

        if (pensionableIncome >= 55900) { pensionableIncome = 55900; }

        yearlyCPPContribution = pensionableIncome * 0.0495;

        if (yearlyCPPContribution >= 2953.80) { yearlyCPPContribution = 2953.80; }

        if (biWeekly == true)
        {
            CppPayment = yearlyCPPContribution / 26;
        }

        else if (biWeekly == false) { CppPayment = yearlyCPPContribution / 26; }

        return CppPayment;
    }




    //Calculating EI Method
    public static double CalculateEmploymentInsurance(double grossPay, bool biWeekly)
    {
        double EIPayment = 0;

        //Employment Insurance Tax references
        //Maximum pensionalble EI yearly earnings for 2018 : 51,700
        //EI Payment Rate: 1.66% or 0.0166
        //Maximum yearly Contribution Cap: 858.22

        double pensionableIncome = 0;
        double yearlyEIContribution = 0;

        pensionableIncome = grossPay * 26;
        if (pensionableIncome >= 51700) { pensionableIncome = 51700; }

        yearlyEIContribution = pensionableIncome * 0.0166;
        if (yearlyEIContribution >= 858.22) { yearlyEIContribution = 858.22; }

        try
        {
            if (biWeekly == true)
            {
                EIPayment = yearlyEIContribution / 26;
            }

            else if (biWeekly == false)
            {
                EIPayment = yearlyEIContribution;
            }
        }

        catch (Exception e)
        {
            EIPayment = 0;
        }

        return EIPayment;
    }



    public static double CalculateIncomeTax(double grossPay, string prov, bool biWeekly)
    {
        double yearlyIncome = 0;

        //For bi Weekly
        if (biWeekly == true)
        {
            yearlyIncome = grossPay * 26;
        }

        //For Yearly
        else { yearlyIncome = grossPay; }


        double incomeTax = 0;

        //Province
        string province = prov;

        //Tax
        double provincialTax = 0;
        double federalTax = 0;


        if (province == "Ontario")
        {
            //For provincial tax brackets of each province
            if (yearlyIncome <= 10354)
            {
                provincialTax = 0;
            }

            else if (yearlyIncome > 10354 && yearlyIncome <= 42960)
            {

                provincialTax = yearlyIncome * 0.0505;

            }

            else if (yearlyIncome > 42960 && yearlyIncome <= 85923)
            {

                provincialTax = yearlyIncome * 0.0915;

            }


            else if (yearlyIncome > 85923 && yearlyIncome <= 150000)
            {

                provincialTax = yearlyIncome * 0.1116;

            }


            else if (yearlyIncome > 150000 && yearlyIncome <= 220000)
            {

                provincialTax = yearlyIncome * 0.1216;

            }

            else if (yearlyIncome >= 220000)
            {

                provincialTax = yearlyIncome * 0.1316;

            }
        }

        else if (province == "Manitoba")
        {

            if (yearlyIncome <= 31843)
            {
                provincialTax = yearlyIncome * 0.1080;
            }

            else if (yearlyIncome > 31844 && yearlyIncome <= 42960)
            {

                provincialTax = yearlyIncome * 0.1275;

            }

            else if (yearlyIncome > 42960)
            {

                provincialTax = yearlyIncome * 0.1740;

            }

        }

        else if (province == "Quebec")
        {
            if (yearlyIncome <= 15012)
            {
                provincialTax = 0;
            }

            else if (yearlyIncome > 15012 && yearlyIncome <= 43055)
            {

                provincialTax = yearlyIncome * 0.15;

            }

            else if (yearlyIncome > 43055 && yearlyIncome <= 86105)
            {

                provincialTax = yearlyIncome * 0.20;

            }

            else if (yearlyIncome > 86105 && yearlyIncome <= 104765)
            {

                provincialTax = yearlyIncome * 0.24;

            }
            else if (yearlyIncome > 104765)
            {

                provincialTax = yearlyIncome * 0.2575;

            }
        }

        else if (province == "Alberta")
        {
            if (yearlyIncome >= 126625)
            {
                provincialTax = yearlyIncome * 0.10;
            }

            else if (yearlyIncome > 126625 && yearlyIncome <= 151950)
            {

                provincialTax = yearlyIncome * 0.12;

            }

            else if (yearlyIncome > 151950 && yearlyIncome <= 202600)
            {

                provincialTax = yearlyIncome * 0.13;

            }

            else if (yearlyIncome > 202600 && yearlyIncome <= 303900)
            {

                provincialTax = yearlyIncome * 0.14;

            }

            else if (yearlyIncome > 303900)
            {

                provincialTax = yearlyIncome * 0.15;

            }
        }



        else if (province == "Saskatchewan")
        {
            if (yearlyIncome >= 45225)
            {
                provincialTax = yearlyIncome * 0.1075;
            }

            else if (yearlyIncome > 45225 && yearlyIncome <= 129214)
            {

                provincialTax = yearlyIncome * 0.1275;

            }

            else if (yearlyIncome > 129214)
            {

                provincialTax = yearlyIncome * 0.1475;

            }
        }


        else if (province == "Newfoundland")
        {
            if (yearlyIncome >= 35851)
            {
                provincialTax = yearlyIncome * 0.0870;
            }

            else if (yearlyIncome > 35851 && yearlyIncome <= 71701)
            {

                provincialTax = yearlyIncome * 0.1450;

            }

            else if (yearlyIncome > 71701 && yearlyIncome <= 128010)
            {

                provincialTax = yearlyIncome * 0.1580;

            }

            else if (yearlyIncome > 128011 && yearlyIncome <= 179214)
            {

                provincialTax = yearlyIncome * 0.1730;

            }

            else if (yearlyIncome > 179214)
            {

                provincialTax = yearlyIncome * 0.1830;

            }
        }

        else if (province == "British Columbia")
        {
            if (yearlyIncome <= 10412)
            {
                provincialTax = 0;
            }

            else if (yearlyIncome > 10412 && yearlyIncome <= 39676)
            {

                provincialTax = yearlyIncome * 0.0506;

            }

            else if (yearlyIncome > 39676 && yearlyIncome <= 79353)
            {

                provincialTax = yearlyIncome * 0.0770;

            }

            else if (yearlyIncome > 79353 && yearlyIncome <= 91106)
            {

                provincialTax = yearlyIncome * 0.1050;

            }

            else if (yearlyIncome > 91106 && yearlyIncome <= 110629)
            {

                provincialTax = yearlyIncome * 0.1229;

            }

            else if (yearlyIncome > 110629 && yearlyIncome <= 150000)
            {

                provincialTax = yearlyIncome * 0.1470;

            }

            else if (yearlyIncome > 150000)
            {

                provincialTax = yearlyIncome * 0.1680;

            }
        }

        else if (province == "Nova Scotia")
        {
            if (yearlyIncome <= 29590)
            {
                provincialTax = yearlyIncome * 0.0879;
            }

            else if (yearlyIncome > 29590 && yearlyIncome <= 59180)
            {

                provincialTax = yearlyIncome * 0.1495;

            }

            else if (yearlyIncome > 59180 && yearlyIncome <= 93000)
            {

                provincialTax = yearlyIncome * 0.1667;

            }

            else if (yearlyIncome > 93000 && yearlyIncome <= 150000)
            {

                provincialTax = yearlyIncome * 0.1750;

            }

            else if (yearlyIncome > 150000)
            {

                provincialTax = yearlyIncome * 0.21;

            }
        }


        else if (province == "New Brunswick")
        {
            if (yearlyIncome <= 41059)
            {
                provincialTax = yearlyIncome * 0.0968;
            }

            else if (yearlyIncome > 41059 && yearlyIncome <= 82119)
            {

                provincialTax = yearlyIncome * 0.1482;

            }

            else if (yearlyIncome > 82119 && yearlyIncome <= 133507)
            {

                provincialTax = yearlyIncome * 0.1652;

            }

            else if (yearlyIncome > 133507 && yearlyIncome <= 152100)
            {

                provincialTax = yearlyIncome * 0.1784;

            }

            else if (yearlyIncome > 152100)
            {

                provincialTax = yearlyIncome * 0.2030;

            }
        }

        else if (province == "Yukon")
        {
            if (yearlyIncome <= 45916)
            {
                provincialTax = yearlyIncome * 0.0640;
            }

            else if (yearlyIncome > 45916 && yearlyIncome <= 91831)
            {

                provincialTax = yearlyIncome * 0.09;

            }

            else if (yearlyIncome > 91832 && yearlyIncome <= 142353)
            {

                provincialTax = yearlyIncome * 0.1090;

            }

            else if (yearlyIncome > 142353 && yearlyIncome <= 500000)
            {

                provincialTax = yearlyIncome * 0.1280;

            }

            else if (yearlyIncome > 500000)
            {

                provincialTax = yearlyIncome * 0.15;

            }
        }


        else if (province == "Northwest Territories")
        {
            if (yearlyIncome <= 41585)
            {
                provincialTax = yearlyIncome * 0.0590;
            }

            else if (yearlyIncome > 41585 && yearlyIncome <= 83172)
            {

                provincialTax = yearlyIncome * 0.0860;

            }

            else if (yearlyIncome > 83172 && yearlyIncome <= 135219)
            {

                provincialTax = yearlyIncome * 0.1220;

            }


            else if (yearlyIncome > 135219)
            {

                provincialTax = yearlyIncome * 0.1405;

            }
        }


        else if (province == "Nunavut")
        {
            if (yearlyIncome <= 43780)
            {
                provincialTax = yearlyIncome * 0.04;
            }

            else if (yearlyIncome > 43780 && yearlyIncome <= 87560)
            {

                provincialTax = yearlyIncome * 0.07;

            }

            else if (yearlyIncome > 87560 && yearlyIncome <= 142353)
            {

                provincialTax = yearlyIncome * 0.09;

            }


            else if (yearlyIncome > 142353)
            {

                provincialTax = yearlyIncome * 0.1150;

            }
        }

        //Federal Tax Calculations
        //If income is within the federal tax exemption
        if (yearlyIncome <= 11809)
        {

            federalTax = 0;

        }

        else if (yearlyIncome > 11809 && yearlyIncome <= 45916)
        {
            federalTax = yearlyIncome * 0.15;
        }

        else if (yearlyIncome > 45916 && yearlyIncome <= 91831)
        {
            federalTax = yearlyIncome * 0.2050;
        }

        else if (yearlyIncome > 91831 && yearlyIncome <= 142353)
        {
            federalTax = yearlyIncome * 0.26;
        }

        else if (yearlyIncome > 142353 && yearlyIncome <= 202800)
        {
            federalTax = yearlyIncome * 0.29;
        }

        else if (yearlyIncome > 202800)
        {
            federalTax = yearlyIncome * 0.33;
        }

        //Adding provincial and federal tax
        try
        {
            if (biWeekly == true)
            {
                incomeTax = (provincialTax + federalTax) / 26;
            }

            else if (biWeekly == false)
            {
                incomeTax = provincialTax + federalTax;
            }
        }

        catch (Exception e) { incomeTax = 0; }


        return incomeTax;

    }

    public enum PROVINCE
    {
        ON = 1,
        MB = 2,
        QC = 3,
        AB = 4,
        SK = 5,
        NL = 6,
        BC = 7,
        NS = 8,
        NB = 9,
        YT = 10,
        NT = 11,
        NU = 12
    }
}