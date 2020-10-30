package com.example.quizapp.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.quizapp.App;
import com.example.quizapp.interfac.IQuizApiCallBack;
import com.example.quizapp.model.HistoryModel;
import com.example.quizapp.model.ModelQuestions;
import com.example.quizapp.model.ResultModel;
import java.util.List;

public class QuizViewModel extends ViewModel implements IQuizApiCallBack.ListQuestion {
    MutableLiveData<Integer> answerAmownt = new MutableLiveData<>(0);
    MutableLiveData<List<ResultModel>> mutableQuestions = new MutableLiveData<>();
    MutableLiveData<Integer> positionAnswer = new MutableLiveData<>(0);
    MutableLiveData<List<ResultModel>> resultQuiz = new MutableLiveData<>();
    ResultModel resultModel;
    List<ModelQuestions> list;
    HistoryModel historyModel;
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
    void skip(){
        if (positionAnswer.getValue() != null){
            positionAnswer.setValue(positionAnswer.getValue()+1);
        } else {
            positionAnswer.setValue(0);
        }
    }
    void btnBack(){
        if (positionAnswer.getValue() != null){
            positionAnswer.setValue(positionAnswer.getValue() - 1);
        }else {
            positionAnswer.setValue(0);
        }
    }
}
