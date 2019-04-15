package com.example.admin.payrollapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsForEmployeeActivity extends AppCompatActivity {
    public TextView emptxtFname;
    public TextView emptxtLname;
    public TextView emptxtPassword;
    public TextView emptxtAddress;
    public TextView emptxtPhone;
    public TextView emptxtEmail;
    public TextView emptxtTitle;
    List<Employee> employees = new ArrayList<>();
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    DatabaseReference databaseReference;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        //Get current signed in user's email
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        emptxtFname = (TextView) findViewById(R.id.emptxtFname);
        emptxtLname = (TextView) findViewById(R.id.emptxtLname);
        emptxtPassword = (TextView) findViewById(R.id.emptxtPassword);
        emptxtAddress = (TextView) findViewById(R.id.emptxtAddress);
        emptxtPhone = (TextView) findViewById(R.id.emptxtPhone);
        emptxtEmail = (TextView) findViewById(R.id.emptxtEmail);
        emptxtTitle = (TextView) findViewById(R.id.emptxtTitle);

        databaseReference = FirebaseDatabase.getInstance().getReference("employee");
        query = databaseReference.orderByChild("email").equalTo(email);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Employee emp = new Employee();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    emp = ds.getValue(Employee.class);
                }

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
                Toast.makeText(DetailsForEmployeeActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
