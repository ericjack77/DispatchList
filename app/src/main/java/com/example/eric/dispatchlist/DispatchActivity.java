package com.example.eric.dispatchlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eric.dispatchlist.DAOdata.DispatchDAO;
import com.example.eric.dispatchlist.DAOdata.DispatchList;

public class DispatchActivity extends AppCompatActivity {
    TextView tvtime,tvlocation,tvdriver;
    final int time=999,location=998;
    int ch,tmp=-1;
    public static DispatchDAO dispatchdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        tvtime =findViewById(R.id.tvtime);
        tvlocation = findViewById(R.id.tvlocation);
        tvdriver = findViewById(R.id.tvdriver);
        dispatchdao = new DispatchDAO("dispatchLists");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case time:
                tvtime.setText(data.getStringExtra("time"));
                break;
            case location:
                tvlocation.setText(data.getStringExtra("location"));
                break;

        }
    }


    public void clickDriver(View v)
    {
        AlertDialog.Builder dia=new AlertDialog.Builder(DispatchActivity.this);
        dia.setTitle("司機列表");
        final String fruits[]={"Apple","Pineapple","Tomato"};
        tmp=ch;

        dia.setSingleChoiceItems(fruits, ch, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tmp=i;
            }
        });
        dia.setPositiveButton("送出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ch=tmp;
                tvdriver.setText(fruits[ch]);
            }
        });
        dia.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvdriver.setText("司機");
            }
        });

        dia.show();
    }

    public void clickTime(View v)
    {
        Intent it =new Intent(DispatchActivity.this,DatepickActivity.class);
        startActivityForResult(it,time);
    }

    public  void clickLocation(View v)
    {
        Intent it =new Intent(DispatchActivity.this,LocationActivity.class);
        startActivityForResult(it,location);
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
        builder.show();
    }
}
