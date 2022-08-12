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
import com.codewithamrit.myapplication.GetterSetter.ModalClassDog;
import com.codewithamrit.myapplication.NetworkIP.NetworkIP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FavouriteDatabase {
    private String retrieve_url=NetworkIP.NETWORK_URL +"/dogFinderApp/retrievefavouritedog.php";
    private String imagePath=NetworkIP.NETWORK_URL +"/dogFinderApp/dogImages/";
    private static ArrayList<ModalClassDog> dogArrayList= new ArrayList<>();
    public void favouriteDogInformation(final Context context,final String userID, final VolleyCallBackForDog callBackForDog){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, retrieve_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dogArrayList.clear();
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String success=jsonObject.getString("success");
                    JSONArray jsonArray=jsonObject.getJSONArray("favourite");
                    if(success.equals("1")){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject object= jsonArray.getJSONObject(i);
                            String id= object.getString("id");
                            String name= object.getString("name");
                            String address= object.getString("address");
                            String image= object.getString("image");
                            String age= object.getString("age");
                            String gender= object.getString("gender");
                            String description= object.getString("description");
                            String breed= object.getString("breed");
                            ModalClassDog classDog= new ModalClassDog(id,name,age,gender,breed,address,description,imagePath+image);
                                dogArrayList.add(classDog);
                                callBackForDog.onSuccessResponse(dogArrayList);
                        }
                        callBackForDog.onSuccessResponse(dogArrayList);
                    }
                    else{
                        Toast.makeText(context, "Error Retrieving data", Toast.LENGTH_SHORT).show();

                    }

                }catch (JSONException e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Server Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("dog", "onErrorResponse: "+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> params= new HashMap<String, String>();
               params.put("userid",userID);
               return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context,null);
        requestQueue.add(stringRequest);
    }


}
