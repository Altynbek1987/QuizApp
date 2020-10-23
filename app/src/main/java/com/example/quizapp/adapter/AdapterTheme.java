package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.HistoryHolderBinding;
import com.example.quizapp.databinding.ListThemeHolderBinding;
import com.example.quizapp.interfac.OnItemClickListener;

import java.util.List;

public class AdapterTheme extends RecyclerView.Adapter<AdapterTheme.ThemeViewHolder>{

    List<Integer>list;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AdapterTheme(List<Integer> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThemeViewHolder(ListThemeHolderBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_theme_holder, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThemeViewHolder extends RecyclerView.ViewHolder {
        ListThemeHolderBinding listThemeHolderBinding;

        public ThemeViewHolder(@NonNull ListThemeHolderBinding binding) {
            super(binding.getRoot());
            listThemeHolderBinding = binding;
            binding.getRoot().setOnClickListener(v -> {
                binding.radio1.setChecked(true);
                onItemClickListener.onItemClick(getAdapterPosition());
            });
        }

        public void bind(int integer) {
            listThemeHolderBinding.imageTheme.setImageDrawable(ContextCompat.getDrawable(listThemeHolderBinding.getRoot().getContext(), integer));

        }
    }
}