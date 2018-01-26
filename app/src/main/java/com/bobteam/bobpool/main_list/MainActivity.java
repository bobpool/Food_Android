package com.bobteam.bobpool.main_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.UserSettingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();
        initFragment();
    }

    private void initFragment(){
        RecyclerFragment recyclerFragment = new RecyclerFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_list,recyclerFragment );
        fragmentTransaction.commit();
    }

    private void initButton(){
        Button addressSetting = findViewById(R.id.address_setting);
        addressSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserSettingActivity.class);

                startActivity(intent);
            }
        });
    }
}
