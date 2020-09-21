package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;

import java.util.ArrayList;

public class AdapterHistoryFragment extends RecyclerView.Adapter<AdapterHistoryFragment.VievHolder>{
    public AdapterHistoryFragment(ArrayList<String> list) {
        this.list = list;
    }

    private ArrayList<String> list;

    @NonNull
    @Override
    public VievHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_holder,parent,false);
        return new VievHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VievHolder holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VievHolder extends RecyclerView.ViewHolder{
        private TextView tv_category,tv_answers,tv_difficulty;

        public VievHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_answers = itemView.findViewById(R.id.tv_answers);
            tv_difficulty = itemView.findViewById(R.id.tv_difficulty);
        }

        public void bind(String s) {

        }
    }
}
