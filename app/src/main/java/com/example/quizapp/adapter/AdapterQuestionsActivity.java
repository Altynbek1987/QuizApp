package com.example.quizapp.adapter;

import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ListQuizHolderBinding;
import com.example.quizapp.interfac.OnAnswerClick;
import com.example.quizapp.interfac.OnItemClickListener;
import com.example.quizapp.model.ResultModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class AdapterQuestionsActivity extends RecyclerView.Adapter<AdapterQuestionsActivity.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<ResultModel> listQues = new ArrayList<>();


    public void setListQues(List<ResultModel> listQues) {
        this.listQues = listQues;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListQuizHolderBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_quiz_holder, parent, false)));
    }


    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(listQues.get(position));

    }

    @Override
    public int getItemCount() {
        return listQues.size();
    }

    public void addData(List<ResultModel> resultModels) {
        listQues.addAll(resultModels);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements OnAnswerClick {
        public static final int CORRECT_ANSWER = 1;
        public static final int INCORRECT_ANSWER = 2;
        private static final int WRONG_ANSWER = 3;

        private ListQuizHolderBinding listQuizHolderBinding;

        public ViewHolder(@NonNull ListQuizHolderBinding binding) {
            super(binding.getRoot());
            listQuizHolderBinding = binding;
            binding.setListener(this);
        }

        //@RequiresApi(api = Build.VERSION_CODES.M)
        public void onBind(ResultModel resultModel) {

            listQuizHolderBinding.btnone.setBackgroundResource(R.color.White);
            listQuizHolderBinding.btntwo.setBackgroundResource(R.color.White);
            listQuizHolderBinding.btnthree.setBackgroundResource(R.color.White);
            listQuizHolderBinding.btnfour.setBackgroundResource(R.color.White);
            listQuizHolderBinding.btnTrue.setBackgroundResource(R.color.White);
            listQuizHolderBinding.btnFalse.setBackgroundResource(R.color.White);

            if (resultModel.isChoice()){
                switch (resultModel.getUserChoice()){
                    case 0:
                        listQuizHolderBinding.btnone.setBackgroundResource(R.drawable.back_incoret_answer);
                        break;
                    case 1:
                        listQuizHolderBinding.btntwo.setBackgroundResource(R.drawable.back_incoret_answer);
                        break;
                    case 2:
                        listQuizHolderBinding.btnthree.setBackgroundResource(R.drawable.back_incoret_answer);
                        break;
                    case 3:
                        listQuizHolderBinding.btnfour.setBackgroundResource(R.drawable.back_incoret_answer);
                        break;
                }
                showCorrectButton();
            }


            listQuizHolderBinding.tvQuestion.setText(resultModel.getQuestion());
            Log.e("ololo", "onBind: " + resultModel.isChoice() + "  " + resultModel.getIncorrectAnswers().size());

            if (!resultModel.isBind()) {
                resultModel.getIncorrectAnswers().add(resultModel.getCorrectAnswer());
                Collections.shuffle(resultModel.getIncorrectAnswers());
                setClickable(true);
                resultModel.setBind(true);
            } else setClickable(false);


            if (resultModel.getIncorrectAnswers().size() == 2) {
                listQuizHolderBinding.layoutTrueFalse.setVisibility(View.VISIBLE);
                listQuizHolderBinding.layoutVariant.setVisibility(View.GONE);
                listQuizHolderBinding.btnTrue.setText(resultModel.getIncorrectAnswers().get(0));
                listQuizHolderBinding.btnFalse.setText(resultModel.getIncorrectAnswers().get(1));

            } else {
                listQuizHolderBinding.layoutVariant.setVisibility(View.VISIBLE);
                listQuizHolderBinding.layoutTrueFalse.setVisibility(View.GONE);
                listQuizHolderBinding.btnone.setText(resultModel.getIncorrectAnswers().get(0));
                listQuizHolderBinding.btntwo.setText(resultModel.getIncorrectAnswers().get(1));
                listQuizHolderBinding.btnthree.setText(resultModel.getIncorrectAnswers().get(2));
                listQuizHolderBinding.btnfour.setText(resultModel.getIncorrectAnswers().get(3));
            }
            listQuizHolderBinding.tvQuestion.setText(resultModel.getQuestion());

            listQuizHolderBinding.setModel(resultModel);
        }

        private void showCorrectButton(){
            String corAnswer = listQuizHolderBinding.getModel().getCorrectAnswer();
            int positionCorAnswer = 0;
            for (int i = 0; i < listQuizHolderBinding.getModel().getIncorrectAnswers().size(); i++) {
                if (corAnswer.equals(listQuizHolderBinding.getModel().getIncorrectAnswers().get(i)));
                positionCorAnswer = i;
            }
            switch (positionCorAnswer) {
                case 0:
                    listQuizHolderBinding.btnone.setBackgroundResource(R.drawable.back_answer);
                    break;
                case 1:
                    listQuizHolderBinding.btntwo.setBackgroundResource(R.drawable.back_answer);
                    break;
                case 2:
                    listQuizHolderBinding.btnthree.setBackgroundResource(R.drawable.back_answer);
                    break;
                case 3:
                    listQuizHolderBinding.btnfour.setBackgroundResource(R.drawable.back_answer);
                    break;
            }
        }
        private void setClickable(boolean b) {
            listQuizHolderBinding.btnone.setClickable(b);
            listQuizHolderBinding.btntwo.setClickable(b);
            listQuizHolderBinding.btnthree.setClickable(b);
            listQuizHolderBinding.btnfour.setClickable(b);
            listQuizHolderBinding.btnTrue.setClickable(b);
            listQuizHolderBinding.btnFalse.setClickable(b);
        }

        //@RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onAnswer(View view, int positionQuestion, int positionAnswer) {
            listQuizHolderBinding.getModel().setChoice(true);
            listQuizHolderBinding.getModel().setUserChoice(positionAnswer);
            setClickable(false);
            Button button = (Button) view;
            int position = 0;
            ResultModel quizModel = Objects.requireNonNull(listQues.get(getAdapterPosition()));
            String userAnswer = quizModel.getIncorrectAnswers().get(positionAnswer);
            if (userAnswer.equals(quizModel.getCorrectAnswer())) {
                if (getAdapterPosition() >= listQues.size()-1) {
                    button.setBackgroundResource(R.drawable.back_answer);
                    position = CORRECT_ANSWER;
                    Log.e(TAG, "ansverOk: " + quizModel.getCorrectAnswer());
                } else {
                    button.setBackgroundResource(R.drawable.back_answer);
                }
            } else {
                if (getAdapterPosition() >= listQues.size()-1) {
                    button.setBackgroundResource(R.drawable.back_incoret_answer);
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(5)
                            .playOn(view);
                    position = INCORRECT_ANSWER;
                } else {
                    button.setBackgroundResource(R.drawable.back_incoret_answer);
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(5)
                            .playOn(view);
                    position = WRONG_ANSWER;
                }
                Log.e(TAG, "ansverOk: " + quizModel.getIncorrectAnswers());
            }
            new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }
                @Override
                public void onFinish() {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            }.start();
        }
    }
}
