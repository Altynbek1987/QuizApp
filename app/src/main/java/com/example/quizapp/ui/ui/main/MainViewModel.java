package com.example.quizapp.ui.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.quizapp.App;
import com.example.quizapp.interfac.IQuizApiCallBack;
import com.example.quizapp.model.ModelCategory;

public class MainViewModel extends ViewModel implements IQuizApiCallBack.QuestionCallback {
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
        App.getInstance().getRepository().getCategories(this);
    }

    @Override
    public void onSuccess(ModelCategory category) {
        mutableCategory.setValue(category);

    }

    @Override
    public void onFailure(Throwable e) {

    }

}