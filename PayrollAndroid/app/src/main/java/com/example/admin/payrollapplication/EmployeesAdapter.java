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
    Context context;

    public EmployeesAdapter(List<Employee> employees) {
        this.employees = employees;

    }

    public EmployeesAdapter(Context ctx, List<Employee> employeeList) {
        this.employees = employeeList;
        this.context = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        Context context = viewGroup.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);

//        View employeeView = inflater.inflate(R.layout.item_employee, viewGroup, false);

        View employeeView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_items, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(employeeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Get the data model based on position
        final Employee employee = employees.get(i);

//        // Set item views based on your views and data model
//        viewHolder.idTextView.setText(employee.getEmployeeID());
//        TextView textView = viewHolder.nameTextView;
//        textView.setText(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
//        Button button = viewHolder.viewButton;
//        button.setText("View Details");
//
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                // Start NewActivity.class
//                Intent myIntent = new Intent(DetailsForManager.ctxt,
//                        ManagerViewDetailsActivity.class);
//                myIntent.putExtra("employeeID",employee.getEmployeeID());
//                DetailsForManager.ctxt.startActivity(myIntent);
//            }
//        });
        viewHolder.EmployeeIdView.setText(employee.getEmployeeID());
        viewHolder.FirstNameView.setText(employee.getFirstName());
        viewHolder.LastNameView.setText(employee.getLastName());
        viewHolder.AddressView.setText(employee.getAddress());
        viewHolder.PhoneNumberView.setText(employee.getPhoneNumber());
        viewHolder.EmailView.setText(employee.getEmail());
        viewHolder.TitleView.setText(employee.getTitle());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
//        // Your holder should contain a member variable
//        // for any view that will be set as you render a row
//        public TextView idTextView;
//        public TextView nameTextView;
//        public Button viewButton;
//
//        // We also create a constructor that accepts the entire item row
//        // and does the view lookups to find each subview
//        public ViewHolder(View itemView) {
//            // Stores the itemView in a public final member variable that can be used
//            // to access the context from any ViewHolder instance.
//            super(itemView);
//
//            idTextView = (TextView) itemView.findViewById(R.id.employee_id);
//            nameTextView = (TextView) itemView.findViewById(R.id.employee_name);
//            viewButton = (Button) itemView.findViewById(R.id.view_button);
        public TextView EmployeeIdView;
        public TextView FirstNameView;
        public TextView LastNameView;
        public TextView AddressView;
        public TextView PhoneNumberView;
        public TextView EmailView;
        public TextView TitleView;

        public ViewHolder(View itemView) {

            super(itemView);

            EmployeeIdView = (TextView) itemView.findViewById(R.id.ShowEmployeeID);
            FirstNameView= (TextView) itemView.findViewById(R.id.ShowFirstName);
            LastNameView= (TextView) itemView.findViewById(R.id.ShowLastName);
            AddressView= (TextView) itemView.findViewById(R.id.ShowAddress);
            PhoneNumberView= (TextView) itemView.findViewById(R.id.ShowPhoneNumber);
            EmailView= (TextView) itemView.findViewById(R.id.ShowEmail);
            TitleView= (TextView) itemView.findViewById(R.id.ShowTitle);
        }
    }
}
