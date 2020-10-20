package com.example.quizapp.data.locally;

import androidx.lifecycle.LiveData;

import com.example.quizapp.model.HistoryModel;
import com.example.quizapp.model.ResultModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    @Override
    public LiveData<ArrayList<ResultModel>> getAll() {
        return null;
    }

    @Override
    public ResultModel getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(HistoryModel historyModel) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }


}
