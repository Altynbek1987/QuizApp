package com.example.quizapp.quiz;

import androidx.annotation.IntegerRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.AdapterQuestionsActivity;
import com.example.quizapp.databinding.ActivityQuizBinding;
import com.example.quizapp.interfac.OnAnswerClick;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.model.ModelQuiz;
import com.example.quizapp.model.ScoreModel;
import com.example.quizapp.result.ResultActivity;

import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements OnAnswerClick {
    public static final String KEYNAME = "keyName";
    public static final String KEYDIFFICULY = "keyDifficuly";
    public static final String KEYAMOUNT = "keyAmount";
    private AdapterQuestionsActivity horizontalAdapter;
    public static final String KEY = "key";
    private QuizViewModel quizViewModel;
    private int amountQuestions;
    private static int QUESTIONS_ACTIVITY_CODE = 10;
    private String diff;
    private String categ;
    private ScoreModel scoreModel;
    ActivityQuizBinding activityQuizBinding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        activityQuizBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        quizViewModel.answerAmownt.observeForever(new Observer<Integer>() { //Сначала это вызываем потом вызываем quizViewModel.updateQuestion("easy", category, amount);
            @Override
            public void onChanged(Integer integer) {
                activityQuizBinding.horizontalRecyclerView.smoothScrollToPosition(integer);
            }
        });

//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(activityQuizBinding.horizontalRecyclerView);

        Intent intent = getIntent();
        int category = intent.getIntExtra(KEY, 9);
        categ=intent.getStringExtra(KEYNAME);
        activityQuizBinding.tvCategory.setText(categ);
        Log.e("log"," "+categ);
        activityQuizBinding.tvProgressBar.setText(intent.getStringExtra(KEYDIFFICULY));
        int amount = intent.getIntExtra(KEYAMOUNT, 10);

        activityQuizBinding.progressBar.setMax(amount);
        activityQuizBinding.progressBar.setProgress(0);
        horizontalAdapter = new AdapterQuestionsActivity();
        horizontalAdapter.setListener(this);
        activityQuizBinding.horizontalRecyclerView.setAdapter(horizontalAdapter);
        quizViewModel.mutableQuestions.observeForever(modelQuestions -> {
            horizontalAdapter.addData(modelQuestions);
            amountQuestions = modelQuestions.size();
        });
        quizViewModel.updateQuestion("easy", category, amount); //Вызываем после quizViewModel.answerAmownt.observeForever(new Observer<Integer>());

        horizontalAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e("ololo", "onItemClick: " + position + "  " + (amountQuestions - 1));
                if (position >= amountQuestions - 1) {
                    diff =intent.getStringExtra(KEYDIFFICULY);
                    Log.e("ololo"," "+diff+amount+quizViewModel.corAnswer+categ + position + "  " + (amountQuestions - 1));
                    Intent intent = new Intent(QuestionsActivity.this,ResultActivity.class);
                    intent.putExtra(ResultActivity.KEYDIFFICULY,diff);
                    intent.putExtra(ResultActivity.KEYAMOUNT,amount);
                    intent.putExtra(ResultActivity.KEYRESULTANSWER,quizViewModel.corAnswer);
                    intent.putExtra(ResultActivity.KEYNAME,categ);
                    startActivityForResult(intent, QUESTIONS_ACTIVITY_CODE);
                    finish();
                } else {
                    activityQuizBinding.progressBar.setProgress(activityQuizBinding.progressBar.getProgress() + 1);
                    activityQuizBinding.horizontalRecyclerView.scrollToPosition(activityQuizBinding.progressBar.getProgress());
                }
            }

        });
        activityQuizBinding.backQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ololo", "onClick:backQuestion ");
                activityQuizBinding.progressBar.setProgress(activityQuizBinding.progressBar.getProgress() - 1);
                activityQuizBinding.horizontalRecyclerView.scrollToPosition(activityQuizBinding.progressBar.getProgress());

            }
        });
        activityQuizBinding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ololo", "onClick:btnSkip ");
                activityQuizBinding.progressBar.setProgress(activityQuizBinding.progressBar.getProgress() + 1);
                activityQuizBinding.horizontalRecyclerView.scrollToPosition(activityQuizBinding.progressBar.getProgress());
            }
        });
    }

    @Override
    public void onAnswer(View view, int positionQuestion, int positionAnswer) {

    }

    @Override
    public void correctAnswer(boolean b) {
        if (b) {
            quizViewModel.correctAnswerr();
        }
    }

}
