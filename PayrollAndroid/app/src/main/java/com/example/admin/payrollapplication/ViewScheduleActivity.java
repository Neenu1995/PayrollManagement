package com.example.admin.payrollapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 *ViewScheduleActivity displays the schedule details of the logged in employee
 *
 * @author  Team6 COMP 313-001
 * @version 1.0
 * @since   10/4/2019
 */
public class ViewScheduleActivity extends AppCompatActivity {
    // Write a message to the database
    private static final String TAG = "ViewScheduleActivity";
    Context context = ViewScheduleActivity.this;
    FirebaseAuth auth;
    DatabaseReference myRef ;
    TextView scheduleText;

    Map<String, String> schedule;
    Map<String, String> start;
    Map<String, String> end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        scheduleText = findViewById(R.id.scheduleText);
        schedule = new HashMap<>();
        start = new HashMap<>();
        end = new HashMap<>();
        String uid = FirebaseAuth.getInstance().getUid(); //Get ID from Authentication
        myRef = FirebaseDatabase.getInstance().getReference("employee").child(uid).child("schedule");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Schedule emp = dataSnapshot.getValue(Schedule.class);
                if(emp!=null){
                    scheduleText.setText(emp.toString());
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
