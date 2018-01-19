package com.example.eric.dispatchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.eric.dispatchlist.DAOdata.DispatchDAO;

public class MainActivity extends AppCompatActivity {
    public static DispatchDAO employeedao;
    public static DispatchDAO cranedao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employeedao = new DispatchDAO("employeeList");
        cranedao = new DispatchDAO("Crane");
//        employeedao.cranelist.add();
//        employeedao.cranelist.get().name;
    }

    public void clickLogin(View v)
    {
        Intent it =new Intent(MainActivity.this,EmployerActivity.class);
        startActivity(it);
    }
}
