package com.example.admin.payrollapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    EditText nameTextV;
    EditText passwordTextV;
    Button submitBtn;
    DatabaseReference myRef;
    FirebaseAuth myAuth;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myRef = FirebaseDatabase.getInstance().getReference("person");
        nameTextV = findViewById(R.id.nameText);
        passwordTextV = findViewById(R.id.passwordText);
        submitBtn = findViewById(R.id.submitButton);
        myAuth = FirebaseAuth.getInstance();



        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
             //checkCredentials();
                signIn();

//                Intent myIntent = new Intent(LoginActivity.this,
//                        MainActivity.class);
//                startActivity(myIntent);

            }
        });


    }

    private void signIn(){
        String name = nameTextV.getText().toString().trim();
        String password = passwordTextV.getText().toString().trim();

        myAuth.signInWithEmailAndPassword(name,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Sign In Successfull",Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(LoginActivity.this,"Sign In Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkCredentials() {

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = nameTextV.getText().toString().trim();
                String password = passwordTextV.getText().toString().trim();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String key = ds.getKey();
                    Employee emp = ds.getValue(Employee.class);

                    if(name.equals(emp.getFirstName()) && password.equals(emp.getPassword())){

                        Intent myIntent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(myIntent);
                    }
                    Log.d(TAG, "Test Name is: " + emp.getFirstName());
                    Log.d(TAG, "Test ID is: " + emp.getLastName());
                    //Toast.makeText(this,p.toString(),Toast.LENGTH_LONG).show();
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