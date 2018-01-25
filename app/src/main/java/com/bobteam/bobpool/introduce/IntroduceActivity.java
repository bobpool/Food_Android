package com.bobteam.bobpool.introduce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.bobteam.bobpool.R;
import com.bobteam.bobpool.task.BaseJsonRead;
import com.bobteam.bobpool.task.BaseTask;
import com.bobteam.bobpool.task.TaskManager;
import com.bobteam.bobpool.task.TestParsing;
import com.bobteam.bobpool.task.TestUrlSetting;
import com.bobteam.bobpool.vo.RestaurantVO;
import com.bobteam.bobpool.vo.UserVO;

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

        PagerSlidingTabStrip tab = findViewById(R.id.tabs);
        tab.setViewPager(viewPager);
    }


    private void taskStart(){
        BaseTask<UserVO> task = new BaseTask<>();

        TaskManager<UserVO> manager = new TaskManager<>();
            manager.setUrlSetting( new TestUrlSetting());
            manager.setJsonRead( new BaseJsonRead());
            manager.setJsonParsing( new TestParsing());

        task.setManager(manager);
        task.execute();
    }
}
