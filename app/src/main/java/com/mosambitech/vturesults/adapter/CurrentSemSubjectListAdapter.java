package com.mosambitech.vturesults.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mosambitech.vturesults.R;
import com.mosambitech.vturesults.model.Result;
import com.mosambitech.vturesults.model.Subject;

import java.util.ArrayList;

/**
 * Created by User1 on 9/23/2015.
 */
public class CurrentSemSubjectListAdapter extends RecyclerView.Adapter<CurrentSemSubjectListAdapter.SubjectHolder> {

    ArrayList<Subject> mSubjectList;

    public CurrentSemSubjectListAdapter(Result result) {
        mSubjectList = result.getSemList().get(0).getAllSubjects();
    }

    @Override
    public SubjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subject, parent, false);
        return new SubjectHolder(view);

    }

    @Override
    public void onBindViewHolder(SubjectHolder holder, int position) {
        holder.subject.setText(mSubjectList.get(position).getSubjectName());
        holder.subjectCode.setText(mSubjectList.get(position).getSubjectCode());
        holder.internalMark.setText(Integer.toString(mSubjectList.get(position).getInternalMarks()));
        holder.externalMark.setText(Integer.toString(mSubjectList.get(position).getExternalMarks()));
        holder.total.setText(Integer.toString(mSubjectList.get(position).getSubjectTotal()));
        holder.subjectResult.setText(mSubjectList.get(position).getSubjectResult());
    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }

    public class SubjectHolder extends RecyclerView.ViewHolder {
        TextView subject, subjectCode, internalMark, externalMark, total, subjectResult;

        public SubjectHolder(View itemView) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.subject);
            subjectCode = (TextView) itemView.findViewById(R.id.subjectCode);
            internalMark = (TextView) itemView.findViewById(R.id.internal);
            externalMark = (TextView) itemView.findViewById(R.id.external);
            total = (TextView) itemView.findViewById(R.id.total);
            subjectResult = (TextView) itemView.findViewById(R.id.result);
        }
    }
}
