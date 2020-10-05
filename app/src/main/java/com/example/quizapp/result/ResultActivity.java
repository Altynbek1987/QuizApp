package com.example.quizapp.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityResultBinding;
import com.example.quizapp.interfac.OnItemClickListener;

public class ResultActivity extends AppCompatActivity {
    public static final String KEYRESULTANSWER = "keyResultAnswer";
    ActivityResultBinding activityResultBinding;
    public static final String KEYDIFFICULY = "keyDifficuly";
    public static final String KEYAMOUNT = "keyAmount";
    private int amount;
    private int correct;

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
        activityResultBinding.tvDifficulty.setText(intent.getStringExtra(KEYDIFFICULY));
        amount = intent.getIntExtra(KEYAMOUNT, 10);
        activityResultBinding.tvTotalAnswer.setText(String.valueOf(amount));
        correct = intent.getIntExtra(KEYRESULTANSWER,10);
        activityResultBinding.tvCorrectAnswer.setText(String.valueOf(correct));

        activityResultBinding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}