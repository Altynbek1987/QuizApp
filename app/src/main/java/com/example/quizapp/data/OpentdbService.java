package com.example.quizapp.data;

import android.accounts.NetworkErrorException;
import android.util.Log;

import androidx.constraintlayout.motion.utils.MonotonicCurveFit;

import com.example.quizapp.model.ModelCategory;
import com.example.quizapp.model.ModelQuestions;
import com.example.quizapp.model.ResultModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class OpentdbService {

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://opentdb.com/")
            .build();
QuizApi service = retrofit.create(QuizApi.class);

public void getListQuestion(QuestionCallback callback, String difficulty, int category,int amount){
    Call<ModelQuestions> call = service.getQuestion(amount,category,difficulty);
    call.enqueue(new Callback<ModelQuestions>() {
        @Override
        public void onResponse(Call<ModelQuestions> call, Response<ModelQuestions> response) {
            if (response.isSuccessful()){
                if (response.body() != null){
                    callback.onSuccess(response.body().getResults());
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
public void getCategory(QuestionCallback callback){
    Call<ModelCategory> call = service.getCategoryQuestion();
    call.enqueue(new Callback<ModelCategory>() {
        @Override
        public void onResponse(Call<ModelCategory> call, Response<ModelCategory> response) {
            if (response.isSuccessful() && response.body() != null){
                Log.e("tag", response.body().toString());
                callback.onResponse(response.body());
            }

        }

        @Override
        public void onFailure(Call<ModelCategory> call, Throwable t) {
            callback.onFailure(new NetworkErrorException());
        }
    });
}
public interface QuestionCallback{

    void onSuccess(List<ResultModel> body);

    void onFailure(Exception e);

    void onResponse(ModelCategory question);
}

    public interface QuizApi{
        @GET("api.php")
        Call<ModelQuestions>getQuestion(
                @Query("amount")int amount,
                @Query("category")int category,
                @Query("difficulty") String difficulty
        );

        @GET("api_category.php")
        Call<ModelCategory>getCategoryQuestion();

    }
}
