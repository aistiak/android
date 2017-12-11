package com.example.aristaik.ci031;

import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;

public class CrimeListActivity extends SingleFragmentActivity {



    @Override
    protected Fragment createFragmetn() {
        Log.d("flag1", "crime list activity ");
        CrimeListFragment clf = new CrimeListFragment();
        return  clf ;
    }
}
