package com.example.eric.dispatchlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.eric.dispatchlist.Adapter.adapter_checkwork;
import com.example.eric.dispatchlist.DAOdata.DispatchList;

import java.util.ArrayList;

public class CheckWorkActivity extends AppCompatActivity {
    ListView lv;
    boolean cks[];
    ArrayList<DispatchList> filter = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_work);
        lv = findViewById(R.id.listView);

        for (DispatchList d:MainActivity.dao.dispatchLists)
        {
            if (MainActivity.map3.get(MainActivity.user).equals(d.driver) ||
                    MainActivity.map3.get(MainActivity.user).equals(d.apprentice) )
            {
                filter.add(d);
            }
        }

        adapter_checkwork adapter = new adapter_checkwork(CheckWorkActivity.this,filter,cks);
        lv.setAdapter(adapter);
    }

    public void clickcheck(View v)
    {

    }
}
