package com.blast.inapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blast.inapp.databinding.ActivityEmployeeBinding;

import java.util.List;

/**
 * Created by owner on 21/10/2016.
 */

public class EmployeeActivity  extends AppCompatActivity {
    private ActivityEmployeeBinding binding;
    private DBSQLiteHelper dbsqLiteHelper;
    private static final String TAG = "EmployeeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee);
        dbsqLiteHelper = new DBSQLiteHelper(this);

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        String[] adapterCols = new String[]{DBContract.Employer.COLUMN_NAME};
        int[] adapterRowViews = new int[] {android.R.id.text1};


        Cursor cursor = dbsqLiteHelper.getEmployersNames();

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, adapterCols, adapterRowViews, 0);
        cursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.employerSpinner.setAdapter(cursorAdapter);

        //cursor.close();

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = new Employee();
                employee.setFirstName(binding.firstnameEditText.getText().toString());
                employee.setLastName(binding.lastnameEditText.getText().toString());
                employee.setDateOfBirth(Util.getDate(binding.dobEditText.getText().toString()));
                employee.setDateOfEmployment(Util.getDate(binding.employedEditText.getText().toString()));
                employee.setJobDescription(binding.jobDescEditText.getText().toString());
                employee.setEmployerID(((Cursor) binding.employerSpinner.getSelectedItem()).getInt(0));
                dbsqLiteHelper.saveEmployeeDetails(employee);
            }
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = binding.firstnameEditText.getText().toString().trim();
                String lastName = binding.lastnameEditText.getText().toString().trim();

                List<Employee> employees = dbsqLiteHelper.getEmployeeDetails(firstName, lastName);
                binding.recycleView.setAdapter(new JoinRecyclerViewCursorAdapter(employees));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbsqLiteHelper.close();
    }
}
