package com.example.admin.payrollapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ScheduleActivity extends AppCompatActivity {

    DatabaseReference reference;
    CalendarView calendarView;
    TextView shiftView;
    EditText idText;
    Intent intent;
    String date;
    Button confirmButton;
    Map<String, String> schedule;
    private String employeeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        shiftView = (TextView) findViewById(R.id.shiftText);
        idText = (EditText) findViewById(R.id.idTxt);
        confirmButton = (Button) findViewById(R.id.confirmBtn);
        schedule = new HashMap<>();
        reference = FirebaseDatabase.getInstance().getReference("employee");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "/" + month + "/" + dayOfMonth;
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeEmail = idText.getText().toString();
                if(shiftView.getText().equals("Start of Shift")){
                    schedule.put("start",date);
                    shiftView.setText("End of Shift");
                } else{
                    schedule.put("end",date);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()){
                                Employee temp = ds.getValue(Employee.class);

                                if(temp.getEmail().equals(employeeEmail)){
                                    ds.child("schedule").getRef().setValue(schedule);
                                    reference.removeEventListener(this);
                                    Intent intent = new Intent(ScheduleActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(ScheduleActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
//                    reference.child(key).child("schedule").setValue(schedule);
//                    Toast.makeText(ScheduleActivity.this, eh, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
