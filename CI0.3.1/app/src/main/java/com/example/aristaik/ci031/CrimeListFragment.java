package com.example.aristaik.ci031;


import java.util.ArrayList;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.ListFragment;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by AR Istaik on 10/22/2017.
 */

public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes;


    @Override
    public void onResume() {
        super.onResume();
        updateUI();

    }

    public void updateUI() {

        ((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
    }

    private void updateSubtitle() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrime().size();
        String subtitle = getString(R.string.subtitle_fromat, crimeCount);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity
                        .newIntent(getActivity(), crime.getID());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                updateSubtitle();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("flag1", "crime list fragment  ");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.crime_hint);
        mCrimes = CrimeLab.get(getActivity()).getCrime();
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }


    public void onListItemClick(ListView l, View v, int position, long id) {
       /* Log.i("flag1", "item pressed  ");
        //Toast.makeText(getActivity(),"clciked",Toast.LENGTH_SHORT).show();
               Crime c = (Crime) getListAdapter().getItem(position); // not_same
               Intent i = new Intent(getActivity(),CrimeActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getID());
        Log.i("flag1", "extra data on place  ");
       // Toast.makeText(getActivity(),"pressed",Toast.LENGTH_LONG).show();
        //startActivity(i);
         startActivityForResult(i, 0);*/
        Crime c = (Crime) getListAdapter().getItem(position);
        Intent intent = CrimePagerActivity.newIntent(getActivity(), c.getID());
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("flag1", "result recived  ");
        ((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
    }


    public class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), android.R.layout.simple_list_item_1, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }


            // configure the view for this Crime
            Crime c = getItem(position);

            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());
            CheckBox solvedCheckBox =
                    (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }


    }
}
