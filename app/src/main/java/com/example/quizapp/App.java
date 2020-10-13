package com.example.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.example.quizapp.data.Repository;
import com.example.quizapp.data.service.IQuizApiClient;
import com.example.quizapp.data.service.OpentdbService;
import com.example.quizapp.room.AppDatabase;

public class App extends Application {
    //public static OpentdbService opentdbService;
    private static App instance;
    private IQuizApiClient quizApiClient;
    private Repository repository;
    private AppDatabase database;


    @Override
    public void onCreate() {
        super.onCreate();
        //opentdbService = new OpentdbService();
        instance = this;
       quizApiClient = new OpentdbService();
       repository = new Repository(quizApiClient);
       database = Room.databaseBuilder(this,AppDatabase.class,"my_data_base")
               .allowMainThreadQueries()
               .fallbackToDestructiveMigration()
               .build();
    }

    public static App getInstance() {
        return instance;
    }
    public Repository getRepository(){
        return repository;
    }
    public AppDatabase getDatabase() {
        return database;
    }
}
