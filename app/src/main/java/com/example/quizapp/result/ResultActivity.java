package com.example.quizapp.result;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.widget.Toast;
import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityResultBinding;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.model.HistoryModel;
import java.util.ArrayList;
import java.util.Calendar;

public class ResultActivity extends AppCompatActivity {
    private ResultViewModel resultViewModel;
    public static final String KEYRESULTANSWER = "keyResultAnswer";
    public static final String KEYNAME = "keyName";
    public static final String KEY = "key";
    ActivityResultBinding activityResultBinding;
    public static final String KEYDIFFICULY = "keyDifficuly";
    public static final String KEYAMOUNT = "keyAmount";
    private int amount;
    private int correct;
    private static int RESULT_ACTIVITY_CODE = 12;
    private String diff;
    private String category;
    private HistoryModel historyModel;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    private OnItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getPreferences().getTheme(R.style.AppTheme));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        activityResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        Intent intent = getIntent();
        diff = intent.getStringExtra(KEYDIFFICULY);
        activityResultBinding.tvDifficulty.setText(diff);
        amount = intent.getIntExtra(KEYAMOUNT, 10);
        activityResultBinding.tvTotalQuiz.setText(String.valueOf(amount)); //Количество вопросов
        correct = intent.getIntExtra(KEYRESULTANSWER,10);
        activityResultBinding.tvCorrectAnswer.setText(String.valueOf(correct));//Правильные ответы
        category = intent.getStringExtra(KEYNAME);
        activityResultBinding.tvCategory.setText(category);
        historyModel=new HistoryModel(category, diff, correct, amount, new ArrayList<>(), Calendar.getInstance().getTime());
        int sto = 100;
        int result = sto*correct;
        int percent = result/amount;
        activityResultBinding.tvResult.setText(String.valueOf(percent));
        resultViewModel.resultActivity.observeForever(resultModels -> {
        });

        activityResultBinding.btnFinish.setOnClickListener(v -> {
            resultViewModel.saveResult(historyModel);
            Toast.makeText(ResultActivity.this, diff+correct, Toast.LENGTH_LONG).show();
            finish();
        });
    }
    @Override
    public void onBackPressed() {
        resultViewModel.saveResult(historyModel);
        super.onBackPressed();
    }
}