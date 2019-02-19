package com.example.admin.payrollapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsForManager extends AppCompatActivity {

    ListView l1;
    List<String> empList;
    DatabaseReference myRef;
    ArrayAdapter<String> adapter;
    Employee employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_for_manager);
        l1 = (ListView)findViewById(R.id.deptListview);

        empList = new ArrayList<>();

        myRef = FirebaseDatabase.getInstance().getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                empList.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                // String value = dataSnapshot.getValue(String.class);
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    employee = ds.getValue(Employee.class);
                    empList.add(employee.getFirstName());
                    empList.add(employee.getLastName());
                    empList.add(employee.getAddress());
                    empList.add(employee.getPhoneNumber());
                    empList.add(employee.getEmail());
                }
                adapter = new ArrayAdapter<>(DetailsForManager.this, android.R.layout.activity_list_item);
                l1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "There is some error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
