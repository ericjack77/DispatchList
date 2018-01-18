package com.example.eric.dispatchlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eric.dispatchlist.DAOdata.DispatchList;

public class DispatchActivity extends AppCompatActivity {
    TextView tvtime;
    final int time=999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        tvtime =findViewById(R.id.textView7);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case time:
                tvtime.setText(data.getStringExtra("time"));
        }
    }

    public void clickTime(View v)
    {
        Intent it =new Intent(DispatchActivity.this,DatepickActivity.class);
        startActivityForResult(it,time);
    }

    public void clickSubmit(View v)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提交工單");
        builder.setMessage("確認送出?");
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //以後要在此送出資料去fireBase
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }
}
