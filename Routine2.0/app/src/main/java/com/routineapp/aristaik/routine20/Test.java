package com.routineapp.aristaik.routine20;

import java.util.*;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Test extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Period> mArrayList;

    public Test() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Test newInstance(ArrayList<Period> arrayList) {
        Log.d("arif", "instace of Test is created ");
        Test fragment = new Test();
        Bundle args = new Bundle();
        args.putSerializable("list", arrayList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("arif", "on create method of test ");
        super.onCreate(savedInstanceState);
       /* try {

            getActivity().setTitle(getArguments().getSerializable("day").toString());
        } catch (Exception e) {
        }*/
        try {
            mArrayList = (ArrayList<Period>) getArguments().getSerializable("list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        PeriodAdapter adapter = new PeriodAdapter(mArrayList);

        if (mArrayList == null) Log.d("arif", " arrry list is null ");
        Log.d("arif", "adapter has been set ");
        setListAdapter(adapter);
    }


    public class PeriodAdapter extends ArrayAdapter<Period> {

        public PeriodAdapter(ArrayList<Period> arrayList) {
            super(getActivity(), android.R.layout.simple_list_item_1, arrayList);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.fragment_test, null);
            }

            String str = " " ;
            int dayOfWeek = position ;// (int)getArguments().getSerializable("dayOfWeek") ;
            if(dayOfWeek == 7) dayOfWeek = 0;
            if(dayOfWeek == 0) str = "SAT \n\n";
            if(dayOfWeek == 1) str = "SUN \n\n";
            if(dayOfWeek == 2) str = "NOM \n\n";
            if(dayOfWeek == 3) str = "TUE \n\n";
            if(dayOfWeek == 4) str = "WED \n\n";
            Log.d("arif" ,"position :"+ String.valueOf(position));
            getActivity().setTitle(str);

            Period p = getItem(position);

            try {
                //TextView current_day = (TextView) convertView.findViewById(R.id.day_id);
                // current_day.setText(getArguments().getSerializable("day").toString());


                TextView sub_name = (TextView) convertView.findViewById(R.id.sub_name_id);
                sub_name.setText(p.getSub_name());

                TextView sub_code = (TextView) convertView.findViewById(R.id.sub_code_id);
                sub_code.setText(p.getCourse_code());

                TextView teacher_name = (TextView) convertView.findViewById(R.id.teacher_name_id);
                teacher_name.setText(p.getTeacher_name());

                TextView time = (TextView) convertView.findViewById(R.id.time_id);
                time.setText(p.getStart_time() + " to " + p.getEnd_time());

            }catch (Exception e){

                Log.d("arif","array null errro ");
            }
            return convertView;
        }
    }


}
