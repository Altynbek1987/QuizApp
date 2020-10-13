package com.example.quizapp.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.TextView;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityResultBinding;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.model.HistoryModel;

public class ResultActivity extends AppCompatActivity {
    public static final String KEYRESULTANSWER = "keyResultAnswer";
    public static final String KEYNAME = "keyName";
    public static final String KEY = "key";
    ActivityResultBinding activityResultBinding;
    public static final String KEYDIFFICULY = "keyDifficuly";
    public static final String KEYAMOUNT = "keyAmount";
    private int amount;
    private int correct;
    private String diff;
   private HistoryModel historyModel;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        activityResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_result);

        Intent intent = getIntent();
        diff = intent.getStringExtra(KEYDIFFICULY);
        activityResultBinding.tvDifficulty.setText(diff);
        amount = intent.getIntExtra(KEYAMOUNT, 10);
        activityResultBinding.tvTotalQuiz.setText(String.valueOf(amount)); //Количество вопросов
        correct = intent.getIntExtra(KEYRESULTANSWER,10);
        activityResultBinding.tvCorrectAnswer.setText(String.valueOf(correct));//Правильные ответы

        activityResultBinding.tvCategory.setText(intent.getStringExtra(KEYNAME));

        int sto = 100;
        int result = sto*correct;
        int percent = result/amount;
        activityResultBinding.tvResult.setText(String.valueOf(percent));

        activityResultBinding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRoom();
                App.getInstance().getDatabase().historyDao().insert(historyModel);
            }
        });

    }
    public void saveRoom(){
        //Не работает
        historyModel.setCorrectAns(correct);
        historyModel.setAmount_quiz(amount);
        historyModel.setCategory(diff);
    }
}