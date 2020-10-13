package com.example.quizapp.ui.ui.main;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.databinding.MainFragmentBinding;
import com.example.quizapp.model.TriviaCategory;
import com.example.quizapp.quiz.QuestionsActivity;
import com.example.quizapp.R;
import com.example.quizapp.seek_bar.SimpleSeekBarChangeListenner;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private SeekBar seekBarr;
    private TextView textView;
    private Button buttonStart;
    private ImageView iconPlus, iconMinus;
    private static int MAIN_FRAGMENT_CODE = 1;
    private Spinner spinner, spinnerDifficulty;
    private Integer category;
    private String nameCategoryTitleQuestionActivity;
    MainFragmentBinding mainFragmentBinding;
    private String difficul;


    private MainViewModel mViewModel;
    private int amount;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = MainFragmentBinding.bind(inflater.inflate(R.layout.main_fragment, container, false));
        return mainFragmentBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("fragment name", "Main Fragment");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = view.findViewById(R.id.category_spinner);
        spinnerDifficulty = view.findViewById(R.id.difficulty_spinner);
        iconPlus = view.findViewById(R.id.icon_plus);
        iconMinus = view.findViewById(R.id.icon_minus);
        buttonStart = view.findViewById(R.id.start_game);
        textView = view.findViewById(R.id.seek_text);
        textView.setText("0");
        seekBarr = view.findViewById(R.id.seek_bar);
        onClick();
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.updateCategory();
        mViewModel.mutableLiveData.observeForever(integer -> {
            textView.setText(integer+"");
            seekBarr.setProgress(integer);
            amount = integer;
        });

        spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                difficul = getResources().getStringArray(R.array.Level)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mViewModel.mutableCategory.observeForever(modelCategory -> {
            List<TriviaCategory> categoryList = modelCategory.getTriviaCategories();
            List<String> name_category = new ArrayList<>();
            for (TriviaCategory triviaCategory : categoryList) {
                name_category.add(triviaCategory.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.support_simple_spinner_dropdown_item, name_category);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    category = modelCategory.getTriviaCategories().get(position).getId();
                    nameCategoryTitleQuestionActivity = modelCategory.getTriviaCategories().get(position).getName();

                    Toast.makeText(requireContext(), "Selected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });
    }

    private void onClick() {

        seekBarr.setOnSeekBarChangeListener(new SimpleSeekBarChangeListenner(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                mViewModel.mutableLiveData.setValue(progress);
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ololo", "buttonStart");
                Intent intent = new Intent(requireContext(), QuestionsActivity.class);
                intent.putExtra(QuestionsActivity.KEY, category.intValue());
                intent.putExtra(QuestionsActivity.KEYNAME,nameCategoryTitleQuestionActivity);
                intent.putExtra(QuestionsActivity.KEYDIFFICULY,difficul);
                intent.putExtra(QuestionsActivity.KEYAMOUNT,amount);
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
