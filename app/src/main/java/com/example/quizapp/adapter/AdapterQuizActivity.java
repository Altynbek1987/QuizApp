package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quizapp.R;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.model.ModelQuiz;

import java.util.List;

public class AdapterQuizActivity extends RecyclerView.Adapter<AdapterQuizActivity.ViewHolder>{
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;
    private List<ModelQuiz> listQuiz;

    public AdapterQuizActivity(List<ModelQuiz> listQuiz) {
        this.listQuiz = listQuiz;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_quiz_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindQuiz(listQuiz.get(position));
    }
    @Override
    public int getItemCount() {
        return listQuiz.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvQuestion;
        private Button btnOne,btnTwo,btnThree,btnFour;
        private Button btnTrue,btnFalse;
        private LinearLayout layout,layout1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tv_question);
            btnOne = itemView.findViewById(R.id.btnone);
            btnTwo = itemView.findViewById(R.id.btntwo);
            btnThree = itemView.findViewById(R.id.btnthree);
            btnFour = itemView.findViewById(R.id.btnfour);
            btnTrue = itemView.findViewById(R.id.btn_true);
            btnFalse = itemView.findViewById(R.id.btn_false);
            layout = itemView.findViewById(R.id.layout_variant);
            layout1 =itemView.findViewById(R.id.layout_true_false);
            onClick();
        }
        public void onClick(){
            btnOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            btnTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            btnThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            btnFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            btnTrue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            btnFalse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
        public void bindQuiz(ModelQuiz s) {
            if (s.getType() == ModelQuiz.Type.truefalse){
                layout1.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                btnTrue.setText(s.getListAnswer()[0]);
                btnFalse.setText(s.getListAnswer()[1]);
            }else {
                layout.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
                btnOne.setText(s.getListAnswer()[0]);
                btnTwo.setText(s.getListAnswer()[1]);
                btnThree.setText(s.getListAnswer()[2]);
                btnFour.setText(s.getListAnswer()[3]);
            }
            tvQuestion.setText(s.getQuestion());
        }
    }
}
