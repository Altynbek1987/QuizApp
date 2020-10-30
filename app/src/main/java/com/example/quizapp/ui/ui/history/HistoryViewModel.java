package com.example.quizapp.ui.ui.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.quizapp.model.HistoryModel;
import java.util.List;

public class HistoryViewModel extends ViewModel {
    MutableLiveData<List<HistoryModel>> mutableLiveData = new MutableLiveData<>();

}
