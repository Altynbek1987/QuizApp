package com.example.quizapp.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.AdapterQuizActivity;
import com.example.quizapp.data.Server;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.main.MainViewModel;
import com.example.quizapp.model.ModelQuiz;
import com.example.quizapp.seek_bar.SimpleSeekBarChangeListenner;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements OnItemClickListener{

    private SeekBar seekBar2;
    private RecyclerView horizontalRecyclerView;
    private AdapterQuizActivity horizontalAdapter;
    private List<ModelQuiz> listHoriz;
    private TextView textViewQuestion, tvSeekBar;
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
        horizontalAdapter = new AdapterQuizActivity(listHoriz);
        horizontalAdapter.setOnItemClickListener(this);
        horizontalRecyclerView.setAdapter(horizontalAdapter);
        seekBar2 = findViewById(R.id.seek_bar_2);
        tvSeekBar = findViewById(R.id.tv_seek_bar);
        tvSeekBar.setText("0/10");
        layout = findViewById(R.id.layout_variant);
        layout1 =findViewById(R.id.layout_true_false);
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

    }


    @Override
    public void onItemClick(int position) {
        quizViewModel.onItemClick(position);
    }
}
