package com.example.eric.dispatchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LocationActivity extends AppCompatActivity {
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        et=findViewById(R.id.editText2);
        if (DispatchActivity.bloc == true)
        {
            et.setHint("請輸入地點");
        }
        else if (DispatchActivity.bconsumer == true)
        {
            et.setHint("請輸入客戶聯絡人");
        }
        else if (DispatchActivity.bcontel == true)
        {
            et.setHint("請輸入客戶聯絡人電話");
        }
        else if (DispatchActivity.bnote == true)
        {
            et.setHint("請輸入備註");
        }
    }

    public void clickSubmitLocation(View v)
    {

        String loc = et.getText().toString();


        if (DispatchActivity.bloc == true)
        {
            Intent it = new Intent();
            it.putExtra("location",loc);
            setResult(RESULT_OK,it);
            DispatchActivity.bloc=false;
            finish();
        }
        else if (DispatchActivity.bconsumer == true)
        {
            Intent it = new Intent();
            it.putExtra("consumer",loc);
            setResult(RESULT_OK,it);
            DispatchActivity.bconsumer=false;
            finish();
        }
        else if (DispatchActivity.bcontel == true)
        {
            Intent it = new Intent();
            it.putExtra("contel",loc);
            setResult(RESULT_OK,it);
            DispatchActivity.bcontel=false;
            finish();
        }
        else if (DispatchActivity.bnote == true)
        {
            Intent it = new Intent();
            it.putExtra("note",loc);
            setResult(RESULT_OK,it);
            DispatchActivity.bnote=false;
            finish();
        }

    }
}
