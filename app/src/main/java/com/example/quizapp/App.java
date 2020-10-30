package com.example.quizapp;

import android.app.Application;
import androidx.room.Room;
import com.example.quizapp.data.Repository;
import com.example.quizapp.data.locally.HistoryStorage;
import com.example.quizapp.data.locally.IHistoryStorage;
import com.example.quizapp.data.service.IQuizApiClient;
import com.example.quizapp.data.service.OpentdbService;
import com.example.quizapp.pref.Preferences;
import com.example.quizapp.room.AppDatabase;

public class App extends Application {

    private static App instance;
    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;
    private Repository repository;
    public AppDatabase database;
    private Preferences preferences;


    @Override
    public void onCreate() {
        super.onCreate();
        //opentdbService = new OpentdbService();
        instance = this;
        preferences = new Preferences(this);
       quizApiClient = new OpentdbService();
       historyStorage = new HistoryStorage();
       repository = new Repository(quizApiClient, historyStorage);
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
    public Preferences getPreferences() {
        return preferences;
    }
}
