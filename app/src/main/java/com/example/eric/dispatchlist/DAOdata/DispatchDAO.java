package com.example.eric.dispatchlist.DAOdata;

import android.content.Context;

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
    public ArrayList<WorkState> workstateList;
    Context context;

    public DispatchDAO(String ob)
    {
        this.context=context;
        switch (ob)
        {
            case "dispatchLists":
                dispatchLists = new ArrayList<>();
                break;
            case "workstateList":
                workstateList = new ArrayList<>();
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
        File f =new File(context.getFilesDir(),"dispatchList.txt");
        FileWriter fw =null;
        try {
            fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(dispatchLists);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load()
    {
        File f =new File(context.getFilesDir(),"dispatchList.txt");
        FileReader fr = null;
        try {
            fr= new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            dispatchLists = gson.fromJson(str,new TypeToken<ArrayList<DispatchList>>(){}.getType());
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean addwork(DispatchList s)
    {
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
