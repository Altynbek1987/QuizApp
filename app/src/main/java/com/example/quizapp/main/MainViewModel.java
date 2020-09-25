package com.example.quizapp.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

    public void plus(){
        if (mutableLiveData.getValue() == null){
            mutableLiveData.setValue(0);
        }
        if (mutableLiveData.getValue() <= 49){
            mutableLiveData.setValue(mutableLiveData.getValue()+1);
        }
    }
    public void minus(){
        if (mutableLiveData.getValue() == null){
            mutableLiveData.setValue(0);
        }
        if (mutableLiveData.getValue() >= 1){
            mutableLiveData.setValue(mutableLiveData.getValue()-1);
        }
    }
}