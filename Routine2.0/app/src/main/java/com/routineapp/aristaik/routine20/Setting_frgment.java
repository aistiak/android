package com.routineapp.aristaik.routine20;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;


public class Setting_frgment extends Fragment {

    // view objects
    Spinner dept_spinner, sem_spinner;
    ArrayAdapter<CharSequence> dept_adapter, sem_adapter;
    Button go_button;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Setting_frgment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Setting_frgment.
     */
    // TODO: Rename and change types and number of parameters
    public static Setting_frgment newInstance(String param1, String param2) {
        Setting_frgment fragment = new Setting_frgment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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



        View v = inflater.inflate(R.layout.fragment_setting_frgment, container, false);


        go_button = (Button) v.findViewById(R.id.setting_fragment_button_id);
        dept_spinner = (Spinner) v.findViewById(R.id.dept_spinner);
        sem_spinner = (Spinner) v.findViewById(R.id.sem_spinner);

        dept_adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext()
                , R.array.department_names, android.R.layout.simple_list_item_1);
        sem_adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext()
                , R.array.semester_no, android.R.layout.simple_list_item_1);

        dept_spinner.setAdapter(dept_adapter);
        sem_spinner.setAdapter(sem_adapter);



        if(!FileHelper.readFromFile("data.txt",getActivity().getBaseContext()).equals("")) {
            String dept_name = FindString(FileHelper.readFromFile("data.txt", getActivity().getBaseContext())).toLowerCase();
            String sem_no = FindNumber(FileHelper.readFromFile("data.txt", getActivity().getBaseContext()));
            int pos1, pos2;
            pos1 = dept_adapter.getPosition(dept_name.toUpperCase());
            dept_spinner.setSelection(pos1);
            pos2 = sem_adapter.getPosition(sem_no);
            sem_spinner.setSelection(pos2);
        }

        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* writing the selected department and semester to file */
                String temp = dept_spinner.getSelectedItem().toString() + sem_spinner.getSelectedItem().toString();

                FileHelper.writeToFile(temp, "data.txt", getActivity().getBaseContext());

                /*  */

                /*-------------------------------------------equivalent of showToday() method--------------------------*/

                String dept_name = FindString(FileHelper.readFromFile("data.txt",getActivity().getBaseContext())).toLowerCase();
                String sem_no = FindNumber(FileHelper.readFromFile("data.txt",getActivity().getBaseContext())) ;

                Log.d("arif","dept = " + dept_name + " sem no = "+ sem_no);

                // reading data from xml file and saving to a string
                String data = FileHelper.readFromFile("data.xml", getActivity().getBaseContext());
                // parsing the xml string data
                ParserClass parserObject = new ParserClass(data);

                ArrayList[] mArrayList = null;
                try {
                    // converting the parsed xml data to  a ArrayList[]
                    mArrayList = parserObject.getData(dept_name,sem_no);
                    if (mArrayList == null) Log.d("arif", "sending null array list ");
                } catch (Exception e) {
                    Log.d("arif", e.toString());
                }
                if (mArrayList != null) {
                    Log.d("arif", "sending array of length" + mArrayList.length);
                }
                Log.d("arif", "list recived");


                /* sending the arrayList to Day via a Pager fragment class (PagerFragment)*/
                PagerFragment fragment = PagerFragment.newInstance(mArrayList);

                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.frame, fragment, fragment.getTag())
                        .commit();

                /*_______________________________________________________________________________*/


            }
        });

        return v;
    }


    public static void showToday(Context context, FragmentManager manager) {

        /*get dept name and sem no from data.txt*/
        String dept_name = FindString(FileHelper.readFromFile("data.txt",context)).toLowerCase();
        String sem_no = FindNumber(FileHelper.readFromFile("data.txt",context));
        Log.d("arif","dept = " + dept_name + " sem no = "+ sem_no);
        // reading data from xml file and saving to a string
        String data = FileHelper.readFromFile("data.xml", context);
        // parsing the xml string data
        ParserClass parserObject = new ParserClass(data);

        ArrayList[] mArrayList = null;
        try {
            // converting the parsed xml data to  a ArrayList[]
            mArrayList = parserObject.getData(dept_name, sem_no);
            if (mArrayList == null) Log.d("arif", "sending null array list ");
        } catch (Exception e) {
            Log.d("arif", e.toString());
        }
        if (mArrayList != null) {
            Log.d("arif", "sending array of length" + mArrayList.length);
        }
        Log.d("arif", "list recived");


                /* sending the arrayList to Day via a Pager fragment class (PagerFragment)*/
        PagerFragment fragment = PagerFragment.newInstance(mArrayList);

    /*    FragmentManager manager = getFragmentManager();*/
        manager.beginTransaction()
                .replace(R.id.frame, fragment, fragment.getTag())
                .commit();

    }


    public static String FindNumber(String s){
         String r = "" ;

         for(int i = 0 ; i< s.length() ;i++){
             if(Character.isDigit(s.charAt(i))) r+=s.charAt(i) ;
         }


         return r ;

    }
    public static String FindString(String s){
        String r = "" ;

        for(int i = 0 ; i< s.length() ;i++){
            if(!Character.isDigit(s.charAt(i))) r+=s.charAt(i) ;
        }


        return r ;

    }


}
