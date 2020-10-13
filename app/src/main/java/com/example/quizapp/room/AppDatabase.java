package com.example.quizapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizapp.model.HistoryModel;

@Database(entities = {HistoryModel.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();
}
