package com.example.eric.dispatchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmployerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer);
    }

    public void clickDispatch(View v)
    {
        Intent it =new Intent(EmployerActivity.this,DispatchActivity.class);
        startActivity(it);
    }
}
