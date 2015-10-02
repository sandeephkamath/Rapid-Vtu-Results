package com.mosambitech.vturesults.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mosambitech.vturesults.R;
import com.mosambitech.vturesults.model.Student;
import com.mosambitech.vturesults.ui.SavedStudentListFragment;

import java.util.ArrayList;

/**
 * Created by User1 on 9/29/2015.
 */
public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentHolder> {

    private ArrayList<Student> mStudentList;
    private SavedStudentListFragment.StudentClickListener mListener;

    public StudentListAdapter(ArrayList<Student> studentList) {
        mStudentList = studentList;
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_student_list_row, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentHolder holder, int position) {
        holder.studentName.setText(mStudentList.get(0).getName());
        holder.usn.setText(mStudentList.get(0).getUSN());
    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder {
        TextView studentName, usn;

        public StudentHolder(View itemView) {
            super(itemView);
            studentName = (TextView) itemView.findViewById(R.id.studentName);
            usn = (TextView) itemView.findViewById(R.id.usn);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null!=mListener)
                        mListener.onStudentClicked(mStudentList.get(getPosition()));
                }
            });
        }
    }

    /*public void setStudentClickListner(SavedStudentListFragment.StudentClickListener studentClickListner){
        mListener=studentClickListner;
    }*/

    public void setStudentClickListner(Context context){
        try {
            mListener=(SavedStudentListFragment.StudentClickListener)context;
        }catch (ClassCastException e){
            e.printStackTrace();
        }


    }
}
