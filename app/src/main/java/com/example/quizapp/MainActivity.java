package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.quizapp.adapter.MainPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MainPagerAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getPreferences().getTheme(R.style.AppTheme));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.main_view_pager);
        toolbar = findViewById(R.id.widget_toolbar);
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        toolbar.setTitle("Quiz");
        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main_btn:
                        viewPager.setCurrentItem(0,false);
                        toolbar.setTitle("Quiz");
                        break;
                    case R.id.history_btn:
                        viewPager.setCurrentItem(1,false);
                        toolbar.setTitle("History");
                        break;
                    case R.id.settings_btn:
                        viewPager.setCurrentItem(2,false);
                        toolbar.setTitle("Settings");
                        break;
                }
                return true;
            }
        });




    }
}
