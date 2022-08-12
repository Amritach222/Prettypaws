package com.codewithamrit.myapplication.HandleDatabase;

import com.codewithamrit.myapplication.GetterSetter.UserGetterSetter;

import java.util.List;

public interface VolleyCallBack {
    void onSuccessResponse(List<UserGetterSetter> list);
}
