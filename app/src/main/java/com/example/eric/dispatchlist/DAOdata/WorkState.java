package com.example.eric.dispatchlist.DAOdata;

/**
 * Created by Student on 2018/1/18.
 */

public class WorkState extends DispatchList
{
    public String driverState;
    public String apprenticeState;

    public WorkState(int id,String stime,String etime,String driver,String driverState,String apprentice,String apprenticeState,String car,
                     String location,String consumer,String contel,String note)
    {
        super(id,stime,etime, location, consumer, contel, driver, car, apprentice, note);
        this.driverState=driverState;this.apprenticeState=apprenticeState;
    }




}
