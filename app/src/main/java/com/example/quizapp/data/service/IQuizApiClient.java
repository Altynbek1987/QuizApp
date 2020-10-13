package com.example.quizapp.data.service;

import com.example.quizapp.interfac.IQuizApiCallBack;

public interface IQuizApiClient {
    void getQuestion(IQuizApiCallBack.ListQuestion callback, String difficulty, int category, int amount);

    void getCategories(IQuizApiCallBack.QuestionCallback callback);
}
