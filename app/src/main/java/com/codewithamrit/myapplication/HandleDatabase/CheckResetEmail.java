package com.codewithamrit.myapplication.HandleDatabase;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codewithamrit.myapplication.NetworkIP.NetworkIP;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CheckResetEmail {
    String check_url= NetworkIP.NETWORK_URL+"/dogFinderApp/retrieveallemail.php";
    public void checkResetEmail(Context context, String email, CallBackCheckLike callBackCheckLike){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, check_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String success=jsonObject.getString("success");
                    if(success.equals("1")){
                        callBackCheckLike.onSuccessResponse(true);
                    }
                    else{
                        callBackCheckLike.onSuccessResponse(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Problem with internet", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<String, String>();
               params.put("email",email);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context,null);
        requestQueue.add(stringRequest);
    }
}
