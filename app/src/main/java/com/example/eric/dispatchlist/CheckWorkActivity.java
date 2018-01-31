package com.example.eric.dispatchlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        adapter_checkwork adapter = new adapter_checkwork(CheckWorkActivity.this,filter);
        lv.setAdapter(adapter);// 根据RadioButton的选择情况
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//如果不使用这个设置，选项中的radiobutton无法响应选中事件
        // http://blog.csdn.net/xiaohei5188/article/details/43225525

    }

    public void clickcheck(View v)
    {

    }
}
