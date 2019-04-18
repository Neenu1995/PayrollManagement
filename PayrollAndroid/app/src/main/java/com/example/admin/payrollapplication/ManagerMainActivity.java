package com.example.admin.payrollapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ManagerMainActivity extends AppCompatActivity {

    Button registerButton;
    Button emailButton;
    Button viewEmpButton;
    Button viewScheduleButton;
    Button scheduleButton;
    Button viewListButton;
    Button calculatePayButton;
    Context context = ManagerMainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);

        registerButton = findViewById(R.id.registerButton);
        emailButton = findViewById(R.id.sendEmailButton);
        viewEmpButton = findViewById(R.id.empButton);
        viewScheduleButton = findViewById(R.id.scheduleButton);
        viewListButton = findViewById(R.id.viewEmpListButton);
        scheduleButton = findViewById(R.id.scheduleEmpButton);
        calculatePayButton = findViewById(R.id.calculateButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context,
                        RegistrationActivity.class);
                startActivity(myIntent);
            }
        });

        viewEmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context,
                        DetailsForEmployeeActivity.class);
                startActivity(myIntent);
            }
        });

        viewScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context,
                        ViewScheduleActivity.class);
                startActivity(myIntent);
            }
        });

        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context,
                        ManagerViewDetailsActivity.class);
                startActivity(myIntent);
            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context,
                        Schedule.class);
                startActivity(myIntent);
            }
        });

        calculatePayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context,
                        CalculatePayActivity.class);
                startActivity(myIntent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
