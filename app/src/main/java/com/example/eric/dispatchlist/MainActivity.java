package com.example.eric.dispatchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static DispatchDAO dao;

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dao =new DispatchDAO("Crane");
        dao =new DispatchDAO("employeeList");
        dao =new DispatchDAO("dispatchLists");


        database = FirebaseDatabase.getInstance(); //建立連結


        dlCar();  //下載 車子資料
        dlEmployee(); //下載  人事資料
        dlDispatch(); //下載 派工資料


    }

    public void clickLogin(View v)
    {
        Intent it =new Intent(MainActivity.this,EmployerActivity.class);
        startActivity(it);
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



}
