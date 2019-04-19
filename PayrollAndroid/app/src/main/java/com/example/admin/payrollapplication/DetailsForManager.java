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

/**
 *DetailsForManagerActivity displays the details of all the employees
 *
 * @author  Team6 COMP 313-001
 * @version 1.0
 * @since   10/4/2019
 */
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
        mRootRef = FirebaseDatabase.getInstance().getReference("employee");

        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Employee emp = ds.getValue(Employee.class);
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

        recyclerView.setHasFixedSize(true);
    }
}
