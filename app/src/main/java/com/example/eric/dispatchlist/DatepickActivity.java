package com.example.eric.dispatchlist;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DatepickActivity extends AppCompatActivity {
    DatePicker dp;
    TimePicker tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepick);
        tp=findViewById(R.id.timePicker);
        dp=findViewById(R.id.datePicker);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void clickSubmitTime(View v)
    {
        int year = dp.getYear();
        int month = dp.getMonth()+1;
        int day = dp.getDayOfMonth();
        int hour=tp.getHour();
        int min =tp.getMinute();
        String gettime = year + "/" + month + "/" + day+"("+hour+":"+min+")";
        Intent i=new Intent();
        i.putExtra("time",gettime);
        setResult(RESULT_OK,i);
        finish();

        //        Bundle b=new Bundle();
//        b.putString("B", "I am B");
//        i.putExtras(b);

    }
}
