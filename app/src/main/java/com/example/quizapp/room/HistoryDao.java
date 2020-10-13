package com.example.quizapp.room;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.quizapp.model.HistoryModel;

@Dao
public interface HistoryDao {
@Insert
    void insert(HistoryModel historyModel);
}
