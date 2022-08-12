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
import com.codewithamrit.myapplication.GetterSetter.UserGetterSetter;
import com.codewithamrit.myapplication.NetworkIP.NetworkIP;
import com.codewithamrit.myapplication.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelper {
    String infoUrl=NetworkIP.NETWORK_URL +"/dogFinderApp/retrieveuserinfo.php";
    String storeImageUrl=NetworkIP.NETWORK_URL +"/dogFinderApp/storeuserimage.php";
    String imagePath= NetworkIP.NETWORK_URL +"/dogFinderApp/userImage/";
    String updateUrl=NetworkIP.NETWORK_URL +"/dogFinderApp/updateuserinfo.php";
    private SessionManager sessionManager;
    public void databaseHelper(final Context context, final String email, final String password, final VolleyCallBack callBack){
        final List<UserGetterSetter> personsList = new ArrayList<>();
        // Code Here
        StringRequest request1 = new StringRequest(Request.Method.POST, infoUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String success = object.getString("success");
                    if (success.equals("1")) {
                        JSONArray data = object.getJSONArray("data");
                        JSONObject infoObject = data.getJSONObject(0);
                        String info_id = infoObject.getString("id");
                        String info_name = infoObject.getString("name");
                        String info_email = infoObject.getString("email");
                        String info_contact = infoObject.getString("contact_number");
                        String info_address = infoObject.getString("address");
                        String info_imageUrl=imagePath+infoObject.getString("imagename");
                        UserGetterSetter userGetterSetter = new UserGetterSetter(info_id, info_name, info_email, info_contact, info_address, info_imageUrl);
                        personsList.add(userGetterSetter);
                        callBack.onSuccessResponse(personsList);
                        new SessionManager(context).setUserId(info_id);
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
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(context, null);
        requestQueue1.add(request1);
    }
    public void storeAccountImage(final Context context, final String encodedImage, final String email, final String password ){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, storeImageUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "onResponse: "+response);
                try {
                    JSONObject object= new JSONObject(response);
                    String success=object.getString("success");
                    if (success.equals("1")){
                        Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "Image upload Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("userimage",encodedImage);
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(context,null);
        requestQueue.add(stringRequest);
    }

//    update Profile
    public void updateProfile(final Context context,final String old_email,final String old_password, final String name, final String email, final String contact,final String password){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, updateUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                sessionManager= new SessionManager(context);
                Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
                if (!email.equals("1")){
                    sessionManager.setEmail(email);
                }
                if (!password.equals("1")){
                    sessionManager.setPassword(password);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<String, String>();
                params.put("oldemail",old_email);
                params.put("oldpass",old_password);
                params.put("name",name);
                params.put("email",email);
                params.put("contact",contact);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(context,null);
        requestQueue.add(stringRequest);
    }
}