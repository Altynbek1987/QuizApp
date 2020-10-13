package com.example.quizapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class HistoryModel {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String category;
    private String difficulty;
    private int correctAns;
    private String data;
    private int amount_quiz;


    public HistoryModel(long id, String category, String difficulty, int correctAns, String data, int amount_quiz) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAns = correctAns;
        this.data = data;
        this.amount_quiz = amount_quiz;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getAmount_quiz() {
        return amount_quiz;
    }

    public void setAmount_quiz(int amount_quiz) {
        this.amount_quiz = amount_quiz;
    }

    @Override
    public String toString() {
        return "HistoryModel{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", correctAns=" + correctAns +
                ", data='" + data + '\'' +
                ", amount_quiz=" + amount_quiz +
                '}';
    }
}
