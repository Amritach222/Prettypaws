package com.codewithamrit.myapplication.HandleDatabase;

import com.codewithamrit.myapplication.GetterSetter.ModalVeterinary;

import java.util.List;

public interface CallBackVeterinaryInfo {
    void onResponse(List<ModalVeterinary> list);
}
