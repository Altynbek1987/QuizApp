package com.example.quizapp.ui.ui.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel {
    MutableLiveData<List<HistoryModel>> mutableLiveData = new MutableLiveData<>();

//    public void updateData() {
//        List<HistoryModel> historyModels = new ArrayList<>();
//        historyModels.add(new HistoryModel("Category", "hard", "8/10", "12 may 2019 20:32"));
//        historyModels.add(new HistoryModel("Category", "hard", "8/10", "12 may 2019 20:32"));
//        historyModels.add(new HistoryModel("Category", "hard", "8/10", "12 may 2019 20:32"));
//        historyModels.add(new HistoryModel("Category", "hard", "8/10", "12 may 2019 20:32"));
//        historyModels.add(new HistoryModel("Category", "hard", "8/10", "12 may 2019 20:32"));
//        historyModels.add(new HistoryModel("Category", "hard", "8/10", "12 may 2019 20:32"));
//        mutableLiveData.setValue(historyModels);
//    }


}
