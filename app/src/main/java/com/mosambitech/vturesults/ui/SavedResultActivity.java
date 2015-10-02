package com.mosambitech.vturesults.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mosambitech.vturesults.R;
import com.mosambitech.vturesults.model.Student;

public class SavedResultActivity extends AppCompatActivity implements SavedStudentListFragment.StudentClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_result);

        SavedStudentListFragment savedStudentListFragment=new SavedStudentListFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, savedStudentListFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void onStudentClicked(Student student) {
        CurrentSemResultFragment currentSemResultFragment = new CurrentSemResultFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, currentSemResultFragment);
        currentSemResultFragment.setStudent(student);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
