package com.example.quizapp.ui.ui.settings;

import androidx.lifecycle.ViewModelProviders;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapter.AdapterTheme;
import com.example.quizapp.databinding.SettingsFragmentBinding;
import com.example.quizapp.model.RadioModel;
import com.example.quizapp.model.ResultModel;
import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {
    private AdapterTheme adapterTheme;
    private List<RadioModel> listTheme;
    private LinearLayout layout;
    private ResultModel resultModel;
    SettingsFragmentBinding settingsFragmentBinding;
    private Button button;
    private Boolean clicked = true;
    private SettingsViewModel mViewModel;
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.btn_open_recycler);
        listTheme = new ArrayList<>();
        adapterTheme = new AdapterTheme(listTheme);
        settingsFragmentBinding = SettingsFragmentBinding.bind(view);
        settingsFragmentBinding.recyclerTheme.setAdapter(adapterTheme);
        settingsFragmentBinding.recyclerTheme.setVisibility(View.GONE);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        mViewModel.mutableLiveDataSetting.observeForever(radioModels -> {
            onChanged(radioModels);
        });
        layout = view.findViewById(R.id.linear_clear);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Вы хотите удалить историю?")
                        .setNegativeButton("Нет",null)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("ololo", "SettingsFragmentOnClick" + layout);
                                mViewModel.clearData();
                                Toast.makeText(requireContext(), "История удалено", Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();

            }
        });
        mViewModel.show.observeForever(integer -> {
            Intent intent = requireActivity().getIntent();
            requireActivity().finish();
            startActivity(intent);
        });
        adapterTheme.setOnItemClickListener(position -> {
            mViewModel.theme(position);
            //requireActivity().recreate();
            Log.e("ololo", "onViewCreated: " + position);
        });


        button.setOnClickListener(v -> {
            if (settingsFragmentBinding.recyclerTheme.getVisibility()==View.VISIBLE)
                settingsFragmentBinding.recyclerTheme.setVisibility(View.GONE);
            else settingsFragmentBinding.recyclerTheme.setVisibility(View.VISIBLE);
        });
    }

    private void onChanged(List<RadioModel> radioModels) {
        adapterTheme.addData(radioModels);
    }
}
