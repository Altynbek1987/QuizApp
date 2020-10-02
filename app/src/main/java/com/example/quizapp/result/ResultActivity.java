package com.example.quizapp.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quizapp.R;

public class ResultActivity extends AppCompatActivity {
    private TextView tvDifficulty;

    public static final String KEYDIF = "keyDif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvDifficulty = findViewById(R.id.tv_difficulty);
        Intent intent = getIntent();
        tvDifficulty.setText(intent.getStringExtra(KEYDIF));


    }
}