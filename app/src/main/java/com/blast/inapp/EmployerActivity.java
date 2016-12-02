package com.blast.inapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blast.inapp.databinding.ActivityEmployerBinding;

/**
 * Created by owner on 21/10/2016.
 */

public class EmployerActivity extends AppCompatActivity {

    private static final String TAG = "EmployerActivity" ;
    private ActivityEmployerBinding binding;
    private DBSQLiteHelper dbsqLiteHelper;
    String name, desc, foundedDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbsqLiteHelper = new DBSQLiteHelper(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer);



        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = binding.nameEditText.getText().toString().trim();
                desc = binding.descEditText.getText().toString().trim();
                foundedDate = binding.foundedEditText.getText().toString().trim();
                saveToDB(name, desc, foundedDate);
                clearFields();
            }
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = binding.nameEditText.getText().toString().trim();
                desc = binding.descEditText.getText().toString().trim();
                foundedDate = binding.foundedEditText.getText().toString().trim();
                readFromDB(name, desc, Util.getDate(foundedDate));
                clearFields();
            }
        });
    }

    private void validateFields(){
    }
    private void clearFields(){
        binding.nameEditText.setText("");
        binding.descEditText.setText("");
        binding.foundedEditText.setText("");
        binding.nameEditText.requestFocus();
    }
    private void saveToDB(String name, String desc, String foundedDate){
        Employer employer = new Employer();
        employer.setName(name);
        employer.setDateFounded(Util.getDate(foundedDate));
        employer.setDescription(desc);
        dbsqLiteHelper.saveEmployerDetails(employer);
    }


    private void readFromDB(String name, String desc, long foundedDate){
        List<Employer> employers = dbsqLiteHelper.getEmployerDetails(name, desc, foundedDate);
        binding.recycleView.setAdapter(new RecyclerViewCursorAdapter(employers));
    }
}
