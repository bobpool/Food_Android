package com.bobteam.bobpool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bobteam.bobpool.order.BasketProvider;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-30.
 */

public class OrderCompleteActivity extends AppCompatActivity {
    private ArrayList<BasketProvider> providers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);

        providers = (ArrayList<BasketProvider>)getIntent().getSerializableExtra("basketData");
    }
}
