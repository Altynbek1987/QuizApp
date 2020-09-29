package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.HistoryHolderBinding;
import com.example.quizapp.model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.HistoryViewHolder>{

    private List<HistoryModel> data = new ArrayList<>();

    public void setData(List<HistoryModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(HistoryHolderBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_holder,parent,false)));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bind(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        HistoryHolderBinding historyHolderBinding;
        public HistoryViewHolder(@NonNull HistoryHolderBinding binding) {
            super(binding.getRoot());
            historyHolderBinding = binding;

        }
        public void bind(HistoryModel historyModel){
            historyHolderBinding.tvCategory.setText(historyModel.getCategory());
        }
    }
}
