package com.example.admin.payrollapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText emailTextV;
    EditText passwordTextV;
    Button submitBtn;
    Boolean radioUser ;
    DatabaseReference myRef;
    TextView registerTextV;
    FirebaseAuth myAuth;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myRef = FirebaseDatabase.getInstance().getReference("Employee");

        emailTextV = findViewById(R.id.emailText);
        passwordTextV = findViewById(R.id.passwordText);
        submitBtn = findViewById(R.id.submitButton);
        registerTextV = findViewById(R.id.registerTextButton);
        radioUser = false;
        myAuth = FirebaseAuth.getInstance();


        registerTextV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this,
                        RegistrationActivity  .class);
                startActivity(myIntent);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
             //checkCredentials();
                signIn();

            }
        });


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_man:
                if (checked)
                    radioUser = true;//Manager
                    break;
            case R.id.radio_emp:
                if (checked)
                    radioUser = false;//Employee
                    break;
        }
    }

    private void signIn(){
        String email = emailTextV.getText().toString().trim();
        String password = passwordTextV.getText().toString().trim();

        myAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = myAuth.getCurrentUser();
                   if(radioUser) {
                       Toast.makeText(LoginActivity.this, "Manager Sign In Successful", Toast.LENGTH_LONG).show();
                       Intent myIntent = new Intent(LoginActivity.this,
                               MainActivity.class);
                       startActivity(myIntent);
                   }
                   else{
                       Toast.makeText(LoginActivity.this, "Employee Sign In Successful", Toast.LENGTH_LONG).show();
                       Intent myIntent = new Intent(LoginActivity.this,
                               MainActivity.class);
                       startActivity(myIntent);
                   }
                } else {
                    Toast.makeText(LoginActivity.this,"Sign In Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkCredentials(String userID) {
        final String currentUserID = userID;
        Employee emp = new Employee();
        DatabaseReference myCurrentRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(currentUserID);
        myCurrentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userType = dataSnapshot.child("Title").getValue().toString();
                if (userType.equals("Manager")) {
                    Toast.makeText(LoginActivity.this,"Manager Sign In Successfull",Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(myIntent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Employee Sign In Successfull",Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(myIntent);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        });


    }
}