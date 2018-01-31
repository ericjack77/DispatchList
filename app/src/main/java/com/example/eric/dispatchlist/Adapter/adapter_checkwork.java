package com.example.eric.dispatchlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.eric.dispatchlist.R;

/**
 * Created by Eric on 2018/1/25.
 */

public class adapter_checkwork extends BaseAdapter {
        Context context;
//    public adapter_checkwork(Context context)
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v1, ViewGroup parent) {
        viewholder vh;
        if(v1 == null)
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
        return null;
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
