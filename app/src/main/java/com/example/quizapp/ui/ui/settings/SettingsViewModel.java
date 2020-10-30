package com.example.quizapp.ui.ui.settings;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.model.RadioModel;

import java.util.ArrayList;
import java.util.List;

public class SettingsViewModel extends ViewModel {
   MutableLiveData <List<RadioModel>>mutableLiveDataSetting = new MutableLiveData<>();
   MutableLiveData <Integer> show = new MutableLiveData<>();

    public SettingsViewModel() {
        update();
    }

    public void clearData(){
        App.getInstance().getDatabase().historyDao().deleteAll();
   }
   public void update(){
       List<RadioModel> listTheme = new ArrayList<>();
       listTheme.add(new RadioModel(R.drawable.green_theme_icon,false));
       listTheme.add(new RadioModel(R.drawable.blue_theme_icon,false));
       listTheme.add(new RadioModel(R.drawable.dark_theme_icon,false));
       listTheme.add(new RadioModel(R.drawable.violet_theme_icon,false));
       listTheme.add(new RadioModel(R.drawable.white_theme_icon,false));
       listTheme.get(App.getInstance().getPreferences().getButton(0)).setChange(true);
       mutableLiveDataSetting.setValue(listTheme);
   }
   public void theme(int position){
       switch (position) {
           case 0:
               //requireActivity().setTheme(R.style.GreenTheme);
               // mutableLiveDataSetting.getValue().equals(R.style.GreenTheme);
               if (App.getInstance().getPreferences().getTheme(position) != R.style.GreenTheme) {
                   App.getInstance().getPreferences().setTheme(R.style.GreenTheme);
                   show.setValue(R.style.GreenTheme);
               }
               break;
           case 1:
               if (App.getInstance().getPreferences().getTheme(position) != R.style.BlueTheme) {
                   App.getInstance().getPreferences().setTheme(R.style.BlueTheme);
                   show.setValue(R.style.BlueTheme);
               }
               break;
           case 2:
               if (App.getInstance().getPreferences().getTheme(position) != R.style.DarkTheme) {
                   App.getInstance().getPreferences().setTheme(R.style.DarkTheme);
                   show.setValue(R.style.DarkTheme);
               }
               break;
           case 3:
               if (App.getInstance().getPreferences().getTheme(position) != R.style.VioletTheme) {
                   App.getInstance().getPreferences().setTheme(R.style.VioletTheme);
                   show.setValue(R.style.VioletTheme);
               }
               break;
           case 4:
               if (App.getInstance().getPreferences().getTheme(position) != R.style.AppTheme) {
                   App.getInstance().getPreferences().setTheme(R.style.AppTheme);
                   show.setValue(R.style.AppTheme);
               }
               Log.e("ololo", "Norm");
               break;
       }
       App.getInstance().getPreferences().setButton(position);
//       Log.e("ololo", "Norm" + App.getInstance().getPreferences().getButton());
   }
}
