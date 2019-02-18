package com.example.admin.payrollapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText nameText;
    EditText passwordText;
    Button submitBtn;
    DatabaseReference myRef;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myRef = FirebaseDatabase.getInstance().getReference("person");
        nameText = findViewById(R.id.nameText);
        passwordText = findViewById(R.id.passwordText);
        submitBtn = findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(LoginActivity.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        });


    }

    private boolean checkCredentials(final String name) {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                // String value = dataSnapshot.getValue(String.class);
                boolean result = false;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String key = ds.getKey();
                    Employee emp = ds.getValue(Employee.class);

                    if(name.equals(emp.getFirstName())){
                       result = true;
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

        return false;
    }
}