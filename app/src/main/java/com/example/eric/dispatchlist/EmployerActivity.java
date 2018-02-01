package com.example.eric.dispatchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.eric.dispatchlist.DAOdata.DispatchDAO;

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

    public void clickgopresent(View v)
    {
        Intent in = new Intent(this,PresentActivity.class);
        startActivity(in);
    }
}
