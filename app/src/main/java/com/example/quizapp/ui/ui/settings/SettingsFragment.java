package com.example.quizapp.ui.ui.settings;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapter.AdapterTheme;
import com.example.quizapp.databinding.SettingsFragmentBinding;
import com.example.quizapp.interfac.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {
    private AdapterTheme adapterTheme;
    private List<Integer> listTheme;
    private LinearLayout layout;
    private SettingsViewModel settingsViewModel;
    SettingsFragmentBinding settingsFragmentBinding;

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
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listTheme = new ArrayList<>();
        adapterTheme = new AdapterTheme(listTheme);
        settingsFragmentBinding = SettingsFragmentBinding.bind(view);
        settingsFragmentBinding.recyclerTheme.setAdapter(adapterTheme);
        listTheme.add(R.drawable.green_theme_icon);
        listTheme.add(R.drawable.blue_theme_icon);
        listTheme.add(R.drawable.dark_theme_icon);
        listTheme.add(R.drawable.violet_theme_icon);
        listTheme.add(R.drawable.white_theme_icon);
        layout = view.findViewById(R.id.linear_clear);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ololo", "SettingsFragmentOnClick" + layout);
                App.getInstance().getDatabase().historyDao().deleteAll();
            }
        });
        adapterTheme.setOnItemClickListener(position -> {
            switch (position) {
                case 0:
                    requireActivity().setTheme(R.style.GreenTheme);
                    App.getInstance().getPreferences().setTheme(R.style.GreenTheme);
                    break;
                case 1:
                    requireActivity().setTheme(R.style.BlueTheme);
                    App.getInstance().getPreferences().setTheme(R.style.BlueTheme);
                    break;
                case 2:
                    requireActivity().setTheme(R.style.DarkTheme);
                    App.getInstance().getPreferences().setTheme(R.style.DarkTheme);
                    break;
                case 3:
                    requireActivity().setTheme(R.style.VioletTheme);
                    App.getInstance().getPreferences().setTheme(R.style.VioletTheme);
                    break;
                case 4:
                    requireActivity().setTheme(R.style.AppTheme);
                    App.getInstance().getPreferences().setTheme(R.style.AppTheme);
                    break;
            }
            Intent intent = requireActivity().getIntent();
            requireActivity().finish();
            startActivity(intent);
            Log.e("ololo", "onViewCreated: " + position);
        });


    }
}
