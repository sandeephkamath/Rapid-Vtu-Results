package com.mosambitech.vturesults.network;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mosambitech.vturesults.AppController;
import com.mosambitech.vturesults.JSONHandler.JSONHandler;
import com.mosambitech.vturesults.model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User1 on 9/21/2015.
 */
public class NetworkManager {
    public static String ALL_SEM_URL = "http://www.mosambitech.com/vturesults/allsem.php?usn=";
    public static String CURRENT_SEM_URL = "http://www.mosambitech.com/vturesults/currentsem.php?usn=";
    private static ResultDownloadListener mListener;

    public static void getCurrentSemResult(String usn, ResultDownloadListener listener) {
        mListener = listener;
        AppController.getInstance().addToRequestQueue(getCurrentSemRequest(usn));
    }
    public static void getAllSemResult(String usn,ResultDownloadListener listener){
        mListener=listener;
        AppController.getInstance().addToRequestQueue(getAllSemRequest(usn));
    }

    public interface ResultDownloadListener {
        void onSuccess(Student student);

        void onFailure();
    }


    private static JsonObjectRequest getCurrentSemRequest(String usn) {
        JsonObjectRequest currentSem = new JsonObjectRequest(CURRENT_SEM_URL + usn, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray("[" + response.toString() + "]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Student student = JSONHandler.getResult(jsonArray);
                if (null != mListener)
                    mListener.onSuccess(student);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != mListener)
                    mListener.onFailure();
            }
        });
        currentSem.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return currentSem;
    }

    private static JsonArrayRequest getAllSemRequest(String usn) {
        JsonArrayRequest allSemRequest = new JsonArrayRequest(ALL_SEM_URL+usn, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Student student = JSONHandler.getResult(response);
                if (null != mListener)
                    mListener.onSuccess(student);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != mListener)
                    mListener.onFailure();
            }
        });
        allSemRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return allSemRequest;
    }
}
