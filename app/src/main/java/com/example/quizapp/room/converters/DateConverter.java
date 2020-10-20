package com.example.quizapp.room.converters;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.example.quizapp.model.ResultModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    @TypeConverter
    public static Long toJsonDate(@Nullable Date date){
        if (date == null)return null;
        return date.getTime();
    }
    @TypeConverter
    public static Date fromJsonDate(@Nullable Long timestamp){
        if (timestamp == null)return null;
        return new Date(timestamp);
    }
}
