package com.codewithamrit.myapplication.HandleDatabase;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codewithamrit.myapplication.GetterSetter.ModalVeterinary;
import com.codewithamrit.myapplication.NetworkIP.NetworkIP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DatabaseVeterinary {
    String url= NetworkIP.NETWORK_URL+"/dogFinderApp/retrieveveterinaryinfo.php";

    public void retrieveDetail(Context context, CallBackVeterinaryInfo callBackVeterinaryInfo){
        List<ModalVeterinary> veterinaryList=new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String success = object.getString("success");
                    JSONArray data = object.getJSONArray("data");
                    if (success.equals("1")) {
                        for(int i=0;i<data.length();i++){
                            JSONObject infoObject = data.getJSONObject(i);
                            String info_name = infoObject.getString("name");
                            String address = infoObject.getString("address");
                            String contact = infoObject.getString("contact");
                            ModalVeterinary veterinary= new ModalVeterinary(info_name,address,contact);
                            veterinaryList.add(veterinary);
                            callBackVeterinaryInfo.onResponse(veterinaryList);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "ServerError", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue1 = Volley.newRequestQueue(context, null);
        requestQueue1.add(stringRequest);
    }
}
