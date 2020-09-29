package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.OpentdbService;

public class App extends Application {
    public static OpentdbService opentdbService;

    @Override
    public void onCreate() {
        super.onCreate();
        opentdbService = new OpentdbService();
    }
}
