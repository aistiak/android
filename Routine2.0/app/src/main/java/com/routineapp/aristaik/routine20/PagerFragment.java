package com.routineapp.aristaik.routine20;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;


public class PagerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ViewPager mViewPager;
    private ArrayList<Period>[] mArrayList;


    public static PagerFragment newInstance(ArrayList<Period>[] list) {

        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putSerializable("list_send", list);
        fragment.setArguments(args);

        return fragment;
    }


    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.pager_fragment_layout, container, false);


        Log.d("arif", "pager fragment  created");


        try {  //  this thing was gives a preety mess
            mArrayList = (ArrayList<Period>[]) getArguments().getSerializable("list_send");
            Log.d("arif", "recived extra array list");
        } catch (Exception e) {

            Log.d("arif", "could not recived extra array list");
            Log.d("arif", "error :" + e.toString());
        }


        if (mArrayList == null) Log.d("arif", "array is null");
        mViewPager = (ViewPager) v.findViewById(R.id.pager_fragment_id);

        FragmentManager manager = getFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                ArrayList<Period> arrayList = mArrayList[position];
                //return Day.newInstance(arrayList,position);//SecondFragment.newInstance(arrayList);

                /*for testing perpouse*/

                if(arrayList == null){ //  if no list is found

                    return Day.newInstance(arrayList,position);
                }else { // if list is found and have to show list view classes
                    String str = " ";
                    int dayOfWeek = position;
                    if (dayOfWeek == 0) str = "SAT";
                    if (dayOfWeek == 1) str = "SUN";
                    if (dayOfWeek == 2) str = "NOM";
                    if (dayOfWeek == 3) str = "TUE";
                    if (dayOfWeek == 4) str = "WED ";
                    //getActivity().setTitle(str);
                    //Log.d("arif" ,"position :"+ String.valueOf(position));
                    return Test.newInstance(arrayList);
                }
            }

            @Override
            public int getCount() {

                return mArrayList.length;
            }
        });



        /** setting  current day*/

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == 7) dayOfWeek = 0;
        mViewPager.setCurrentItem(dayOfWeek);


        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event

}
