package com.mosambitech.vturesults.model;

import java.util.ArrayList;

/**
 * Created by User1 on 8/2/2015.
 */
public class Result{
    private ArrayList<Sem> mSemList;


    public ArrayList<Sem> getSemList() {
        return mSemList;
    }

    public void setmSemList(ArrayList<Sem> semList) {
        mSemList = semList;
    }

    public int gotTotalSem() {
        return mSemList.size();
    }

}
