package com.tama.roadtrip;

public class SearchItem {
    private String mClock;
    private String mPrice;
    private String mUserID;
    private String mRideID;

    public SearchItem(String clock, String price, String userID, String rideID) {
        this.mClock = clock;
        this.mPrice = price;
        this.mUserID = userID;
        this.mRideID = rideID;
    }

    public String getClock() {
        return mClock;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getUserID() { return mUserID; }

    public String getmRideID() { return mRideID; }
}
