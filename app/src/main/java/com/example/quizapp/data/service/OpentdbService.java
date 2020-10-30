package com.example.quizapp.data.service;

import android.accounts.NetworkErrorException;
import android.util.Log;
import com.example.quizapp.interfac.IQuizApiCallBack;
import com.example.quizapp.model.ModelCategory;
import com.example.quizapp.model.ModelQuestions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpentdbService implements IQuizApiClient{

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://opentdb.com/")
            .build();
QuizApi service = retrofit.create(QuizApi.class);

    @Override
    public void getQuestion(IQuizApiCallBack.ListQuestion callback, String difficulty, int category, int amount) {
        Call<ModelQuestions> call = service.getQuestion(amount,category,difficulty);
        call.enqueue(new Callback<ModelQuestions>() {
            @Override
            public void onResponse(Call<ModelQuestions> call, Response<ModelQuestions> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onSuccess(response.body());
                        Log.e("ololo","getListQuestion");
                    }else {
                        Log.e("ololo", "response body is null");
                        callback.onFailure(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelQuestions> call, Throwable t) {
                Log.e("ololo", "Error");
                callback.onFailure(new Exception());
            }
        });
    }

    @Override
    public void getCategories(IQuizApiCallBack.QuestionCallback callback) {
        Call<ModelCategory> call = service.getCategoryQuestion();
        call.enqueue(new Callback<ModelCategory>() {
            @Override
            public void onResponse(Call<ModelCategory> call, Response<ModelCategory> response) {
                if (response.isSuccessful() && response.body() != null){
                    Log.e("tag", response.body().toString());
                    callback.onSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<ModelCategory> call, Throwable t) {
                callback.onFailure(new NetworkErrorException());
            }
        });

    }



}
