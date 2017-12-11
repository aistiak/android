package com.routineapp.aristaik.routine20;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


public class Day extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    // TODO: Rename and change types of parameters

    TextView mTextView ;
    ArrayList<Period> mArrayList ;


    public Day() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Day newInstance(ArrayList<Period> arrayList, int dayOfWeek) {
        Day fragment = new Day();
        Bundle args = new Bundle();
        args.putSerializable("dayOfWeek",dayOfWeek);
        args.putSerializable("list",arrayList);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.day_fragmet_layout, container, false);

        mTextView = (TextView) v.findViewById(R.id.day_text_view_id);
        String str = " " ;

        /*setting the day*/
        int dayOfWeek = (int)getArguments().getSerializable("dayOfWeek") ;
        if(dayOfWeek == 7) dayOfWeek = 0;
        if(dayOfWeek == 0) str = "SAT \n\n";
        if(dayOfWeek == 1) str = "SUN \n\n";
        if(dayOfWeek == 2) str = "NOM \n\n";
        if(dayOfWeek == 3) str = "TUE \n\n";
        if(dayOfWeek == 4) str = "WED \n\n";


        try {

            mArrayList = (ArrayList<Period>) getArguments().getSerializable("list");

            for (int i = 0; i < mArrayList.size(); i++) {
                Period p = mArrayList.get(i);
                str += "perdiod " + i + " : \n";
                str += p.getSub_name() + "   ";
                str += p.getCourse_code() + "\n\n";
                str += p.getTeacher_name() + "\n\n";
                str += p.getStart_time() + "   ";
                str += p.getEnd_time() + "\n\n\n";
            }
            mTextView.setText(str);

        }catch (Exception e){

            mTextView.setText("NO CLASS");
        }

        return  v ;
    }


}
