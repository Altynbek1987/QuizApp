package com.example.quizapp.room.converters;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.example.quizapp.model.ResultModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuestionConverter {
    @TypeConverter
    public static String toJsonQuestion(@Nullable ArrayList<ResultModel> resultModels){
        if (resultModels == null)return null;

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ResultModel>>(){}.getType();
        return gson.toJson(resultModels,type);
    }
    @TypeConverter
    public static ArrayList<ResultModel> fromJsonQuestion(@Nullable String json){
        if (json == null)return null;

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ResultModel>>(){}.getType();
        return gson.fromJson(json,type);
    }
}
