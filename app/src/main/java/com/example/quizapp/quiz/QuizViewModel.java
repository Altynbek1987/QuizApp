package com.example.quizapp.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.OpentdbService;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.model.ModelCategory;
import com.example.quizapp.model.ModelQuestions;
import com.example.quizapp.model.ModelQuiz;
import com.example.quizapp.model.ResultModel;

import java.util.List;

public class QuizViewModel extends ViewModel implements  OpentdbService.QuestionCallback {
    MutableLiveData<Integer> answerAmownt = new MutableLiveData<>();
    MutableLiveData<List<ResultModel>> mutableQuestions = new MutableLiveData<>();
    MutableLiveData<ResultModel> backQuestion = new MutableLiveData<>();
    ModelQuiz modelQuiz;
    List<ModelQuestions> list;


    void updateQuestion(String difficulty,int category,int amount){
        App.opentdbService.getListQuestion(this, difficulty,category, amount);
    }

    @Override
    public void onSuccess(List<ResultModel> body) {
        Log.e("ololo", "onSuccess: " + body.size());
       mutableQuestions.setValue(body);

    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(ModelCategory question) {

    }
}
