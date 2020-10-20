package com.example.quizapp.data.locally;

import androidx.lifecycle.LiveData;

import com.example.quizapp.model.HistoryModel;
import com.example.quizapp.model.ResultModel;

import java.util.ArrayList;
import java.util.List;

public interface IHistoryStorage {
    LiveData<ArrayList<ResultModel>>getAll();
    ResultModel getQuizResult(int id);

    int saveQuizResult(HistoryModel historyModel);//Nado perdat modelcu vnutr skopki
    void delete(int id);
    void deleteAll();

//    void insertHistoryResult(HistoryModel historyModel);
//    List<HistoryModel> getAllHistoryResult();
//    void clearAll();
}
