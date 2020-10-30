package com.example.quizapp.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private  SharedPreferences preferences;
    public Preferences(Context context){
        preferences = context.getSharedPreferences("MyPref",Context.MODE_PRIVATE);

    }
    public void setTheme(int value){
        preferences.edit().putInt("theme",value).apply();
    }
    public int getTheme(int defValue){
       return preferences.getInt("theme",defValue);
    }

    public void setButton(int value){
        preferences.edit().putInt("radio",value).apply();
    }
    public int getButton(int defValue){
        return preferences.getInt("radio",defValue);

    }

}
