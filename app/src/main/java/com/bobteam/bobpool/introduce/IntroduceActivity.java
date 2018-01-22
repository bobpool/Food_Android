package com.bobteam.bobpool.introduce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.bobteam.bobpool.R;

/**
 * Created by Osy on 2018-01-22.
 */

public class IntroduceActivity extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_introduce);

        String[] tabTitles = new String[]{ "정보", "메뉴", "리뷰"};
        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter( new ViewPagerAdapter( getSupportFragmentManager(), tabTitles) );

        PagerSlidingTabStrip tab = findViewById(R.id.tabs);
        tab.setViewPager(viewPager);
    }
}
