package com.example.eric.dispatchlist.DAOdata;

/**
 * Created by Student on 2018/1/18.
 */

public class WorkState extends DispatchList
{
    public String driverState;
    public String aprenticeState;

    public WorkState(int id,String time,String driver,String driverState,String aprentice,String aprenticeState,String car,
                     String location,String consumer,String contel,String note)
    {
        super(id,time, location, consumer, contel, driver, car, aprentice, note);
        this.driverState=driverState;this.aprenticeState=aprenticeState;
    }




}
