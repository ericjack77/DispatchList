package com.example.eric.dispatchlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.eric.dispatchlist.DAOdata.Crane;
import com.example.eric.dispatchlist.DAOdata.DispatchDAO;
import com.example.eric.dispatchlist.DAOdata.DispatchList;
import com.example.eric.dispatchlist.DAOdata.Employee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    public static DispatchDAO dao;
    EditText etuser,etpass;
    FirebaseDatabase database;
    DatabaseReference myRef;
    HashMap<String,String> mapuser,map,map2;
    //map 帳號對密碼 //map2 帳號對職位 //mapuser 自己的帳號密碼
    public static HashMap<String,String> map3;
    //map3 帳號對名字
    public static String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etuser =findViewById(R.id.etuser);
        etpass = findViewById(R.id.etpass);
        mapuser = new HashMap<>();
        dao =new DispatchDAO("Crane");
        dao =new DispatchDAO("employeeList");
        dao =new DispatchDAO("dispatchLists");


        database = FirebaseDatabase.getInstance(); //建立連結
        loadFile(); //下載 自己的帳號密碼資料
        if (mapuser.isEmpty() == false)
        {
            for(String key : mapuser.keySet()){
                String value = mapuser.get(key);
                Log.d("帳號=",key+",密碼="+value);
                etuser.setText(key);
                etpass.setText(value);
            }

        }

        dlCar();  //下載 車子資料
        dlEmployee(); //下載  人事資料
        dlDispatch(); //下載 派工資料

    }



    public void clickLogin(final View v)
    {

        if (dao.employeeList == null)
        {
             Toast.makeText(this, "系統讀取中,請稍後再按一次\n或者檢查您的網路連線", Toast.LENGTH_SHORT).show();
        }
        else {
            map = new HashMap<>();  //抓帳號密碼資料庫 //帳號對密碼
            map2 = new HashMap<>(); //帳號對職位
            map3 = new HashMap<>(); //帳號對名字
            for (Employee e:dao.employeeList)
            {
                map.put(e.username,e.password);
                Log.d("帳號",e.username+",密碼"+e.password+",名字"+e.name);
                map2.put(e.username,e.position);

                map3.put(e.username,e.name);
            }

            user = etuser.getText().toString();
            String pass=  etpass.getText().toString();
            if (map.containsKey(user) && (map.get(user).equals(pass)) && (map2.get(user).equals("管理"))) //做分流
            {
                savaFile();
                Intent it =new Intent(MainActivity.this,EmployerActivity.class);
                startActivity(it);
            }
            else if (map.containsKey(user) && (map.get(user).equals(pass)) && (map2.get(user).equals("司機") || map2.get(user).equals("助手")))
            {
                savaFile();
                Intent it =new Intent(MainActivity.this,EmployeeActivity.class);
                startActivity(it);
            }
            else
            {
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setTitle("錯誤");
                builder.setMessage("帳號或密碼錯誤,請再重新輸入一次"+"\n"
                        +"帳號為身份證字號首字為大寫");
                builder.setPositiveButton("重新輸入", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }

        }

    }

    public void dlDispatch()
    {
        myRef = database.getReference("dispatchlist");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
//                Log.d("firebase", "Value is: " + value);

                Gson gson =new Gson();
                dao.dispatchLists =gson.fromJson(value,new TypeToken<ArrayList<DispatchList>>(){}.getType());
                if(dao.dispatchLists== null)
                {
                    dao.dispatchLists = new ArrayList<>();
                }
                Log.d("data",dao.dispatchLists.toString());
                for(DispatchList c: dao.dispatchLists)
                {
                    Log.d("array","工單編號:"+c.id+",地點:"+c.location+",時間"+c.stime+"~"+c.etime+",車種"+c.car);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });
    }
    public  void dlCar()
    {
        myRef = database.getReference("craneList");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
//                Log.d("firebase", "Value is: " + value);

                Gson gson =new Gson();
//                ArrayList<Crane> data=gson.fromJson(value,new TypeToken<ArrayList<Crane>>(){}.getType());
                dao.cranelist =gson.fromJson(value,new TypeToken<ArrayList<Crane>>(){}.getType());
                Log.d("data",dao.cranelist.toString());
                for(Crane c: dao.cranelist)
                {
                    Log.d("array",c.id+"車牌:"+c.name+",噸數"+c.maxLift);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });
    }


    public void  dlEmployee()
    {
        myRef = database.getReference("employeeList");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                Log.d("firebase", "Value is: " + value);

                Gson gson =new Gson();
                dao.employeeList =gson.fromJson(value,new TypeToken<ArrayList<Employee>>(){}.getType());
                Log.d("data",dao.employeeList.toString());
                for(Employee c: dao.employeeList)
                {
                    Log.d("array",c.id+"號,姓名:"+c.name+",職位:"+c.position);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });
    }

    public  void savaFile()
    {
        mapuser.clear();
        mapuser.put(etuser.getText().toString(),etpass.getText().toString());
        File f =new File(getFilesDir(),"user.txt");
        FileWriter fw =null;
        try {
            fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mapuser);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile()
    {
        File f =new File(getFilesDir(),"user.txt");
        FileReader fr = null;
        try {
            fr= new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            mapuser = gson.fromJson(str,new TypeToken<HashMap<String,String>>(){}.getType());
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
