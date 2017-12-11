package com.example.aristaik.ci031;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

/**
 * Created by AR Istaik on 10/22/2017.
 */

public class CrimeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragmetn() {
        Log.i("flag1", "crime activity ");
        UUID crimeId = (UUID)getIntent()
                .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID); // FETCHES DATA FROM THIS FRAGMENTS HOSTING ACTIVITY -> ARIF COMMENT
        return CrimeFragment.newInstance(crimeId);
      //  return  new CrimeFragment();

    }
}
