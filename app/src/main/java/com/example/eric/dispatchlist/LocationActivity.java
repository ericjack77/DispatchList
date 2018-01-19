package com.example.eric.dispatchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }

    public void clickSubmitLocation(View v)
    {
        EditText et=findViewById(R.id.editText2);
        String loc = et.getText().toString();
        Intent it = new Intent();
        it.putExtra("location",loc);
        setResult(RESULT_OK,it);
        finish();
    }
}
