package com.example.quizapp.data;

import com.example.quizapp.data.service.IQuizApiClient;
import com.example.quizapp.interfac.IMainCallBack;
import com.example.quizapp.interfac.IQuizApiCallBack;

public class Repository implements IQuizApiClient {
    IQuizApiClient quizApiClient;

    public Repository(IQuizApiClient quizApiClient) {
        this.quizApiClient = quizApiClient;

    }

    @Override
    public void getQuestion(IQuizApiCallBack.ListQuestion callback, String difficulty, int category, int amount) {
        quizApiClient.getQuestion(callback,difficulty,category,amount);

    }

    @Override
    public void getCategories(IQuizApiCallBack.QuestionCallback callback) {
        quizApiClient.getCategories(callback);

    }
}
