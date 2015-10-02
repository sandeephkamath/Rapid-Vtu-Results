package com.mosambitech.vturesults.JSONHandler;

import android.util.Log;

import com.mosambitech.vturesults.model.Result;
import com.mosambitech.vturesults.model.Sem;
import com.mosambitech.vturesults.model.Student;
import com.mosambitech.vturesults.model.Subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User1 on 8/2/2015.
 */
public class JSONHandler {

    public static String NAME = "name";
    public static String SEM = "sem";
    public static String SEM_TOTAL = "tmarks";
    public static String RESULT = "result";
    public static String PERCENTAGE = "percent";
    public static String SUBJECT_CODES = "scodes";
    public static String SUBJECT_NAMES = "snames";
    public static String SUBJECT_RESULT = "subres";
    public static String INTERNAL_MARKS = "inmarks";
    public static String EXTERNAL_MARKS = "exmarks";
    public static String SUBJECT_TOTAL = "subtm";
    public static String USN = "usn";

    public static Student getResult(JSONArray jsonArray) {
        ArrayList<Sem> semList = new ArrayList<Sem>();
        Student student = new Student();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                student.setName(jsonObject.getString(NAME));
                student.setUSN(jsonObject.getString(USN));

                String semString = jsonObject.getString(SEM).trim();
                String semTotalString = jsonObject.getString(SEM_TOTAL).trim();
                String semResult = jsonObject.getString(RESULT).trim();
                String semPercent = jsonObject.getString(PERCENTAGE).trim();

                Sem sem = new Sem(Integer.parseInt(semString));
                sem.setSemTotal(Integer.parseInt(semTotalString));
                sem.setSemPercent(Float.parseFloat(semPercent));
                sem.setSemResult(semResult);

                ArrayList<String> subjectCodes = toStringList(jsonObject.getJSONArray(SUBJECT_CODES));
                ArrayList<String> subjectNames = toStringList(jsonObject.getJSONArray(SUBJECT_NAMES));
                ArrayList<String> subjectResult = toStringList(jsonObject.getJSONArray(SUBJECT_RESULT));
                ArrayList<String> internalMarks = toStringList(jsonObject.getJSONArray(INTERNAL_MARKS));
                ArrayList<String> externalMarks = toStringList(jsonObject.getJSONArray(EXTERNAL_MARKS));
                ArrayList<String> subjectTotal = toStringList(jsonObject.getJSONArray(SUBJECT_TOTAL));

                Log.e("sc= " + sem.getSemester(), subjectCodes.toString());

                for (int j = 0; j < subjectCodes.size(); j++) {
                    Subject subject = new Subject();
                    subject.setSubjectName(subjectNames.get(j));
                    subject.setSubjectCode(subjectCodes.get(j));
                    subject.setInternalMarks(Integer.parseInt(internalMarks.get(j)));
                    subject.setExternalMarks(Integer.parseInt(externalMarks.get(j)));
                    subject.setSubjectTotal(Integer.parseInt(subjectTotal.get(j)));
                    subject.setSubjectResult(subjectResult.get(j));
                    sem.addSubject(subject);
                }
                semList.add(sem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        result.setmSemList(semList);
        student.setResult(result);
        return student;
    }

    public static ArrayList<String> toStringList(JSONArray jsonArray) throws JSONException {
        ArrayList<String> stringList = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String temp = jsonArray.getString(i).trim();
            if (temp != null && !temp.isEmpty() && !temp.equals("null")) {
                //   Log.e("er= ",temp);
                stringList.add(jsonArray.getString(i));
            }
        }
        return stringList;
    }
}
