package com.example.quizapp.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapter.AdapterQuizActivity;
import com.example.quizapp.data.OpentdbService;
import com.example.quizapp.data.Server;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.main.MainViewModel;
import com.example.quizapp.model.ModelCategory;
import com.example.quizapp.model.ModelQuestions;
import com.example.quizapp.model.ModelQuiz;
import com.example.quizapp.seek_bar.SimpleSeekBarChangeListenner;

import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements OnItemClickListener, OpentdbService.QuestionCallback {

    public static final String KEYNAME = "keyName";
    private SeekBar seekBar2;
    private RecyclerView horizontalRecyclerView;
    private AdapterQuizActivity horizontalAdapter;
    private List<ModelQuiz> listHoriz;
    private TextView textViewQuestion, tvSeekBar,tvCategory;
    public static final String KEY = "key";
    private ModelQuiz modelQuiz;
    private LinearLayout layout,layout1;
    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        horizontalRecyclerView = findViewById(R.id.horizontal_recyclerView);
        listHoriz = Server.getListQuiz();
        tvCategory = findViewById(R.id.tv_category);
        horizontalAdapter = new AdapterQuizActivity(listHoriz);
        horizontalAdapter.setOnItemClickListener(this);
        horizontalRecyclerView.setAdapter(horizontalAdapter);
        seekBar2 = findViewById(R.id.seek_bar_2);
        tvSeekBar = findViewById(R.id.tv_seek_bar);
        tvSeekBar.setText("0/10");
        layout = findViewById(R.id.layout_variant);
        layout1 = findViewById(R.id.layout_true_false);
        textViewQuestion = findViewById(R.id.tv_question);
        seekBar2.setOnSeekBarChangeListener(new SimpleSeekBarChangeListenner() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                tvSeekBar.setText(" " + progress + "/10 ");
            }
        });
        quizViewModel.answerAmownt.observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                horizontalRecyclerView.smoothScrollToPosition(integer);
            }
        });
        Intent intent =getIntent();
        int res = intent.getIntExtra(KEY,9);
        tvCategory.setText(intent.getStringExtra(KEYNAME));
        Toast.makeText(this, "" + res, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onItemClick(int position) {
        quizViewModel.onItemClick(position);
    }

    @Override
    public void onSuccess(List<ModelQuestions> body) {

    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onResponse(ModelCategory question) {

    }
}
