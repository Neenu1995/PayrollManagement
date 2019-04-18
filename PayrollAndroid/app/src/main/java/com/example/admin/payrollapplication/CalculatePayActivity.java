package com.example.admin.payrollapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatePayActivity extends AppCompatActivity {
    Button btnPay;
    TextView txtviewPay;
    TextView txtviewDed;
    EditText eEmpInsurance;
    EditText eCpp;
    EditText ePayPerHr;
    EditText eOvtHrs;
    EditText eRegHrs;
    EditText eGrossPay;
    double incomeTax;
    double txtgrossPay;
    double regHrs;
    double ovtHrs;
    double payPerHr;
    double cpp;
    double empInsurance;
    double netPay;
    double overallDed;
    double totalHrs;
    double grossPay;
    double totalDeduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_pay);

        eGrossPay = (EditText)findViewById(R.id.txtGrossPay);
        txtgrossPay = Double.valueOf(eGrossPay.getText().toString());
        eRegHrs = (EditText)findViewById(R.id.txtRegHrs);
        regHrs = Double.valueOf(eRegHrs.getText().toString());
        eOvtHrs = (EditText)findViewById(R.id.txtOvtHrs);
        ovtHrs = Double.valueOf(eOvtHrs.getText().toString());
        ePayPerHr = (EditText)findViewById(R.id.txtPayPerHr);
        payPerHr = Double.valueOf(ePayPerHr.getText().toString());
        eCpp = (EditText)findViewById(R.id.txtCPP);
        cpp = Double.valueOf(eCpp.getText().toString());
        eEmpInsurance = (EditText)findViewById(R.id.txtEmpInsurance);
        empInsurance = Double.valueOf(eEmpInsurance.getText().toString());
        txtviewPay = (TextView)findViewById(R.id.txtviewPay);
        txtviewDed = (TextView)findViewById(R.id.txtviewOverallDed);

        btnPay = (Button) findViewById(R.id.btnCalculatePay);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalHrs = regHrs + ovtHrs;
                grossPay = payPerHr * totalHrs;
                totalDeduction = empInsurance + cpp;

                calculatePay();

                txtviewPay.setText(String.valueOf(netPay));
            }
        });

        startActivity(new Intent(CalculatePayActivity.this, MainActivity.class));
        finish();
    }

    private double calculatePay(){
        if(grossPay > 0 && grossPay <= 47630){
            incomeTax = 0.15;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(overallDed));
            netPay = grossPay - overallDed;

        }
        else if (grossPay > 47630 && grossPay <= 95259){
            incomeTax = 0.205;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(overallDed));
            netPay = grossPay - overallDed;
            return netPay;
        }
        else if (grossPay > 95259 && grossPay <= 147667){
            incomeTax = 0.26;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(overallDed));
            netPay = grossPay - overallDed;
            txtviewPay.setText(String.valueOf(netPay));
        }
        else if (grossPay > 147667 && grossPay <= 210371){
            incomeTax = 0.29;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(overallDed));
            netPay = grossPay - overallDed;
            return netPay;
        }
        else if (grossPay > 210371){
            incomeTax = 0.33;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(overallDed));
            netPay = grossPay - overallDed;
            return netPay;
        }
        else{
            txtviewPay.setText("Enter valid salary");
        }
        return netPay;
    }
}
