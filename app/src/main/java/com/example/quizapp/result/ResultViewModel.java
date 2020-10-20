package com.example.quizapp.result;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.model.HistoryModel;
import com.example.quizapp.model.ResultModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultViewModel extends ViewModel {
    MutableLiveData<List<ResultModel>> resultActivity = new MutableLiveData<>();

    void saveResult(HistoryModel historyModel){
        App.getInstance().getDatabase().historyDao().insert(historyModel);
    }

}
// App.getInstance().getRepository().getQuestion(this,difficulty,category,amount);
//App.getInstance().getDatabase().historyDao().insert(historyModel);