package com.example.admin.payrollapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    EditText fnameText;
    EditText lnameText;
    EditText phoneText;
    EditText emailText;
    EditText passwordText;
    EditText titleText;
    EditText addressText;
    //ProgressBar progressBar;
    FirebaseAuth auth;

    static Integer count = 0;

    Button submitBtn;

    // Write a message to the database
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myRef = FirebaseDatabase.getInstance().getReference("employee");
        fnameText = findViewById(R.id.fnameText);
        lnameText = findViewById(R.id.lnameText);
        passwordText = findViewById(R.id.passwordText);
        phoneText = findViewById(R.id.phoneText);
        emailText = findViewById(R.id.emailText);
        addressText = findViewById(R.id.addressText);
        titleText = findViewById(R.id.titleText);
        submitBtn = findViewById(R.id.submitButton);
        auth = FirebaseAuth.getInstance();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                signUp();
            }
        });
    }

    /**
     *
     */
    private void signUp() {
        String password = passwordText.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegistrationActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            addEmployee();
                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }

    /**
     *
     */
    private void addEmployee() {
        String fname = fnameText.getText().toString().trim();
        String lname = lnameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String phone = phoneText.getText().toString().trim();
        String address = addressText.getText().toString().trim();

        String title = titleText.getText().toString().trim();
        if (!TextUtils.isEmpty(email)) {
            String key = auth.getUid();
            String id = generateID(fname, lname);
            Employee emp = new Employee(id, fname, lname, password, address, phone, email, title);
            //myRef.setValue(id);
            myRef.child(key).setValue(emp);
        } else {
            Toast.makeText(this, "Enter email.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @return
     */
    private String generateID(String firstName, String lastName) {
        String res1 = firstName.substring(0, 3);
        String res3 = lastName.substring(0, 3);
        String res4 = res1 + res3;
        String res5 = count.toString().length() == 1 ? ("00" + count)
                : count.toString().length() == 2 ? ("0" + count) : count.toString();
        count = count + 1;
        String finalResult = res4 + res5;
        return finalResult;
    }
}
