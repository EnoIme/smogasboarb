package com.blast.inapp;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blast.inapp.databinding.EmployeeListItemBinding;
import com.blast.inapp.databinding.EmployerListItemBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by owner on 1/11/2016.
 */

class JoinRecyclerViewCursorAdapter extends RecyclerView.Adapter<JoinRecyclerViewCursorAdapter.ViewHolder> {

    private List<Employee> employees;


    JoinRecyclerViewCursorAdapter(List<Employee> employees){
       this.employees = employees;
    }

    @Override
    public JoinRecyclerViewCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employer_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final JoinRecyclerViewCursorAdapter.ViewHolder holder, int position) {
        final Employee employee = employees.get(position);

        holder.getBinding().firstNameLabel.setText(employee.getFirstName());
        holder.getBinding().lastNameLabel.setText(employee.getLastName());
        holder.getBinding().dobLabel.setText(Util.formatDate(employee.getDateOfBirth()));
        holder.getBinding().jobDescLabel.setText(employee.getJobDescription());
        holder.getBinding().nameLabel.setText(employee.getEmployerName());
        holder.getBinding().descLabel.setText(employee.getEmployerDescription());
        holder.getBinding().foundedLabel.setText(Util.formatDate(employee.getEmployerFoundedDate()));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        EmployeeListItemBinding itemBinding;

        ViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }

        EmployeeListItemBinding getBinding(){
            return itemBinding;
        }

    }
}
