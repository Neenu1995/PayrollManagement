package com.example.admin.payrollapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class SchedulingActivity extends AppCompatActivity {

    EditText emailEditText;
    Button dateButton;
    Button endDateButton;
    Button timeButton;
    Button durationButton;
    Button setScheduleButton;
    TextView dateText;
    TextView endDateText;
    TextView timeText;
    TextView durationText;
    Switch repeatSwitch;

    String date;
    String startDate;
    String endDate;
    String time;
    String startTime;
    String duration;

    GregorianCalendar gStartDate;
    GregorianCalendar gEndDate;
    int lastYear;
    int lastMonth;
    int lastDay;
    boolean startOrEnd;
    DatabaseReference reference;
    DatabaseReference schedules;
    String uid;
    Map<String, String> entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        dateButton = (Button) findViewById(R.id.dateButton);
        endDateButton = (Button) findViewById(R.id.endDateButton);
        dateText = (TextView) findViewById(R.id.dateText);
        endDateText = (TextView) findViewById(R.id.endDateText);
        timeButton = (Button) findViewById(R.id.timeButton);
        timeText = (TextView) findViewById(R.id.timeText);
        durationButton = (Button) findViewById(R.id.durationButton);
        durationText = (TextView) findViewById(R.id.durationText);
        repeatSwitch = (Switch) findViewById(R.id.repeatSwitch);
        setScheduleButton = (Button) findViewById(R.id.setScheduleButton);

        gStartDate = new GregorianCalendar();
        gEndDate = new GregorianCalendar();

        entry = new HashMap<>();

        lastYear = 0;
        lastMonth = 0;
        lastDay = 0;

        reference = FirebaseDatabase.getInstance().getReference("employee");

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                date = year + "-" + (month + 1) + "-" + dayOfMonth;
                view.getDayOfMonth();
//                date = dateToString(new GregorianCalendar(year, month, dayOfMonth), false);
                lastYear = year;
                lastMonth = month;
                lastDay = dayOfMonth;
                if(startOrEnd) {
                    gStartDate.set(year,month,dayOfMonth);
                    startDate = dateToString(gStartDate,false);
                    dateText.setText(startDate);
                } else {
                    gEndDate.set(year,month,dayOfMonth);
                    endDate = dateToString(gEndDate,false);
                    endDateText.setText(endDate);
                }
            }
        };

        final TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String h = (hourOfDay<10) ? ("0"+Integer.toString(hourOfDay)) : Integer.toString(hourOfDay);
                String m = (minute<10) ? ("0"+Integer.toString(minute)) : Integer.toString(minute);
                time = h + ":" + m;
                if(startOrEnd) {
                    timeText.setText(time);
                    startTime = time;
                    gStartDate.set(Calendar.HOUR_OF_DAY,hourOfDay);
                    gStartDate.set(Calendar.MINUTE,minute);
                } else {
                    durationText.setText(time);
                    duration = time;
                    gEndDate.set(Calendar.HOUR_OF_DAY,hourOfDay);
                    gEndDate.set(Calendar.MINUTE,minute);
                }
            }
        };

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if(lastYear ==0||v.getId()== dateButton.getId()) {
                    lastYear = calendar.get(Calendar.YEAR);
                }
                if(lastMonth ==0||v.getId()== dateButton.getId()) {
                    lastMonth = calendar.get(Calendar.MONTH);
                }
                if(lastDay ==0||v.getId()== dateButton.getId()) {
                    lastDay = calendar.get(Calendar.DAY_OF_MONTH);
                }

                startOrEnd = (v.getId()== dateButton.getId() || v.getId()== timeButton.getId());
                if(v.getId()== dateButton.getId()||v.getId()==endDateButton.getId()) {
                    DatePickerDialog dialog = new DatePickerDialog(
                            SchedulingActivity.this, dateSetListener, lastYear, lastMonth, lastDay);
                    dialog.show();
                } else {
                    TimePickerDialog dialog = new TimePickerDialog(
                            SchedulingActivity.this, timeSetListener, 0, 0, false);
                    dialog.show();
                }
            }
        };

        repeatSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                endDateButton.setEnabled(isChecked);
                if (!isChecked) {
                    endDateText.setText("YYYY-MM-DD");
                }
            }
        });

        dateButton.setOnClickListener(buttonListener);
        endDateButton.setOnClickListener(buttonListener);
        timeButton.setOnClickListener(buttonListener);
        durationButton.setOnClickListener(buttonListener);

        setScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startDate.isEmpty()||startTime.isEmpty()||duration.isEmpty()||repeatSwitch.isChecked()&&endDate.isEmpty()) {
                    Toast.makeText(SchedulingActivity.this, "Schedule Incomplete", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(repeatSwitch.isChecked()     //if Repeat Switch is checked/toggled
                        && (gStartDate.get(Calendar.YEAR)>gEndDate.get(Calendar.YEAR)   //if start date year is after end date year
                        || gStartDate.get(Calendar.YEAR)==gEndDate.get(Calendar.YEAR)   //OR if start date year is same as end date year
                        && gStartDate.get(Calendar.MONTH)>gEndDate.get(Calendar.MONTH)  //but start date month is after end date month
                        || gStartDate.get(Calendar.YEAR)==gEndDate.get(Calendar.YEAR)   //OR if start date year is same as end year
                        && gStartDate.get(Calendar.MONTH)==gEndDate.get(Calendar.MONTH) //and start month is same as end month
                        && gStartDate.get(Calendar.DAY_OF_MONTH)>=gEndDate.get(Calendar.DAY_OF_MONTH)) ) {  //but start day is after end
                    Toast.makeText(SchedulingActivity.this, "Start Date must come before End Date", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String employeeEmail = emailEditText.getText().toString();

                reference.orderByChild("email").equalTo(employeeEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Employee employee = ds.getValue(Employee.class);
                            if(employee.getEmail().equals(employeeEmail)) {
                                uid = ds.getKey();
                                createSchedule(ds);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(SchedulingActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
//                schedules = FirebaseDatabase.getInstance().getReference("schedules").child(uid);
//                schedules.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        createSchedule2(dataSnapshot);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
            }
        });
    }

     public void createSchedule(final DataSnapshot ds) {
        final int n = numOfDays(gStartDate,gEndDate);
        final Calendar calendar = new GregorianCalendar(gStartDate.get(Calendar.YEAR),gStartDate.get(Calendar.MONTH),gStartDate.get(Calendar.DAY_OF_MONTH));
         for(int i = 0; i < n; i++) {
             String key = dateToString(calendar,false);
             ds.child("schedule").child(key).child("time").getRef().setValue(startTime);
             ds.child("schedule").child(key).child("duration").getRef().setValue(duration);
             calendar.add(Calendar.DAY_OF_MONTH,1);
         }
     }

    public boolean overlapCheck(String t, String d) {
        if(Double.parseDouble(t.substring(0,2))+
                Double.parseDouble(d.substring(0,2))+
                ((Double.parseDouble(t.substring(3,5))+
                        Double.parseDouble(d.substring(3,5)))/60.0)>=24)
            return true;
        return false;
    }

    public String dateToString(Calendar c1, boolean addTime) {
        String result = c1.get(Calendar.YEAR)+"-";//+(c1.get(Calendar.MONTH)+1)+"-"+c1.get(Calendar.DAY_OF_MONTH);
        result += (c1.get(Calendar.MONTH)+1 < 10) ?
                ("0"+(c1.get(Calendar.MONTH)+1)+"-") : ((c1.get(Calendar.MONTH)+1)+"-");

        result += (c1.get(Calendar.DAY_OF_MONTH) < 10) ?
                ("0"+(c1.get(Calendar.DAY_OF_MONTH))) : (c1.get(Calendar.DAY_OF_MONTH));
        if(addTime){
            int hour = c1.get(Calendar.HOUR_OF_DAY);
            int min = c1.get(Calendar.MINUTE);
            result+="-"+((hour<10) ? ("0"+Integer.toString(hour)) : Integer.toString(hour))
                    + ":" + (min<10 ? "0"+Integer.toString(min) : Integer.toString(min));
        }
        return result;
    }

    public int numOfDays(Calendar start, Calendar end) {
        int total = 0;
        int startYear = start.get(Calendar.YEAR);
        int endYear = end.get(Calendar.YEAR);
        int startMonth = start.get(Calendar.MONTH);
        int startDay = start.get(Calendar.DAY_OF_MONTH);
        int daysInMonth;
        int day;
        int endMonth = start.getActualMaximum(Calendar.MONTH);

        //Count from start year to end
        for (int i = startYear; i <= endYear ; i++) {
            //Last year?
            if(i==endYear) {
                endMonth = end.get(Calendar.MONTH);
            }
            //Cycle through months in year
            for (int j = startMonth; j <= endMonth; j++) {
                //Start first pass on beginning date
                if(i==startYear && j==startMonth && total==0) {
                    day=startDay;
                } else {
                    day=1;
                }
                //Stop at end date
                if(i==endYear && j==endMonth) {
                    daysInMonth = end.get(Calendar.DAY_OF_MONTH);
                } else {
                    Calendar test = new GregorianCalendar(i,j,1);
                    //Get days in calendar to account for leap years
                    daysInMonth = test.getActualMaximum(Calendar.DAY_OF_MONTH);
                }
                //Cycle through days in month
                for(int k = day; k <= daysInMonth; k++) {
                    total++;
                }
            }
            //Set startMonth variable to January after first pass
            startMonth = start.getActualMinimum(Calendar.MONTH);
        }
        return total;
    }

}
