package com.mosambitech.vturesults.model;

import java.util.ArrayList;

/**
 * Created by User1 on 8/1/2015.
 */
public class Sem{
    private String mSemResult;
    private float mSemPercent;
    private int mSemTotal;
    private int mSemester;

    private ArrayList<Subject> mSubjects=new ArrayList<Subject>();

    public int getSemester() {
        return mSemester;
    }

    public Sem(int semester) {
        mSemester = semester;
    }

    public String getSemResult() {
        return mSemResult;
    }

    public void setSemResult(String semResult) {
        mSemResult = semResult;
    }

    public float getSemPercent() {
        return mSemPercent;
    }

    public void setSemPercent(float semPercent) {
        mSemPercent = semPercent;
    }

    public int getSemTotal() {
        return mSemTotal;
    }

    public void setSemTotal(int semTotal) {
        mSemTotal = semTotal;
    }

    public void addSubject(Subject subject) {
        mSubjects.add(subject);
    }

    public int getNumberOfSubjects() {
        return mSubjects.size();
    }

    public ArrayList<Subject> getAllSubjects() {
        return mSubjects;
    }

}
