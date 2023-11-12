package com.example.lab4;

import java.util.Date;
import java.util.UUID;

public class Inventory {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    private int mIndex;
    public Inventory(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    public UUID getmId() {
        return mId;
    }
    public String getmTitle() {
        return mTitle;
    }
    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public Date getmDate() {
        return mDate;
    }
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
    public boolean ismSolved() {
        return mSolved;
    }
    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
}