package com.example.admin.payrollapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CalculatePayActivity extends AppCompatActivity {
    DatabaseReference reference;
    Button btnPay;
    TextView txtviewPay;
    TextView txtviewDed;
    EditText eEmpInsurance;
    EditText eCpp;
    EditText ePayPerHr;
    EditText eOvtHrs;
    EditText eRegHrs;
    EditText eGrossPay;
    EditText empId;
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
    String employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_pay);
        reference = FirebaseDatabase.getInstance().getReference("employee");

        empId = (EditText)findViewById(R.id.txtEmpId);
        eGrossPay = (EditText)findViewById(R.id.txtGrossPay);
        eRegHrs = (EditText)findViewById(R.id.txtRegHrs);
        eOvtHrs = (EditText)findViewById(R.id.txtOvtHrs);
        ePayPerHr = (EditText)findViewById(R.id.txtPayPerHr);
        eCpp = (EditText)findViewById(R.id.txtCPP);
        eEmpInsurance = (EditText)findViewById(R.id.txtEmpInsurance);
        txtviewPay = (TextView)findViewById(R.id.txtviewPay);
        txtviewDed = (TextView)findViewById(R.id.txtviewOverallDed);

        btnPay = (Button) findViewById(R.id.btnCalculatePay);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeId = empId.getText().toString();
                txtgrossPay = Double.valueOf(eGrossPay.getText().toString());
                regHrs = Double.valueOf(eRegHrs.getText().toString());
                ovtHrs = Double.valueOf(eOvtHrs.getText().toString());
                payPerHr = Double.valueOf(ePayPerHr.getText().toString());
                cpp = Double.valueOf(eCpp.getText().toString());
                empInsurance = Double.valueOf(eEmpInsurance.getText().toString());
                totalHrs = regHrs + ovtHrs;
                grossPay = payPerHr * totalHrs;
                totalDeduction = empInsurance + cpp;

                calculatePay();

                txtviewPay.setText(String.valueOf(netPay));

                reference.orderByChild("employeeID").equalTo(employeeId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            Employee temp = ds.getValue(Employee.class);

                            if(temp.getEmployeeID().equals(employeeId)){
                                ds.child("hoursWorked").getRef().setValue(totalHrs);
                                ds.child("pay").child("end").getRef().setValue(netPay);
                                reference.removeEventListener(this);
                                Intent intent = new Intent(CalculatePayActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(CalculatePayActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private double calculatePay(){
        if(grossPay > 0 && grossPay <= 47630){
            incomeTax = 0.15;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(grossPay * incomeTax));
            netPay = grossPay - overallDed;
        }
        else if (grossPay > 47630 && grossPay <= 95259){
            incomeTax = 0.205;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(grossPay * incomeTax));
            netPay = grossPay - overallDed;
            return netPay;
        }
        else if (grossPay > 95259 && grossPay <= 147667){
            incomeTax = 0.26;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(grossPay * incomeTax));
            netPay = grossPay - overallDed;
            txtviewPay.setText(String.valueOf(netPay));
        }
        else if (grossPay > 147667 && grossPay <= 210371){
            incomeTax = 0.29;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(grossPay * incomeTax));
            netPay = grossPay - overallDed;
            return netPay;
        }
        else if (grossPay > 210371){
            incomeTax = 0.33;
            overallDed = totalDeduction + (grossPay * incomeTax);
            txtviewDed.setText(String.valueOf(grossPay * incomeTax));
            netPay = grossPay - overallDed;
            return netPay;
        }
        else{
            txtviewPay.setText("Enter valid salary");
        }
        return netPay;
    }
}
