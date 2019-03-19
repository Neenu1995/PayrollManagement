package com.example.admin.payrollapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class DetailsForEmployeeActivity extends AppCompatActivity {
    public TextView emptxtFname;
    public TextView emptxtLname;
    public TextView emptxtAddress;
    public TextView emptxtPhone;
    public TextView emptxtEmail;
    List<Employee> employees = new ArrayList<>();
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
/*
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    //startActivity(new Intent(DetailsForEmployeeActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        Employee emp = new Employee();

        emptxtFname.setText(user.getDisplayName());
        //emptxtLname.setText(emp.getLastName());
        //emptxtAddress.setText(emp.getAddress());
        emptxtPhone.setText(user.getPhoneNumber());
        emptxtEmail.setText(user.getEmail());*/
    }
}
