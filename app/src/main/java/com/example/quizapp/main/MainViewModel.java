package com.example.quizapp.main;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public void test(){
        String result;
        int firstNumber = 1;
        int secondNumber = 1;
        result = String.valueOf(firstNumber + secondNumber);
    }
}
