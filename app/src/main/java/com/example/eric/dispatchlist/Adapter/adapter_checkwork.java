package com.example.eric.dispatchlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.eric.dispatchlist.DAOdata.DispatchList;
import com.example.eric.dispatchlist.R;

import java.util.ArrayList;

/**
 * Created by Eric on 2018/1/25.
 */

public class adapter_checkwork extends BaseAdapter {
    Context context;
    ArrayList<DispatchList> filter = new ArrayList<>();
    boolean cks[];
    public adapter_checkwork(Context context,ArrayList<DispatchList> filter , boolean cks[])
    {
        this.context=context;this.filter=filter;
        this.cks=cks;
    }
    @Override
    public int getCount() {
        return filter.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override //用getview抓的資料  每次出現在畫面都資料都要重新抓取
    public View getView(int position, View v1, ViewGroup parent) {
        viewholder vh;
        if(v1 == null) //擺設
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            v1 = inflater.inflate(R.layout.checkworklist,null);
            vh = new viewholder();
            //變動的
            vh.ctvid=v1.findViewById(R.id.ctvid);
            vh.ctvstime=v1.findViewById(R.id.ctvstime);
            vh.ctvetime=v1.findViewById(R.id.ctvetime);
            vh.ctvloc=v1.findViewById(R.id.ctvloc);
            vh.ctvemp=v1.findViewById(R.id.ctvemp);
            vh.ctvcar=v1.findViewById(R.id.ctvcar);
            vh.ctvcon=v1.findViewById(R.id.ctvcon);
            vh.ctvnote=v1.findViewById(R.id.ctvnote);
            vh.rb=v1.findViewById(R.id.rb);

            //固定的
            vh.ctvstime2=v1.findViewById(R.id.ctvstime2);
            vh.ctvetime2=v1.findViewById(R.id.ctvetime2);
            vh.ctvloc2=v1.findViewById(R.id.ctvloc2);
            vh.ctvemp2=v1.findViewById(R.id.ctvemp2);
            vh.ctvcar2=v1.findViewById(R.id.ctvcar2);
            vh.ctvcon2=v1.findViewById(R.id.ctvcon2);
            vh.ctvnote2=v1.findViewById(R.id.ctvnote2);
            vh.ctv2=v1.findViewById(R.id.ctv2);

            v1.setTag(vh);

        }
        else
        {
            vh = (viewholder) v1.getTag();
        }

        //settext
        vh.ctvid.setText(filter.get(position).id);
        vh.ctvstime.setText(filter.get(position).stime);
        vh.ctvetime.setText(filter.get(position).etime);
        vh.ctvloc.setText(filter.get(position).location);
        if (filter.get(position).apprentice.equals("助手"))
        {
            vh.ctvemp.setText(filter.get(position).driver);
        }
        else {
            vh.ctvemp.setText(filter.get(position).driver+"/"+filter.get(position).apprentice);
        }
        vh.ctvcar.setText(filter.get(position).car);
        vh.ctvcon.setText(filter.get(position).consumer+"/"+filter.get(position).contel);
        vh.ctvnote.setText(filter.get(position).note);

        //rb 監聽


        return v1;
    }

    static class viewholder
    {
        RadioButton rb;
        //變數
        TextView ctvid,ctvstime,ctvetime,ctvloc,ctvemp,ctvcar,ctvcon,ctvnote;
        //固定
        TextView ctvstime2,ctvetime2,ctvloc2,ctvemp2,ctvcar2,ctvcon2,ctvnote2,ctv2;
    }
}
