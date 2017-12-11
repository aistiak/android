package com.example.aristaik.ci031;

import java.util.Date;
import java.util.UUID;

/**
 * Created by AR Istaik on 10/22/2017.
 */

public class Crime {
    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    private UUID mID ;
    private String mTitle ;
    private Date mDate ;
    private boolean isSolved ;

    public Crime(){

        mID = UUID.randomUUID();
        mDate = new Date();

    }

}
