package com.example.quizapp.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.model.ModelQuiz;

import java.util.List;

public class QuizViewModel extends ViewModel implements OnItemClickListener {
    //MutableLiveData<List<ModelQuiz>> modelQuizMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> modelQuizMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> answerAmownt = new MutableLiveData<>();
    ModelQuiz modelQuiz;
    List<ModelQuiz> list;


    @Override
    public void onItemClick(int position) {
        if (answerAmownt.getValue()==null){
            answerAmownt.setValue(0);
        }
        answerAmownt.setValue(answerAmownt.getValue()+1);
    }

//    public void onClickBtn(int positionQuestion,int positionAnswer){
//        ModelQuiz modelQuiz = modelQuizMutableLiveData.getValue().get(positionQuestion);
//
//        if (modelQuiz.getListAnswer()[positionAnswer].equals(modelQuiz.getCorretAns())){
//        }
//    }
}
