package com.example.eric.dispatchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
    }

    public void clickcheck(View v)
    {
        Intent in = new Intent(EmployeeActivity.this,CheckWorkActivity.class);
        startActivity(in);
    }

    public void clickgopresent(View v)
    {
        Intent in = new Intent(this,PresentActivity.class);
        startActivity(in);
    }
}
