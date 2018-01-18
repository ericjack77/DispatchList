package com.example.eric.dispatchlist.DAOdata;

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

    public DispatchDAO(String ob)
    {
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

    public boolean addwork(DispatchList s)
    {
        dispatchLists.add(s);
        return true;
    }

    public ArrayList<DispatchList> getworklist()
    {
        return dispatchLists;
    }

    public DispatchList get(int id)
    {
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
