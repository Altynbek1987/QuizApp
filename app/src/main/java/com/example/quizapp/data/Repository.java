package com.example.quizapp.data;

import androidx.lifecycle.LiveData;

import com.example.quizapp.data.locally.IHistoryStorage;
import com.example.quizapp.data.service.IQuizApiClient;
import com.example.quizapp.interfac.IMainCallBack;
import com.example.quizapp.interfac.IQuizApiCallBack;
import com.example.quizapp.model.HistoryModel;
import com.example.quizapp.model.ResultModel;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IQuizApiClient, IHistoryStorage {
    IQuizApiClient quizApiClient;
    private final IHistoryStorage historyStorage;


    public Repository(IQuizApiClient quizApiClient,IHistoryStorage iHistoryStorage) {
        this.quizApiClient = quizApiClient;
        this.historyStorage = iHistoryStorage;

    }
// Методы IQuizApiClient ( getQuestion, getCategories)
    @Override
    public void getQuestion(IQuizApiCallBack.ListQuestion callback, String difficulty, int category, int amount) {
        quizApiClient.getQuestion(callback,difficulty,category,amount);

    }

    @Override
    public void getCategories(IQuizApiCallBack.QuestionCallback callback) {
        quizApiClient.getCategories(callback);

    }
    public void insertHistoryResult(HistoryModel historyModel) {
        historyStorage.saveQuizResult(historyModel);
    }



    // Методы IHistoryStorage нижние пять.
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
