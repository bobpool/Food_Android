package com.bobteam.bobpool.introduce;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Osy on 2018-01-23.
 */
// 뷰페이지 어뎁터
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

        switch ( position ) {
            case 0 :
                fragment = InfoFragment.newInstance ();
                break;
            case 1 :
                fragment = MenuFragment.newInstance ();
                break;
            case 2 :
                fragment = ReviewFragment.newInstance ();
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