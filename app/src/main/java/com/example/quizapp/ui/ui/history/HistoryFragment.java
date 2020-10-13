package com.example.quizapp.ui.ui.history;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.R;
import com.example.quizapp.adapter.AdapterHistory;
import com.example.quizapp.databinding.HistoryFragmentBinding;

public class HistoryFragment extends Fragment {

    private AdapterHistory adapterHistory;
    private HistoryViewModel mViewModel;
    HistoryFragmentBinding historyFragmentBinding;


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        historyFragmentBinding = HistoryFragmentBinding.bind(inflater.inflate(R.layout.history_fragment, container, false));
        return historyFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        super.onActivityCreated(savedInstanceState);
        adapterHistory = new AdapterHistory();
        historyFragmentBinding.recyclerView.setAdapter(adapterHistory);
       // mViewModel.updateData();
        mViewModel.mutableLiveData.observeForever(historyModels -> {
            adapterHistory.setData(historyModels);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
