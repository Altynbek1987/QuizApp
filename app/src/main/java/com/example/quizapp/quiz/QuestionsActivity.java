package com.example.quizapp.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapter.AdapterQuestionsActivity;
import com.example.quizapp.databinding.ActivityQuizBinding;
import com.example.quizapp.interfac.OnAnswerClick;
import com.example.quizapp.model.ScoreModel;
import com.example.quizapp.result.ResultActivity;

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
        setTheme(App.getInstance().getPreferences().getTheme(R.style.AppTheme));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        activityQuizBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        //Сначала это вызываем потом вызываем quizViewModel.updateQuestion("easy", category, amount);
        quizViewModel.answerAmownt.observeForever(integer -> activityQuizBinding.horizontalRecyclerView.smoothScrollToPosition(integer));

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
        horizontalAdapter.setOnItemClickListener(position -> {
            Log.e("ololo", "onItemClick: " + position + "  " + (amountQuestions - 1));
            if (position >= amountQuestions - 1) {
                diff =intent.getStringExtra(KEYDIFFICULY);
                Log.e("ololo"," "+diff+amount+quizViewModel.corAnswer+categ + position + "  " + (amountQuestions - 1));
                Intent intent1 = new Intent(QuestionsActivity.this,ResultActivity.class);
                intent1.putExtra(ResultActivity.KEYDIFFICULY,diff);
                intent1.putExtra(ResultActivity.KEYAMOUNT,amount);
                intent1.putExtra(ResultActivity.KEYRESULTANSWER,quizViewModel.corAnswer);
                intent1.putExtra(ResultActivity.KEYNAME,categ);
                startActivityForResult(intent1, QUESTIONS_ACTIVITY_CODE);
                finish();
            } else {
                activityQuizBinding.progressBar.setProgress(activityQuizBinding.progressBar.getProgress() + 1);
                activityQuizBinding.horizontalRecyclerView.scrollToPosition(activityQuizBinding.progressBar.getProgress());
            }
        });
        quizViewModel.positionAnswer.observeForever(integer -> {
            activityQuizBinding.progressBar.setProgress(integer);
            activityQuizBinding.horizontalRecyclerView.scrollToPosition(integer);
            horizontalAdapter.notifyDataSetChanged();
        });
        activityQuizBinding.backQuestion.setOnClickListener(v -> {
            Log.e("ololo", "onClick:backQuestion ");
           quizViewModel.btnBack();
        });
        activityQuizBinding.btnSkip.setOnClickListener(v -> quizViewModel.skip());
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
