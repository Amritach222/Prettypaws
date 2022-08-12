package com.codewithamrit.myapplication.AddDogFragments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codewithamrit.myapplication.GetterSetter.ModalClassDog;

import java.util.List;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<List<ModalClassDog>> fragmentData = new MutableLiveData<>();
    public void setData(List<ModalClassDog> item) {
        fragmentData.setValue(item);
    }
    public MutableLiveData<List<ModalClassDog>> getSelectedItem() {
        return fragmentData;
    }
}
