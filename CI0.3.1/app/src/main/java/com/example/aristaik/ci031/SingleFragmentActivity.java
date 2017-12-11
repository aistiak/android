package com.example.aristaik.ci031;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by AR Istaik on 10/22/2017.
 */
abstract class SingleFragmentActivity extends AppCompatActivity{
/*abstract class SingleFragmentActivity extends FragmentActivity {*/

               protected abstract Fragment createFragmetn();

    @Override
    public void  onCreate(Bundle savedInstanceState){
        Log.i("flag1", "crime list activity ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);


        if(fragment == null){
            fragment = createFragmetn();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer,fragment)
                    .commit();
        }

    }
}
