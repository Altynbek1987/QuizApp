package com.example.quizapp.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.data.service.OpentdbService;
import com.example.quizapp.interfac.IQuizApiCallBack;
import com.example.quizapp.model.ModelCategory;
import com.example.quizapp.model.ModelQuestions;
import com.example.quizapp.model.ResultModel;

import java.util.List;
import java.util.Objects;

public class QuizViewModel extends ViewModel implements IQuizApiCallBack.ListQuestion {
    MutableLiveData<Integer> answerAmownt = new MutableLiveData<>(0);
    MutableLiveData<List<ResultModel>> mutableQuestions = new MutableLiveData<>();
    MutableLiveData<ResultModel> skipMutable = new MutableLiveData<>();

    ResultModel resultModel;
    List<ModelQuestions> list;
    int corAnswer;

    @Override
    public void onSuccess(ModelQuestions modelQuestions) {
        mutableQuestions.setValue(modelQuestions.getResults());
    }

    @Override
    public void onFailure(Throwable throwable) {
    }

    void updateQuestion(String difficulty,int category,int amount){
        App.getInstance().getRepository().getQuestion(this,difficulty,category,amount);
    }

    void correctAnswerr(){
       corAnswer++;
    }

}
