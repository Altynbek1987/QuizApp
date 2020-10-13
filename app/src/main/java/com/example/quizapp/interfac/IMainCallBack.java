package com.example.quizapp.interfac;

public interface IMainCallBack <T>{
    void onSuccess(T result);
    void onFailure(Throwable e);
}
