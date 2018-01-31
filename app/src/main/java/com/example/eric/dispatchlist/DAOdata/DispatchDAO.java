package com.example.eric.dispatchlist.DAOdata;

import android.content.Context;
import android.util.Log;

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

/**
 * Created by Student on 2018/1/18.
 */

public class DispatchDAO
{
    public ArrayList<Crane> cranelist;
    public ArrayList<Employee> employeeList;
    public ArrayList<DispatchList> dispatchLists;

    FirebaseDatabase database;
    DatabaseReference myRef;
    Context context;


    public DispatchDAO(String ob)
    {
        this.context=context;
        database = FirebaseDatabase.getInstance();
        switch (ob)
        {
            case "dispatchLists":
                dispatchLists = new ArrayList<>();
                break;
            case "Crane":
                cranelist = new ArrayList<>();
                break;
            case "employeeList":
                employeeList = new ArrayList<>();
                break;
        }
    }

    public void save()
    {
        myRef = database.getReference("dispatchlist");

        Gson gson = new Gson();
        String data = gson.toJson(dispatchLists);

        myRef.setValue(data);


    }

    public void load()
    {
        myRef = database.getReference("dispatchlist");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
//                Log.d("firebase", "Value is: " + value);
                Gson gson = new Gson();
                dispatchLists = gson.fromJson(value,new TypeToken<ArrayList<DispatchList>>(){}.getType());
                if (dispatchLists == null)
                {
                     dispatchLists = new ArrayList<>();
                }



                Log.d("data",dispatchLists.toString());
                for(DispatchList c: dispatchLists)
                {
                    Log.d("array",c.id+"時間:"+c.stime+"~"+c.etime+",地點"+c.location);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });



    }

    public boolean addwork(DispatchList s)
    {
        if (dispatchLists == null)
        {
            dispatchLists = new ArrayList<>();
        }
        dispatchLists.add(s);
        save();
        return true;
    }

    public ArrayList<DispatchList> getworklist()
    {
        load();
        return dispatchLists;
    }

    public DispatchList get(int id)
    {
        load();
        for(DispatchList d:dispatchLists)
        {
            if(d.id == id)
            {
                return d;
            }
        }
        return null;
    }

    public DispatchList get(String name)
    {
        load();
        for(DispatchList d:dispatchLists)
        {
            if(d.driver == name)
            {
                return d;
            }
            else if(d.apprentice == name)
            {
                return d;
            }
        }
        return null;
    }




}
