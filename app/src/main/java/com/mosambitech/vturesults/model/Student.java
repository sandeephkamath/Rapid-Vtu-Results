package com.mosambitech.vturesults.model;

/**
 * Created by User1 on 8/1/2015.
 */
public class Student {
    private String mName;
    private String mUSN;
    private int mId;
    private Result mResult;
    private int mAggregate;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUSN() {
        return mUSN;
    }

    public void setUSN(String USN) {
        mUSN = USN;
    }

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        this.mResult = result;
    }

    public int getAggregate() {
        return mAggregate;
    }

    public void setAggregate(int aggregate) {
        this.mAggregate = aggregate;
    }
}
