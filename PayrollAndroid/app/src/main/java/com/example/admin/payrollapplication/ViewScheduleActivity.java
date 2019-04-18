package com.example.admin.payrollapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewScheduleActivity extends AppCompatActivity {
    // Write a message to the database
    private static final String TAG = "ViewScheduleActivity";
    Context context = ViewScheduleActivity.this;
    FirebaseAuth auth;
    DatabaseReference myRef ;
    TextView scheduleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        scheduleText = findViewById(R.id.scheduleText);

        String uid = FirebaseAuth.getInstance().getUid(); //Get ID from Authentication
        myRef = FirebaseDatabase.getInstance().getReference("employee").child(uid);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Employee emp = dataSnapshot.getValue(Employee.class);
                if(emp.getLatestSchedule()!=null){
                    Schedule sch = emp.getLatestSchedule();
                    scheduleText.setText(sch.toString());
                }else{
                    scheduleText.setText("You have no recent work history");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String msg = "Error: \nMessage: " + databaseError.getMessage()
                        + "\n Details: " + databaseError.getDetails();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
