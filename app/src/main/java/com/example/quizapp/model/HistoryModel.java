package com.example.quizapp.model;

import java.util.Date;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.quizapp.room.converters.DateConverter;
import com.example.quizapp.room.converters.QuestionConverter;
import java.util.ArrayList;

@Entity
public class HistoryModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "correctAns")
    private int correctAns;
    @ColumnInfo(name = "amount_quiz")
    private int amount_quiz;
    @TypeConverters({QuestionConverter.class})
    private ArrayList<ResultModel> resultModels;
    @TypeConverters({DateConverter.class})
    private Date date;

    public HistoryModel(String category, String difficulty, int correctAns, int amount_quiz, ArrayList<ResultModel> resultModels, Date date) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAns = correctAns;
        this.amount_quiz = amount_quiz;
        this.resultModels = resultModels;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public int getAmount_quiz() {
        return amount_quiz;
    }

    public void setAmount_quiz(int amount_quiz) {
        this.amount_quiz = amount_quiz;
    }

    public ArrayList<ResultModel> getResultModels() {
        return resultModels;
    }

    public void setResultModels(ArrayList<ResultModel> resultModels) {
        this.resultModels = resultModels;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    @Override
//    public String toString() {
//        return "HistoryModel{" +
//                "id=" + id +
//                ", category='" + category + '\'' +
//                ", difficulty='" + difficulty + '\'' +
//                ", correctAns=" + correctAns +
//                ", amount_quiz=" + amount_quiz +
//                ", resultModels=" + resultModels +
//                ", date=" + date +
//                '}';
//    }
}
