package com.example.quizapp.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.quizapp.MainActivity;
import com.example.quizapp.QuizActivity;
import com.example.quizapp.R;

import java.sql.ResultSet;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment {
    private SeekBar seekBarr;
    private TextView textView;
    private Button buttonStart;
    private ImageView iconPlus, iconMinus;
    private static int MAIN_FRAGMENT_CODE = 1;


    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("fragment name", "Main Fragment");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iconPlus = view.findViewById(R.id.icon_plus);
        iconMinus = view.findViewById(R.id.icon_minus);
        buttonStart = view.findViewById(R.id.start_game);
        textView = view.findViewById(R.id.seek_text);
        textView.setText("0");
        seekBarr = view.findViewById(R.id.seek_bar);
        onClick();
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.mutableLiveData.observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(integer+"");
                seekBarr.setProgress(integer);

            }
        });
    }

    private void onClick() {
        seekBarr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mViewModel.mutableLiveData.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ololo", "buttonStart");
                Intent intent = new Intent(requireContext(), QuizActivity.class);
                intent.putExtra(QuizActivity.KEY, " ");
                startActivityForResult(intent, MAIN_FRAGMENT_CODE);
            }
        });
        iconPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.plus();
            }
        });
        iconMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.minus();
            }
        });
    }
}
