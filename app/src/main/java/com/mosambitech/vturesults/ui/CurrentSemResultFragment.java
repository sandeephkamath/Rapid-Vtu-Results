package com.mosambitech.vturesults.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mosambitech.vturesults.R;
import com.mosambitech.vturesults.adapter.CurrentSemSubjectListAdapter;
import com.mosambitech.vturesults.database.DatabaseHandler;
import com.mosambitech.vturesults.model.Student;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentSemResultFragment extends Fragment {

    TextView name, sem, totalMarks, resultField, usnField, percentage;
    RecyclerView subjectList;
    CardView headerView;
    Button saveBtn;
    Student mStudent;

    public CurrentSemResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_sem_result, container, false);
        initViews(view);
        if (null != mStudent)
            setData(mStudent);
        return view;
    }


    private void setData(Student student) {
        name.setText(student.getName());
        sem.setText(Integer.toString(student.getResult().getSemList().get(0).getSemester()));
        totalMarks.setText(Integer.toString(student.getResult().getSemList().get(0).getSemTotal()));
        resultField.setText(student.getResult().getSemList().get(0).getSemResult());
        usnField.setText(student.getUSN());
        percentage.setText(Float.toString(student.getResult().getSemList().get(0).getSemPercent()));
        CurrentSemSubjectListAdapter currentSemSubjectListAdapter = new CurrentSemSubjectListAdapter(student.getResult());
        subjectList.setAdapter(currentSemSubjectListAdapter);
    }

    private void initViews(View view) {
        saveBtn = (Button) view.findViewById(R.id.save);
        name = (TextView) view.findViewById(R.id.name);
        sem = (TextView) view.findViewById(R.id.sem);
        totalMarks = (TextView) view.findViewById(R.id.marks);
        resultField = (TextView) view.findViewById(R.id.result);
        headerView = (CardView) view.findViewById(R.id.header);
        usnField = (TextView) view.findViewById(R.id.usn);
        percentage = (TextView) view.findViewById(R.id.percentage);
        subjectList = (RecyclerView) view.findViewById(R.id.subjectList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        subjectList.setLayoutManager(linearLayoutManager);
        subjectList.setHasFixedSize(false);
        headerView.post(new Runnable() {
            @Override
            public void run() {
                headerView.getLayoutParams().height = 200;
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());
                databaseHandler.addResult(mStudent);
            }
        });
    }

    public void setStudent(Student student) {
        mStudent = student;
    }
}
