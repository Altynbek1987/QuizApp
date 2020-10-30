package com.example.quizapp.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quizapp.model.HistoryModel;
import com.example.quizapp.model.ResultModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    long insert(HistoryModel historyModel);



    @Query("SELECT*FROM historymodel WHERE id=:id")
    HistoryModel getById(int id);

    @Delete
    void delete(HistoryModel historyModel);

    @Query("DELETE FROM historymodel")
    void deleteAll();


   @Query("SELECT*FROM historymodel")
   LiveData<List<HistoryModel>> getAll();
}
