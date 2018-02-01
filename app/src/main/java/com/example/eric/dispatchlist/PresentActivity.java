package com.example.eric.dispatchlist;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eric.dispatchlist.DAOdata.DispatchEnum;
import com.example.eric.dispatchlist.DAOdata.DispatchList;

import java.util.ArrayList;

public class PresentActivity extends AppCompatActivity{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//這裡做已簽收的ListView Adapter
            ListView lv =rootView.findViewById(R.id.frg_listview);
            ArrayList<String> admitted = new ArrayList<>();
            final ArrayList<Integer> admittedid = new ArrayList<>();
            ArrayList<String> notyet = new ArrayList<>();
            final ArrayList<Integer> notyetid = new ArrayList<>();
            for (DispatchList d:MainActivity.dao.dispatchLists)
            {
                if (d.driverstate == DispatchEnum.admiting && d.aprenticestate == DispatchEnum.admiting)
                {
                    admitted.add("地點:"+d.location+"人員:"+d.driver+"/"+d.apprentice);
                    admittedid.add(d.id);
                }
                else
                {
                    notyet.add("地點:"+d.location+"人員:"+d.driver+"/"+d.apprentice);
                    notyetid.add(d.id);
                }
            }

            switch (getArguments().getInt(ARG_SECTION_NUMBER))
            {
                case 1:
                    ArrayAdapter adapter = new ArrayAdapter(rootView.getContext(),
                            android.R.layout.simple_list_item_1,admitted);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());
                            builder.setTitle("查看詳細資料");
                            builder.setMessage("工單編號:"+MainActivity.dao.get(admittedid.get(i)).id+"\n"
                                    +"起訖時間:"+MainActivity.dao.get(admittedid.get(i)).stime+"\n"
                                    +"~"+MainActivity.dao.get(admittedid.get(i)).etime+"\n"
                                    +"工作地點:"+MainActivity.dao.get(admittedid.get(i)).location+"\n"
                                    +"客戶:"+MainActivity.dao.get(admittedid.get(i)).consumer+" "+MainActivity.dao.get(admittedid.get(i)).contel+"\n"
                                    +"出車:"+MainActivity.dao.get(admittedid.get(i)).car+"\n"
                                    +"隨車司機/助手:"+MainActivity.dao.get(admittedid.get(i)).driver+"/"+MainActivity.dao.get(admittedid.get(i)).apprentice+"\n"
                                    +"備註:"+MainActivity.dao.get(admittedid.get(i)).note+"\n"
                                    );
                            builder.setPositiveButton("關閉", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                        }
                    });
                    break;
                case 2:
                    ArrayAdapter adapter2 = new ArrayAdapter(rootView.getContext(),
                            android.R.layout.simple_list_item_1,notyet);
                    lv.setAdapter(adapter2);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());
                            builder.setTitle("查看詳細資料");
                            builder.setMessage("工單編號:"+MainActivity.dao.get(notyetid.get(i)).id+"\n"
                                    +"起訖時間:"+MainActivity.dao.get(notyetid.get(i)).stime+"\n"
                                    +"~"+MainActivity.dao.get(notyetid.get(i)).etime+"\n"
                                    +"工作地點:"+MainActivity.dao.get(notyetid.get(i)).location+"\n"
                                    +"客戶:"+MainActivity.dao.get(notyetid.get(i)).consumer+" "+MainActivity.dao.get(notyetid.get(i)).contel+"\n"
                                    +"出車:"+MainActivity.dao.get(notyetid.get(i)).car+"\n"
                                    +"隨車司機/狀態:"+MainActivity.dao.get(notyetid.get(i)).driver+MainActivity.dao.get(notyetid.get(i)).driverstate+"\n"
                                    +"隨車助手/狀態:"+MainActivity.dao.get(notyetid.get(i)).apprentice+MainActivity.dao.get(notyetid.get(i)).aprenticestate+"\n"
                                    +"備註:"+MainActivity.dao.get(notyetid.get(i)).note+"\n"
                            );
                            builder.setPositiveButton("關閉", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                        }
                    });
                    break;
            }


            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

                return PlaceholderFragment.newInstance(position + 1);

        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}

