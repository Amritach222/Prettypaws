package com.codewithamrit.myapplication.ForgetPassword;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.codewithamrit.myapplication.R;
import com.codewithamrit.myapplication.Utility.Config;

public class ForgetPasswordVerification extends Fragment {
    ImageButton send_btn;
    EditText code1,code2,code3,code4,code5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_forget_password_verification, container, false);
        send_btn=view.findViewById(R.id.ic_send);
        code1=view.findViewById(R.id.code1);
        code2=view.findViewById(R.id.code2);
        code3=view.findViewById(R.id.code3);
        code4=view.findViewById(R.id.code4);
        code5=view.findViewById(R.id.code5);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object receivedCode=code1.getText().toString()+code2.getText().toString()+
                        code3.getText().toString()+code4.getText().toString()
                        +code5.getText().toString();
                Log.d("task", "onClick: "+receivedCode);
                if (receivedCode.equals(Config.V_CODE)){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_forget_password,ForgetPasswordChangePassword.class,null)
                            .commit();
                }
                else{
                    Toast.makeText(getContext(), "Verification Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}