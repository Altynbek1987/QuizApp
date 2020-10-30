package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ListThemeHolderBinding;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.model.RadioModel;

import java.util.List;

public class AdapterTheme extends RecyclerView.Adapter<AdapterTheme.ThemeViewHolder>{

    List<RadioModel>list;

    OnItemClickListener onItemClickListener;

    public void setList(List<RadioModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AdapterTheme(List<RadioModel> list) {
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

    public void addData(List<RadioModel> radioModels) {
        list.clear();
        list.addAll(radioModels);
        notifyDataSetChanged();
    }

    public class ThemeViewHolder extends RecyclerView.ViewHolder {
        ListThemeHolderBinding listThemeHolderBinding;

        public ThemeViewHolder(@NonNull ListThemeHolderBinding binding) {
            super(binding.getRoot());
            listThemeHolderBinding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.radio1.setChecked(!binding.radio1.isChecked());
                    onItemClickListener.onItemClick(ThemeViewHolder.this.getAdapterPosition());
                    App.getInstance().getPreferences().getButton(0);
                }
            });
            binding.radio1.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked)onItemClickListener.onItemClick(getAdapterPosition());
            });
        }

        public void bind(RadioModel radioModel) {
            listThemeHolderBinding.imageTheme.setImageDrawable(ContextCompat.getDrawable(listThemeHolderBinding.getRoot().getContext(), radioModel.getIconTheme()));
            listThemeHolderBinding.radio1.setChecked(radioModel.isChange());
        }


    }
}