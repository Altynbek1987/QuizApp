package com.example.quizapp.main;

import android.widget.ArrayAdapter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.data.OpentdbService;
import com.example.quizapp.model.ModelCategory;
import com.example.quizapp.model.ModelQuestions;
import com.example.quizapp.model.ResultModel;
import com.example.quizapp.model.TriviaCategory;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel implements OpentdbService.QuestionCallback {
    private ModelCategory modelCategory;
    MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    MutableLiveData<ModelCategory> mutableCategory = new MutableLiveData<>();

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

    void updateCategory(){
        App.opentdbService.getCategory(this);
    }

    @Override
    public void onSuccess(List<ResultModel> body) {

    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onResponse(ModelCategory question) {
        mutableCategory.setValue(question);

    }

}