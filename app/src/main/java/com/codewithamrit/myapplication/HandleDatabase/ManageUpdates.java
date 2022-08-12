package com.codewithamrit.myapplication.HandleDatabase;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageUpdates {
    public void updates(final Context context, String id, String url, final CallBackUpdates callBack){
        final List<String> updateList = new ArrayList<>();
        // Code Here
        StringRequest request1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String success = object.getString("success");
                    JSONArray data = object.getJSONArray("message");
                    if (success.equals("1")) {
                        for(int i=0;i<data.length();i++){
                            JSONObject infoObject = data.getJSONObject(i);
                            String message=infoObject.getString("message");
                            updateList.add(message);
                            callBack.onResponse(updateList);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(context, null);
        requestQueue1.add(request1);
    }
}
