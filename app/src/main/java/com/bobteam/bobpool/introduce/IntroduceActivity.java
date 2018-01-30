package com.bobteam.bobpool.introduce;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.vo.RestaurantVO;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

/**
 * Created by Osy on 2018-01-22.
 */

public class IntroduceActivity extends AppCompatActivity{
    private RestaurantVO restaurantVO;

    private MenuFragment menuFragment;
    private ReviewFragment reviewFragment;
    private String restaurantName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_introduce);

        Intent intent = getIntent();
        restaurantName = intent.getStringExtra("data");

        initViewPager();
        initFragment();
    }

    private void initViewPager(){
        String[] tabTitles = new String[]{ "정보", "메뉴", "리뷰"};
        ViewPager viewPager = findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter( getSupportFragmentManager(), tabTitles);
        viewPager.setAdapter( pagerAdapter );

        NavigationTabStrip tab = findViewById(R.id.tabs);
        tab.setTitles("정보", "메뉴", "리뷰");
        tab.setStripColor(Color.GRAY);
        tab.setActiveColor(Color.BLACK);
        tab.setStripWeight(8);

        tab.setViewPager(viewPager);
    }

    private void initFragment(){
        menuFragment = MenuFragment.newInstance();
        reviewFragment = ReviewFragment.newInstance();
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private String[] tabTitles;
        private int maxPage;


        public ViewPagerAdapter (FragmentManager fm , String[] tabTitles) {
            super ( fm );
            this.tabTitles = tabTitles;
            this.maxPage = tabTitles.length;
        }

        @Override
        public Fragment getItem (int position ) {
            Fragment fragment;
            if ( position < 0 || maxPage <= position )
                return null;

            Bundle bundle = new Bundle();
            bundle.putString("name", restaurantName);

            switch ( position ) {
                case 0 :
                    fragment = InfoFragment.newInstance ();
                    break;
                case 1 :
                    fragment = menuFragment;
                    menuFragment.setArguments(bundle);
                    break;
                case 2 :
                    fragment = reviewFragment;
                    reviewFragment.setArguments(bundle);
                    break;
                default:
                    fragment = null;
            }
            return fragment;
        }

        @Override
        public int getCount ( ){
            return maxPage;
        }

        @Override
        public CharSequence getPageTitle ( int position ) {
            return tabTitles[position];
        }
    }
}
