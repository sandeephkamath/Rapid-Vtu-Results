package com.mosambitech.vturesults.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mosambitech.vturesults.R;
import com.mosambitech.vturesults.adapter.StudentListAdapter;
import com.mosambitech.vturesults.database.DatabaseHandler;
import com.mosambitech.vturesults.model.Student;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedStudentListFragment extends Fragment {


    public SavedStudentListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_student_list, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        RecyclerView studentList = (RecyclerView) view.findViewById(R.id.studentList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        studentList.setLayoutManager(linearLayoutManager);
        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());
        StudentListAdapter studentListAdapter = new StudentListAdapter(databaseHandler.getAllStudents());
        studentListAdapter.setStudentClickListner(getActivity());
        studentList.setAdapter(studentListAdapter);
    }

    public interface StudentClickListener {
        void onStudentClicked(Student student);
    }
}
