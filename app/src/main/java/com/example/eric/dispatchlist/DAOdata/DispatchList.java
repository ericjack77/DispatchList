package com.example.eric.dispatchlist.DAOdata;

/**
 * Created by Student on 2018/1/18.
 */

public class DispatchList
{
    public int id;
    public String time;
    public String location;
    public String consumer;
    public String contel;
    public String driver;    //from Employee.name
    public String car;       //from Crane.name
    public String aprentice; //from Employee.name
    public String note;
    public DispatchList(int id,String time,String location,String consumer,String contel,String driver,String car,String aprentice,String note)
    {
        this.id=id;this.time=time;this.location=location;this.consumer=consumer;this.contel=contel;this.driver=driver;this.car=car;this.aprentice=aprentice;this.note=note;
    }





}


