package com.mosambitech.vturesults.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mosambitech.vturesults.model.Sem;
import com.mosambitech.vturesults.model.Student;
import com.mosambitech.vturesults.model.Subject;

import java.util.ArrayList;

/**
 * Created by User1 on 8/1/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "RAPIDVTU";
    private static int DATABASE_VERSION = 1;

    private static String TABLE_STUDENT = "student";
    private static String TABLE_SUBJECTS = "subjects";

    private static String STUDENT_ID = "student_id";
    private static String STUDENT_NAME = "student_name";
    private static String STUDENT_USN = "usn";
    private static String STUDENT_AGGREGATE = "aggregate";


    private static String SEM_NUMBER = "sem";
    private static String SUBJECT_NAME = "subject_name";
    private static String SUBJECT_CODE = "subject_code";
    private static String SUBJECT_INTERNAL_MARKS = "internal_marks";
    private static String SUBJECT_EXTERNAL_MARKS = "external_marks";
    private static String SUBJECT_TOTAL = "subject_total";
    private static String SUBJECT_RESULT = "subject_result";

    private static String CREATE_STUDENT = " CREATE TABLE " + TABLE_STUDENT +
            " ( " + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME +
            " TEXT, " + STUDENT_USN + " TEXT, " + STUDENT_AGGREGATE + " TEXT " +
            " ); ";


    private static String CREATE_SUBJECT = " CREATE TABLE " + TABLE_SUBJECTS +
            " ( " + SEM_NUMBER + " INTEGER, " + STUDENT_ID + " INTEGER, " +
            SUBJECT_NAME + " TEXT, " + SUBJECT_CODE + " TEXT, " + SUBJECT_INTERNAL_MARKS +
            " INTEGER, " + SUBJECT_EXTERNAL_MARKS + " INTEGER, " + SUBJECT_TOTAL + " INTEGER,"
            + SUBJECT_RESULT + "TEXT, PRIMARY KEY( " + SEM_NUMBER + " , " + STUDENT_ID + "));";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_STUDENT);
        database.execSQL(CREATE_SUBJECT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_AGGREGATE, student.getAggregate());
        values.put(STUDENT_USN, student.getUSN());
        database.insert(TABLE_STUDENT, null, values);
        database.close();
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setUSN(cursor.getString(2));
                student.setAggregate(cursor.getInt(3));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        return studentList;
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_STUDENT, STUDENT_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
        database.close();
    }

    public void addSem(Student student, Sem sem) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        ArrayList<Subject> subjects = sem.getAllSubjects();

        for (int i = 0; i < sem.getNumberOfSubjects(); i++) {
            values.put(SEM_NUMBER, sem.getSemester());
            values.put(STUDENT_ID, student.getId());
            values.put(STUDENT_NAME, student.getName());
            values.put(SUBJECT_CODE, subjects.get(i).getSubjectCode());
            values.put(SUBJECT_INTERNAL_MARKS, subjects.get(i).getInternalMarks());
            values.put(SUBJECT_EXTERNAL_MARKS, subjects.get(i).getExternalMarks());
            values.put(SUBJECT_TOTAL, subjects.get(i).getSubjectTotal());
            values.put(SUBJECT_RESULT, subjects.get(i).getSubjectResult());
            database.insert(TABLE_SUBJECTS, null, values);
        }
        database.close();
    }

    public void deleteSem(Student student, Sem sem) {
        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<Subject> subjects = sem.getAllSubjects();
        database.delete(TABLE_SUBJECTS, STUDENT_ID + " = ? AND " + SEM_NUMBER + " = ? ",
                new String[]{String.valueOf(student.getId()), String.valueOf(sem.getSemester())});
        database.close();
    }

    public void addResult(Student student) {
        addStudent(student);
        ArrayList<Sem> semList =student.getResult().getSemList();
        for (int i = 0; i < semList.size(); i++) {
            addSem(student, semList.get(i));
        }

    }

}
