package com.example.aristaik.ci031;

import android.content.Context;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by AR Istaik on 10/22/2017.
 */

public class CrimeLab  {

    private ArrayList<Crime> mCrimes ;

    private Context appContext ;

    private static CrimeLab sCrimeLab ;


    public void addCrime(Crime c){
           mCrimes.add(c);
    }
    private CrimeLab(Context mContext){
        appContext = mContext ;
        mCrimes = new ArrayList<Crime>() ;
      /*  for (int i = 1 ; i<100 ; i++){
             Crime c = new Crime() ;
             c.setTitle("Crime #"+i);
             c.setSolved(i % 2 == 0);
             mCrimes.add(c);

        }*/
    }


    public static CrimeLab get(Context c){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }

        return  sCrimeLab ;
    }


    public ArrayList<Crime> getCrime (){return  mCrimes;}

    public Crime getCrime(UUID id ){
           for(Crime c :mCrimes){
               if(c.getID().equals(id)){
                   return  c;
               }
           }

           return  null ;
    }
}
