package com.mosambitech.vturesults;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by User1 on 8/2/2015.
 */
public class AppController extends Application {

    public static final String TAG = AppController.class
            .getSimpleName();
    private RequestQueue mRequestQueue;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Log.v(TAG, "Create");
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        Log.v(TAG,"Request que");
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        Log.v(TAG,"add");
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
