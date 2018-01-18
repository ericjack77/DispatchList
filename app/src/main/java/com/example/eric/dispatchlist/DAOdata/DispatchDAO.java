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


}
