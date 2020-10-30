
package com.example.quizapp.model;
import android.text.Html;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultModel {

    @SerializedName("category")
    @Expose
    private String category; //категория
    @SerializedName("type")
    @Expose
    private String type;  //тип
    @SerializedName("difficulty")
    @Expose
    private String difficulty;  //трудность
    @SerializedName("question")
    @Expose
    private String question;  //вопрос
    @SerializedName("correct_answer")
    @Expose
    private String correctAnswer;  //правильный ответ
    @SerializedName("incorrect_answers")
    @Expose
    private List<String> incorrectAnswers;  //неправильные ответы
    private boolean isChoice;
    private boolean isBind;

    int rightAnswer;



    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public boolean isBind() {
        return isBind;
    }

    public void setBind(boolean bind) {
        isBind = bind;
    }

    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    private int userChoice;

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        for (int i = 0; i < incorrectAnswers.size() -1; i++) {
            incorrectAnswers.set(i, Html.fromHtml(Html.fromHtml(incorrectAnswers.get(i)).toString()).toString());
        }
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers +
                ", isChoice=" + isChoice +
                ", userChoice=" + userChoice +
                '}';
    }

}
