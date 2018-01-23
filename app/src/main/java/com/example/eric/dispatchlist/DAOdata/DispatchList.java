package com.example.eric.dispatchlist.DAOdata;

/**
 * Created by Student on 2018/1/18.
 */

public class DispatchList
{
    public int id;
    public String stime;
    public String etime;
    public String location;
    public String consumer;
    public String contel;
    public String driver;    //from Employee.name
    public String car;       //from Crane.name
    public String apprentice; //from Employee.name
    public String note;
    public DispatchList(int id,String stime,String etime,String location,String consumer,String contel,String driver,String car,String apprentice,String note)
    {
        this.id=id;this.stime=stime;this.etime=etime;this.location=location;this.consumer=consumer;this.contel=contel;this.driver=driver;this.car=car;this.apprentice=apprentice;this.note=note;

    }





}


