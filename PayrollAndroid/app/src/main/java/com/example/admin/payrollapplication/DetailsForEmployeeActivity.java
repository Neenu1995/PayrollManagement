package com.example.admin.payrollapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 *DetailsForEmployeeActivity is used to calculate the net pay of an employee
 * for a given time period
 *
 * @author  Team6 COMP 313-001
 * @version 1.0
 * @since   10/4/2019
 */
public class DetailsForEmployeeActivity extends AppCompatActivity {
    public TextView emptxtFname;
    public TextView emptxtLname;
    public TextView emptxtPassword;
    public TextView emptxtAddress;
    public TextView emptxtPhone;
    public TextView emptxtEmail;
    public TextView emptxtTitle;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        emptxtFname = (TextView) findViewById(R.id.emptxtFname);
        emptxtLname = (TextView) findViewById(R.id.emptxtLname);
        emptxtPassword = (TextView) findViewById(R.id.emptxtPassword);
        emptxtAddress = (TextView) findViewById(R.id.emptxtAddress);
        emptxtPhone = (TextView) findViewById(R.id.emptxtPhone);
        emptxtEmail = (TextView) findViewById(R.id.emptxtEmail);
        emptxtTitle = (TextView) findViewById(R.id.emptxtTitle);

        String uid = FirebaseAuth.getInstance().getUid(); //Get ID from Authentication
        databaseReference = FirebaseDatabase.getInstance().getReference("employee").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Employee emp = dataSnapshot.getValue(Employee.class);

                emptxtFname.setText(emp.getFirstName());
                emptxtLname.setText(emp.getLastName());
                emptxtPassword.setText(emp.getPassword());
                emptxtAddress.setText(emp.getAddress());
                emptxtPhone.setText(emp.getPhoneNumber());
                emptxtEmail.setText(emp.getEmail());
                emptxtTitle.setText(emp.getTitle());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String msg = "Error: \nMessage: " + databaseError.getMessage()
                        + "\n Details: " + databaseError.getDetails();
                Toast.makeText(DetailsForEmployeeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
