package com.example.admin.payrollapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 *
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    Context context = LoginActivity.this;
    EditText emailTextV;
    EditText passwordTextV;
    Button submitBtn;
    DatabaseReference myRef;
    TextView registerTextV;
    FirebaseAuth myAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myRef = FirebaseDatabase.getInstance().getReference("Employee");

        emailTextV = findViewById(R.id.emailText);
        passwordTextV = findViewById(R.id.passwordText);
        submitBtn = findViewById(R.id.submitButton);
        registerTextV = findViewById(R.id.registerTextButton);

        myAuth = FirebaseAuth.getInstance();


        registerTextV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this,
                        RegistrationActivity.class);
                startActivity(myIntent);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String email = emailTextV.getText().toString().trim();
                String password = passwordTextV.getText().toString().trim();
                signIn(email,password);

            }
        });


    }

    /**
     *
     */
    public void signIn(String email,String password) {

        myAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String uid = FirebaseAuth.getInstance().getUid(); //Get ID from Authentication
                            myRef = FirebaseDatabase.getInstance().getReference("employee").child(uid);

                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Employee emp = dataSnapshot.getValue(Employee.class);
                                    //Schedule emp = dataSnapshot.getValue(Schedule.class);
                                    if (emp != null) {
                                        if (emp.getTitle().equals("Manager")) {
                                            Toast.makeText(LoginActivity.this, "Manager Sign In Successful", Toast.LENGTH_LONG).show();
                                            Intent myIntent = new Intent(LoginActivity.this,
                                                    ManagerMainActivity.class);
                                            startActivity(myIntent);
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Employee Sign In Successful", Toast.LENGTH_LONG).show();
                                            Intent myIntent = new Intent(LoginActivity.this,
                                                    MainActivity.class);
                                            startActivity(myIntent);
                                        }

                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    String msg = "Error: \nMessage: " + databaseError.getMessage()
                                            + "\n Details: " + databaseError.getDetails();
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else {
                            Toast.makeText(LoginActivity.this, "Sign In Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}