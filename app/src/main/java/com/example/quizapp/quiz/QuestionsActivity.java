package com.example.quizapp.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.AdapterQuestionsActivity;
import com.example.quizapp.databinding.ActivityQuizBinding;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.model.ModelQuiz;
import com.example.quizapp.result.ResultActivity;

import java.util.List;

import static com.example.quizapp.adapter.AdapterQuestionsActivity.ViewHolder.CORRECT_ANSWER;

public class QuestionsActivity extends AppCompatActivity{
    public static final String KEYNAME = "keyName";
    public static final String KEYDIFFICULY = "keyDifficuly";
    public static final String KEYAMOUNT = "keyAmount";
    private ProgressBar progressBar;
    private RecyclerView horizontalRecyclerView;
    private AdapterQuestionsActivity horizontalAdapter;
    private List<ModelQuiz> listHoriz;
    private TextView textViewQuestion, tvSeekBar, tvCategory;
    public static final String KEY = "key";
    private LinearLayout layout, layout1;
    private QuizViewModel quizViewModel;
    private int amount;
    private int amountQuestions;
    private static int QUESTIONS_ACTIVITY_CODE = 10;
    ActivityQuizBinding activityQuizBinding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        activityQuizBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        tvCategory = findViewById(R.id.tv_category);
        progressBar = findViewById(R.id.progress_bar);
        tvSeekBar = findViewById(R.id.tv_seek_bar);
        layout = findViewById(R.id.layout_variant);
        layout1 = findViewById(R.id.layout_true_false);
        textViewQuestion = findViewById(R.id.tv_question);
        String diff = KEYDIFFICULY;
        quizViewModel.answerAmownt.observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                horizontalRecyclerView.smoothScrollToPosition(integer);
            }
        });
//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(activityQuizBinding.horizontalRecyclerView);

        Intent intent = getIntent();
        int category = intent.getIntExtra(KEY, 9);
        tvCategory.setText(intent.getStringExtra(KEYNAME));
        tvSeekBar.setText(intent.getStringExtra(KEYDIFFICULY));
        int amount = intent.getIntExtra(KEYAMOUNT, 10);
        activityQuizBinding.progressBar.setMax(amount);
        activityQuizBinding.progressBar.setProgress(0);
        horizontalAdapter = new AdapterQuestionsActivity();
        activityQuizBinding.horizontalRecyclerView.setAdapter(horizontalAdapter);
        quizViewModel.mutableQuestions.observeForever(modelQuestions -> {
            horizontalAdapter.addData(modelQuestions);
            amountQuestions = modelQuestions.size();
        });
        quizViewModel.updateQuestion("easy", category, amount);
        horizontalAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e("ololo", "onItemClick: " + position + "  " + (amountQuestions - 1));
                if (position >= amountQuestions - 1) {
                    Log.e("ololo", "onIhhhhhhhhhhhhhhhhhhhhhhhhhhhtemClick: " + position + "  " + (amountQuestions - 1));
                    Intent intent = new Intent(QuestionsActivity.this,ResultActivity.class);
                    intent.putExtra(ResultActivity.KEYDIF,diff);
                    startActivityForResult(intent, QUESTIONS_ACTIVITY_CODE);
                    startActivity(new Intent(QuestionsActivity.this, ResultActivity.class));

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
}
