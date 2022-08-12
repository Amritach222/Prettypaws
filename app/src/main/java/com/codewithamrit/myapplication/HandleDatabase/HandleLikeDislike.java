package com.codewithamrit.myapplication.HandleDatabase;

import android.content.Context;
import android.util.Log;
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

public class HandleLikeDislike {
    private String checkLike=NetworkIP.NETWORK_URL +"/dogFinderApp/checklike.php";
    private String insertLike=NetworkIP.NETWORK_URL +"/dogFinderApp/insertlike.php";
    private String deleteLike=NetworkIP.NETWORK_URL +"/dogFinderApp/deletelike.php";
    public void checkLikeStatus(Context context,String userID,String dogID ,CallBackCheckLike callBackCheckLike){

        StringRequest stringRequest= new StringRequest(Request.Method.POST, checkLike, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String success=jsonObject.getString("success");
                    if(success.equals("1")){
                        Log.d("status", "getLikeButtonStatus: "+"null");
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
                Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<String, String>();
                params.put("userid",userID);
                params.put("dogid",dogID);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context,null);
        requestQueue.add(stringRequest);
    }
    public void insertLike(Context context,String userID,String dogID,  CallBackCheckLike callBackCheckLike){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, insertLike, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String success=jsonObject.getString("success");
                    if(success.equals("1")){
                        Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                        callBackCheckLike.onSuccessResponse(true);
                    }
                    else{
                        Toast.makeText(context, "Error "+response, Toast.LENGTH_SHORT).show();
                        callBackCheckLike.onSuccessResponse(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<String, String>();
                params.put("userid",userID);
                params.put("dogid",dogID);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context,null);
        requestQueue.add(stringRequest);
    }
    public void deleteLike(Context context,String userID,String dogID, CallBackCheckLike callBackCheckLike){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, deleteLike, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String success=jsonObject.getString("success");
                    if(success.equals("1")){
                        Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT).show();
                        callBackCheckLike.onSuccessResponse(true);
                    }
                    else{
                        Toast.makeText(context, "Error" +response ,Toast.LENGTH_SHORT).show();
                        callBackCheckLike.onSuccessResponse(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<String, String>();
                params.put("userid",userID);
                params.put("dogid",dogID);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context,null);
        requestQueue.add(stringRequest);
    }
}
