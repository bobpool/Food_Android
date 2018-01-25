package com.bobteam.bobpool.introduce;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.vo.RestaurantVO;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

/**
 * Created by Osy on 2018-01-22.
 */

public class IntroduceActivity extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_introduce);

        Intent intent = getIntent();
        RestaurantVO restaurantVO = (RestaurantVO)intent.getSerializableExtra("data");

        TextView titleText = findViewById(R.id.title);
        TextView score = findViewById(R.id.score);

        initViewPager();

    }

    public void initViewPager(){
        String[] tabTitles = new String[]{ "정보", "메뉴", "리뷰"};
        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter( new ViewPagerAdapter( getSupportFragmentManager(), tabTitles) );

        NavigationTabStrip tab = findViewById(R.id.tabs);
        tab.setTitles("정보", "메뉴", "리뷰");
        tab.setStripColor(Color.GRAY);
        tab.setActiveColor(Color.BLACK);
        tab.setStripWeight(8);

        tab.setViewPager(viewPager);
    }
}
