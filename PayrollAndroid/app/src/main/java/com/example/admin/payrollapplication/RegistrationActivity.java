package com.example.admin.payrollapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    EditText fnameText;
    EditText lnameText;
    EditText phoneText;
    EditText emailText;
    EditText sinText;
    EditText titleText;
    EditText addressText;
    static Integer count = 0;

    Button submitBtn;

    // Write a message to the database
    DatabaseReference myRef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myRef = FirebaseDatabase.getInstance().getReference("employee");
        fnameText = findViewById(R.id.fnameText);
        lnameText = findViewById(R.id.lnameText);
        phoneText = findViewById(R.id.phoneText);
        emailText = findViewById(R.id.emailText);
        addressText = findViewById(R.id.addressText);
        sinText = findViewById(R.id.sinText);
        titleText = findViewById(R.id.titleText);
        submitBtn = findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });
    }

    private void addEmployee(){
        String fname = fnameText.getText().toString().trim();
        String lname = lnameText.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String phone = phoneText.getText().toString().trim();
        String address = addressText.getText().toString().trim();
        String sin = sinText.getText().toString().trim();
        String title = titleText.getText().toString().trim();
        if(!TextUtils.isEmpty(sin)){
            String key = myRef.push().getKey();
            String id = generateID(fname,lname);
            Employee emp = new Employee(id,fname,lname,address,phone,email,sin,title);
            myRef.child(key).setValue(emp);
            Toast.makeText(this,"Data added.",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Enter name.",Toast.LENGTH_LONG).show();
        }


    }

    private String generateID( String firstName,String lastName){
        String res1 = firstName.substring(0, 3);
        String res3 = lastName.substring(0, 3);
        String res4 = res1  + res3;
        String res5 = count.toString().length() == 1 ? ("00" + count)
                : count.toString().length() == 2 ? ("0" + count) : count.toString();
        count = count + 1;
        String finalResult = res4 + res5;
        return finalResult;
    }
}
