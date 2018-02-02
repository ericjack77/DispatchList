package com.example.eric.dispatchlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eric.dispatchlist.DAOdata.Crane;
import com.example.eric.dispatchlist.DAOdata.DispatchDAO;
import com.example.eric.dispatchlist.DAOdata.DispatchEnum;
import com.example.eric.dispatchlist.DAOdata.DispatchList;
import com.example.eric.dispatchlist.DAOdata.Employee;

import java.util.ArrayList;

import static com.example.eric.dispatchlist.DAOdata.DispatchEnum.admiting;
import static com.example.eric.dispatchlist.DAOdata.DispatchEnum.sending;

public class DispatchActivity extends AppCompatActivity {
    TextView tvtime,tvlocation,tvdriver,tvapprentice,tvcar,tvconsumer,tvcontel,tvnote,tvid,tvetime;
    final int time=999,location=998,consumer=997,contel=996,note=995,etime=994;
    int ch,tmp=-1;
    public static DispatchDAO dispatchdao;
    public static boolean bloc=false,bconsumer=false,bcontel=false,bnote=false,btime=false,betime=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        tvtime =findViewById(R.id.tvtime);
        tvetime =findViewById(R.id.tvetime);
        tvlocation = findViewById(R.id.tvlocation);
        tvdriver = findViewById(R.id.tvdriver);
        tvapprentice =findViewById(R.id.tvapprentice);
        tvcar = findViewById(R.id.tvcar);
        tvconsumer =findViewById(R.id.tvconsumer);
        tvcontel =findViewById(R.id.tvcontel);
        tvnote = findViewById(R.id.tvnote);
        tvid = findViewById(R.id.tvid);

        dispatchdao = new DispatchDAO("dispatchLists");
        int size = MainActivity.dao.dispatchLists.size()+1;
        tvid.setText(String.valueOf(size));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case time:
                tvtime.setText(data.getStringExtra("time"));
                break;
            case etime:
                tvetime.setText(data.getStringExtra("etime"));
                break;
            case location:
                tvlocation.setText(data.getStringExtra("location"));
                break;
            case consumer:
                tvconsumer.setText(data.getStringExtra("consumer"));
                break;
            case contel:
                tvcontel.setText(data.getStringExtra("contel"));
                break;
            case note:
                tvnote.setText(data.getStringExtra("note"));
                break;


        }
    }


    public void clickDriver(View v)
    {
        AlertDialog.Builder dia=new AlertDialog.Builder(DispatchActivity.this);
        dia.setTitle("司機列表");

        final ArrayList<String> driverlist =new ArrayList<>();

        for(Employee c: MainActivity.dao.employeeList)
        {
            Log.d("array",c.id+"號,姓名:"+c.name+",職位:"+c.position);
            if(c.position.equals("司機"))
            {
                driverlist.add(c.name);
                Log.d("driverlist",driverlist.toString());
            }
        }

        final String driver[] = (String[]) driverlist.toArray(new String[0]);

        tmp=ch;

        dia.setSingleChoiceItems(driver, ch, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tmp=i;
            }
        });
        dia.setPositiveButton("送出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ch=tmp;
                tvdriver.setText(driver[ch]);
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

    public void clickApprentice(View v)
    {
        AlertDialog.Builder dia=new AlertDialog.Builder(DispatchActivity.this);
        dia.setTitle("助手列表");

        final ArrayList<String> apprelist =new ArrayList<>();

        for(Employee c: MainActivity.dao.employeeList)
        {
            Log.d("array",c.id+"號,姓名:"+c.name+",職位:"+c.position);
            if(c.position.equals("助手"))
            {
                apprelist.add(c.name);
                Log.d("driverlist",apprelist.toString());
            }
        }

        final String driver[] = (String[]) apprelist.toArray(new String[0]);

        tmp=ch;

        dia.setSingleChoiceItems(driver, ch, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tmp=i;
            }
        });
        dia.setPositiveButton("送出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ch=tmp;
                tvapprentice.setText(driver[ch]);
            }
        });
        dia.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvapprentice.setText("助手");
            }
        });

        dia.show();
    }

    public void clickCar(View v)
    {
        AlertDialog.Builder dia=new AlertDialog.Builder(DispatchActivity.this);
        dia.setTitle("車子列表");

        final ArrayList<String> carlist =new ArrayList<>();

        for(Crane c: MainActivity.dao.cranelist)
        {
            Log.d("array",c.id+"號,姓名:"+c.name+","+c.maxLift);

            carlist.add("噸數:"+c.maxLift+"車牌:"+c.name);
            Log.d("driverlist",carlist.toString());

        }

        final String driver[] = (String[]) carlist.toArray(new String[0]);

        tmp=ch;

        dia.setSingleChoiceItems(driver, ch, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tmp=i;
            }
        });
        dia.setPositiveButton("送出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ch=tmp;
                tvcar.setText(driver[ch]);
            }
        });
        dia.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvcar.setText("車種");
            }
        });

        dia.show();
    }

    public void clickTime(View v)
    {
        Intent it =new Intent(DispatchActivity.this,DatepickActivity.class);
        btime=true;
        startActivityForResult(it,time);
    }

    public void clickeTime(View v)
    {
        Intent it =new Intent(DispatchActivity.this,DatepickActivity.class);
        betime=true;
        startActivityForResult(it,etime);
    }

    public  void clickLocation(View v)
    {
        Intent it =new Intent(DispatchActivity.this,LocationActivity.class);
        bloc=true;
        startActivityForResult(it,location);
    }

    public  void clickConsumer(View v)
    {
        Intent it =new Intent(DispatchActivity.this,LocationActivity.class);
        bconsumer=true;
        startActivityForResult(it,consumer);
    }

    public  void clickContel(View v)
    {
        Intent it =new Intent(DispatchActivity.this,LocationActivity.class);
        bcontel=true;
        startActivityForResult(it,contel);
    }

    public  void clickNote(View v)
    {
        Intent it =new Intent(DispatchActivity.this,LocationActivity.class);
        bnote=true;
        startActivityForResult(it,note);
    }

    public void clickSubmit(View v)
    {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提交工單");
        builder.setMessage("工單編號:"+Integer.valueOf(tvid.getText().toString())+"\n"
        +"起訖時間:"+tvtime.getText().toString()+"\n"
        +"~"+tvetime.getText().toString()+"\n"
        +"工作地點:"+tvlocation.getText().toString()+"\n"
        +"客戶:"+tvconsumer.getText().toString()+" "+tvcontel.getText().toString()+"\n"
        +"出車:"+tvdriver.getText().toString()+"\n"
        +"隨車司機/助手:"+tvdriver.getText().toString()+"/"+tvapprentice.getText().toString()+"\n"
        +"備註:"+tvnote.getText().toString()+"\n"
        +"確認送出?"
        );
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //以後要在此送出資料去fireBase
                if (tvdriver.getText().equals("助手"))
                {
                    MainActivity.dao.addwork(new DispatchList(Integer.valueOf(tvid.getText().toString()),
                            tvtime.getText().toString(),
                            tvetime.getText().toString(),
                            tvlocation.getText().toString(),
                            tvconsumer.getText().toString(),
                            tvcontel.getText().toString(),
                            tvdriver.getText().toString(),
                            tvcar.getText().toString(),
                            tvapprentice.getText().toString(),
                            tvnote.getText().toString(),
                            sending,admiting,sending));
                }
                else {
                    MainActivity.dao.addwork(new DispatchList(Integer.valueOf(tvid.getText().toString()),
                            tvtime.getText().toString(),
                            tvetime.getText().toString(),
                            tvlocation.getText().toString(),
                            tvconsumer.getText().toString(),
                            tvcontel.getText().toString(),
                            tvdriver.getText().toString(),
                            tvcar.getText().toString(),
                            tvapprentice.getText().toString(),
                            tvnote.getText().toString(),
                            sending,sending,sending));
                }

                finish();


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
