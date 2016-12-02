package com.blast.inapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.employeeBtn).setOnClickListener(this);
        findViewById(R.id.employerBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.employerBtn:
                startActivity(new Intent(this, EmployerActivity.class));
                break;
            case R.id.employeeBtn:
                startActivity(new Intent(this, EmployeeActivity.class));
                break;
            default:
                break;
        }
    }
}
