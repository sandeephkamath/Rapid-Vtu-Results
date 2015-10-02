package com.mosambitech.vturesults.ui;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mosambitech.vturesults.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private HomePageFragmentListener mCallBack;

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        inintViews(view);
        return view;
    }

    private void inintViews(View view) {
        final EditText usnField = (EditText) view.findViewById(R.id.usn);
        Button getResultBtn = (Button) view.findViewById(R.id.getResultBtn);

        getResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.getResult(usnField.getText().toString());
            }
        });
    }

    public interface HomePageFragmentListener {
        void getResult(String usn);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (HomePageFragmentListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }
}
