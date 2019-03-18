package com.example.admin.payrollapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder> {
    private List<Employee> employees;

    public EmployeesAdapter(List<Employee> employees) {
        this.employees = employees;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View employeeView = inflater.inflate(R.layout.item_employee, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(employeeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Get the data model based on position
        final Employee employee = employees.get(i);

        // Set item views based on your views and data model
        viewHolder.idTextView.setText(employee.getEmployeeID());
        TextView textView = viewHolder.nameTextView;
        textView.setText(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
        Button button = viewHolder.viewButton;
        button.setText("View Details");

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                Intent myIntent = new Intent(DetailsForManager.ctxt,
                        ManagerViewDetailsActivity.class);
                myIntent.putExtra("employeeID",employee.getEmployeeID());
                DetailsForManager.ctxt.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView idTextView;
        public TextView nameTextView;
        public Button viewButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            idTextView = (TextView) itemView.findViewById(R.id.employee_id);
            nameTextView = (TextView) itemView.findViewById(R.id.employee_name);
            viewButton = (Button) itemView.findViewById(R.id.view_button);
        }
    }
}
