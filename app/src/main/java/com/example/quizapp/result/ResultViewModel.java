package com.example.quizapp.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.quizapp.App;
import com.example.quizapp.model.HistoryModel;
import com.example.quizapp.model.ResultModel;
import java.util.List;

public class ResultViewModel extends ViewModel {
    MutableLiveData<List<ResultModel>> resultActivity = new MutableLiveData<>();

    void saveResult(HistoryModel historyModel){
        App.getInstance().getDatabase().historyDao().insert(historyModel);
    }
}