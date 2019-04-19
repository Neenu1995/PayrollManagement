package com.example.admin.payrollapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsForManager extends AppCompatActivity {
    public static Context ctxt;
    DatabaseReference mRootRef;
    List<Employee> employees = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctxt = DetailsForManager.this;
        setContentView(R.layout.activity_details_for_manager);
        recyclerView = (RecyclerView) findViewById(R.id.employee_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailsForManager.this));
        //itemlist = new ArrayList<>();
        mRootRef = FirebaseDatabase.getInstance().getReference("employee");

        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Employee emp = ds.getValue(Employee.class);
//                    emp.setEmployeeID(emp.getEmployeeID());
//                    emp.setFirstName(emp.getFirstName());
//                    emp.setLastName(emp.getLastName());
//                    employees.add(emp);
                    employees.add(emp);
                }
                mAdapter = new EmployeesAdapter(DetailsForManager.this,employees);
                recyclerView.setAdapter(mAdapter);
                mRootRef.removeEventListener(this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "There is some error", Toast.LENGTH_LONG).show();
            }
        });
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // TODO: CHANGE THIS LIST TO THE LIST FROM FIREBASE TO GET THE ACTUAL DATA

    }
}
