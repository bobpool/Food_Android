package com.bobteam.bobpool.introduce;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobteam.bobpool.R;

/**
 * Created by Osy on 2018-01-23.
 */

public class MenuFragment extends Fragment {

    public static MenuFragment newInstance(){
        MenuFragment instance = new MenuFragment();

        Bundle args = new Bundle();
        instance.setArguments(args);

        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        return view;
    }
}
