package com.example.admin.payrollapplication;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CalculatePayActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    Context mMockContext;
    @Test
    public void calculatePay(){
        CalculatePayActivity tester = new CalculatePayActivity();
        double incomeTax= 0.205;
        double regHrs = 40;
        double ovtHrs = 6;
        double payPerHr = 25;
        double cpp = 10;
        double empInsurance = 47;

        double totalHrs = regHrs + ovtHrs;;
        double grossPay = payPerHr * totalHrs;
        double totalDeduction = empInsurance + cpp;
        //double result;
       double result = tester.calculatePay();
      //  result = 920.5;
        assertThat(920.5,is(result));
    }
}