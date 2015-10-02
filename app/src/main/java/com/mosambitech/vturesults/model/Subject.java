package com.mosambitech.vturesults.model;

/**
 * Created by User1 on 8/1/2015.
 */
public class Subject {
    private String mSubjectName;
    private String mSubjectCode;
    private int mInternalMark;
    private int mExternalMark;
    private int mSubjectTotal;
    private String mSubjectResult;

    public int getSubjectTotal() {
        return mSubjectTotal;
    }

    public void setSubjectTotal(int mSubjectTotal) {
        this.mSubjectTotal = mSubjectTotal;
    }

    public String getSubjectName() {
        return mSubjectName;
    }

    public void setSubjectName(String subjectName) {
        mSubjectName = subjectName;
    }

    public String getSubjectCode() {
        return mSubjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        mSubjectCode = subjectCode;
    }

    public int getInternalMarks() {
        return mInternalMark;
    }

    public void setInternalMarks(int internalMarks) {
        mInternalMark = internalMarks;
    }

    public int getExternalMarks() {
        return mExternalMark;
    }

    public void setExternalMarks(int externalMarks) {
        mExternalMark = externalMarks;
    }

    public String getSubjectResult() {
        return mSubjectResult;
    }

    public void setSubjectResult(String subjectResult) {
        mSubjectResult = subjectResult;
    }


}
