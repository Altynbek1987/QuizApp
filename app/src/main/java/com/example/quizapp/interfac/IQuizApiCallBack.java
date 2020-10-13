package com.example.quizapp.interfac;

import com.example.quizapp.model.ModelCategory;
import com.example.quizapp.model.ModelQuestions;
import com.example.quizapp.model.ResultModel;

import java.util.List;

public interface IQuizApiCallBack {
    interface ListQuestion extends IMainCallBack<ModelQuestions> {
        void onSuccess(ModelQuestions modelQuestions);

        void onFailure(Throwable throwable);
    }

     interface QuestionCallback extends IMainCallBack<ModelCategory>{

        void onSuccess(ModelCategory category);

        void onFailure(Throwable e);


    }
}
