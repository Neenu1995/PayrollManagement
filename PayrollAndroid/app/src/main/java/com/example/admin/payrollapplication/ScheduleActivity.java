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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                String employeeEmail = idText.getText().toString();
                if(shiftView.getText()=="Start of Shift"){
//                    intent.putExtra("start",date);
                    schedule.put("start",date);
                    shiftView.setText(R.string.end_shift);
                } else{
//                    intent.putExtra("end",date);
                    schedule.put("end",date);
                    reference.orderByChild("email").equalTo(employeeEmail).getRef().child("schedule").setValue(schedule);
                }
            }
        });
    }
}
