package com.example.admin.payrollapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText nameText;
    EditText passwordText;
    Button submitBtn;

    // Write a message to the database
    DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        myRef = FirebaseDatabase.getInstance().getReference("person");
        nameText = findViewById(R.id.nameText);
        passwordText = findViewById(R.id.passwordText);
        submitBtn = findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson();
            }
        });
    }



    private void addPerson(){
        String name = nameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
            String id = myRef.push().getKey();
            Person person1 = new Person(id,name,password);
            myRef.child(id).setValue(person1);
            Toast.makeText(this,"Data added.",Toast.LENGTH_LONG).show();
        }
            else{
            Toast.makeText(this,"Enter name.",Toast.LENGTH_LONG).show();
            }


    }


}
