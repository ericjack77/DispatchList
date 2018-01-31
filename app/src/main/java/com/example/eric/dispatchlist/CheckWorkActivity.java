package com.example.eric.dispatchlist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.eric.dispatchlist.Adapter.adapter_checkwork;
import com.example.eric.dispatchlist.DAOdata.DispatchEnum;
import com.example.eric.dispatchlist.DAOdata.DispatchList;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckWorkActivity extends AppCompatActivity {
    ListView lv;

    ArrayList<DispatchList> filter = new ArrayList<>();
    HashMap<Integer, Boolean> states = new HashMap<Integer, Boolean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_work);
        lv = findViewById(R.id.listView);

        for (DispatchList d:MainActivity.dao.dispatchLists) //篩選所有同名的配進新陣列
        {
            if ((MainActivity.map3.get(MainActivity.user).equals(d.driver) &&
                    d.driverstate == DispatchEnum.sending) ||
                    (MainActivity.map3.get(MainActivity.user).equals(d.apprentice) &&
                    d.aprenticestate == DispatchEnum.sending))
            {
                filter.add(d);
            }

        }



        for (int i=0;i<filter.size();i++)
        {
            states.put(i,false);
        }
        for (int key : states.keySet()) {
            Log.d("onstates",",key="+key+",boolean="+states.get(key));
        }

        Log.d("filtersize","="+filter.size());

        adapter_checkwork adapter = new adapter_checkwork(CheckWorkActivity.this,filter,states);
        lv.setAdapter(adapter);// 根据RadioButton的选择情况
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//如果不使用这个设置，选项中的radiobutton无法响应选中事件
        // http://blog.csdn.net/xiaohei5188/article/details/43225525

    }

    public void clickcheck(View v)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("確認簽收");
        builder.setMessage("");
        builder.setPositiveButton("送出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                for (int key : states.keySet()) {
                    Log.d("states",",key="+key+",boolean="+states.get(key));
                }
                for (int i=0;i<filter.size();i++)
                {
                    if (states.get(i).equals(true) & MainActivity.map2.get(MainActivity.user).equals("司機"))
                    {
                            Log.d("你選擇的是","編號:"+filter.get(i).id+",地點:"+filter.get(i).location);
                            filter.get(i).driverstate = DispatchEnum.admiting;
                            MainActivity.dao.driveradmit(filter.get(i).id);
                    }
                    if (states.get(i).equals(true) & MainActivity.map2.get(MainActivity.user).equals("助手"))
                    {
                        filter.get(i).aprenticestate = DispatchEnum.admiting;
                        MainActivity.dao.apprenticeadmit(filter.get(i).id);
                    }
                }

                finish();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void clickdeny(View v)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("確認退回");
        builder.setMessage("");
        builder.setPositiveButton("致電告知原因", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
