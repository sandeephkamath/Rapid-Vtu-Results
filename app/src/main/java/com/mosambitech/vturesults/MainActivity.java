package com.mosambitech.vturesults;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mosambitech.vturesults.model.Student;
import com.mosambitech.vturesults.network.NetworkManager;
import com.mosambitech.vturesults.ui.CurrentSemResultFragment;
import com.mosambitech.vturesults.ui.HomePageFragment;


public class MainActivity extends AppCompatActivity implements HomePageFragment.HomePageFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadHomePageFragment();


    }

    private void loadHomePageFragment() {
        HomePageFragment homePageFragment = new HomePageFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, homePageFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void getResult(final String usn) {
        NetworkManager.getCurrentSemResult(usn, new NetworkManager.ResultDownloadListener() {
            @Override
            public void onSuccess(Student student) {
                CurrentSemResultFragment currentSemResultFragment = new CurrentSemResultFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, currentSemResultFragment);
                currentSemResultFragment.setStudent(student);
                fragmentTransaction.commitAllowingStateLoss();
            }

            @Override
            public void onFailure() {

            }
        });
    }

}
