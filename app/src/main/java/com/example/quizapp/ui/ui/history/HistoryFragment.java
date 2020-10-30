package com.example.quizapp.ui.ui.history;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapter.AdapterHistory;
import com.example.quizapp.databinding.HistoryFragmentBinding;
import com.example.quizapp.interfac.OnLongClickListener;
import com.example.quizapp.model.HistoryModel;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private AdapterHistory adapterHistory;
    private HistoryViewModel mViewModel;
    HistoryFragmentBinding historyFragmentBinding;
    private ArrayList<HistoryModel> historyList = new ArrayList<>();


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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterHistory = new AdapterHistory(historyList);
        historyFragmentBinding.recyclerView.setAdapter(adapterHistory);
        App.getInstance().getDatabase().historyDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<HistoryModel>>() {
            @Override
            public void onChanged(List<HistoryModel> historyModels) {
                historyList.clear();
                historyList.addAll(historyModels);
                adapterHistory.notifyDataSetChanged();
                Log.d("anime", historyList.toString());
            }
        });

        adapterHistory.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public void onLongItemClick(int position) {
                showAlert(historyList.get(position));
            }
            private void showAlert(final HistoryModel historyModel) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Вы хотите удалить?")
                        .setNegativeButton("Нет", null)
                        .setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                App.getInstance().getDatabase().historyDao().delete(historyModel);
                            }
                        });
                builder.show();
            }
        });
    }
}
