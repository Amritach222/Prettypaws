package com.codewithamrit.myapplication.HandleDatabase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codewithamrit.myapplication.ExploreActivity;
import com.codewithamrit.myapplication.GetterSetter.AdoptionQuestionModalClass;
import com.codewithamrit.myapplication.NetworkIP.NetworkIP;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database_Adoption {
String adoption_url= NetworkIP.NETWORK_URL+"/dogFinderApp/adoptiondetail.php";
String user_id,ans1,ans2,ans3,ans4,ans5,ans6,ans7,ans8,ans9,ans10,dogID;
public void insertAdoptionData(Context context, List<AdoptionQuestionModalClass> adoptionQuestionModalClasses){
    //Creating processDialog
    final ProgressDialog progressDialog= new ProgressDialog(context);
    progressDialog.setTitle("Please wait!");
    for(AdoptionQuestionModalClass modalClass:adoptionQuestionModalClasses){
        user_id=modalClass.getUserid();
        ans1=modalClass.getAns1();
        ans2=modalClass.getAns2();
        ans3=modalClass.getAns3();
        ans4=modalClass.getAns4();
        ans5=modalClass.getAns5();
        ans6=modalClass.getAns6();
        ans7=modalClass.getAns7();
        ans8=modalClass.getAns8();
        ans9=modalClass.getAns9();
        ans10=modalClass.getAns10();
        dogID=modalClass.getDogId();
    }
    progressDialog.show();
// creating Object of StringRequest
    StringRequest stringRequest= new StringRequest(Request.Method.POST,adoption_url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject object = new JSONObject(response);
                String success= object.getString("success");
                if(success.equals("1")) {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Your form is successfully applied.", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, ExploreActivity.class));
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "ServerError", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }}, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            progressDialog.dismiss();
            Toast.makeText(context, ""+"No Internet", Toast.LENGTH_SHORT).show();

        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params= new HashMap<String, String>();
            params.put("userid",user_id);
            params.put("q1",ans1);
            params.put("q2",ans2);
            params.put("q3",ans3);
            params.put("q4",ans4);
            params.put("q5",ans5);
            params.put("q6",ans6);
            params.put("q7",ans7);
            params.put("q8",ans8);
            params.put("q9",ans9);
            params.put("q10",ans10);
            params.put("dogid",dogID);
            return params;
        }
    };
    //Create requestQueue Object
    RequestQueue requestQueue= Volley.newRequestQueue(context,null);
    requestQueue.add(stringRequest);
}
}
