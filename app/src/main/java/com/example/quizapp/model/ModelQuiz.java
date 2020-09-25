package com.example.quizapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelQuiz implements Serializable {

    public ModelQuiz(ModelQuiz.Type type, String[] listAnswer, String question, String corretAns) {
        Type = type;
        this.listAnswer = listAnswer;
        this.question = question;
        this.corretAns = corretAns;
    }

    public ModelQuiz.Type getType() {
        return Type;
    }

    public void setType(ModelQuiz.Type type) {
        Type = type;
    }

    public String getCorretAns() {
        return corretAns;
    }

    public void setCorretAns(String corretAns) {
        this.corretAns = corretAns;
    }

    private  ModelQuiz.Type Type;
    private String[] listAnswer;
    private String question;
    private String corretAns;
   // private boolean answer;


    public String[] getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(String[] listAnswer) {
        this.listAnswer = listAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


public enum Type{variant, truefalse}

}
