package com.bobteam.bobpool.order;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bobteam.bobpool.OrderCompleteActivity;
import com.bobteam.bobpool.R;
import com.bobteam.bobpool.introduce.adapter.MenuRecyclerViewAdapter;
import com.bobteam.bobpool.vo.MenuVO;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-30.
 */

public class OrderActivity extends AppCompatActivity{
    private ArrayList<MenuVO> menuVOS;
    private ArrayList<BasketProvider> basketProviders;

    private BasketRecyclerViewAdapter basketRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        menuVOS = ( ArrayList<MenuVO> )getIntent().getSerializableExtra("menu");
        basketProviders = new ArrayList<>();

        initButton();
        initMenuRecyclerView();
        initBasketRecyclerView();
    }

    private void initButton(){
        Button partyCreateBtn = findViewById(R.id.party_create);
        partyCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, OrderCompleteActivity.class);
                intent.putExtra("basketData", basketRecyclerViewAdapter.getProvider());

                startActivityForResult(intent,1);
//                startActivity(intent);
            }
        });
    }

    private void initMenuRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.menu_list);
        final MenuRecyclerViewAdapter adapter = new MenuRecyclerViewAdapter(menuVOS, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(OrderActivity.this);
                /*RecyclerView.LayoutParams layoutParams =
                        new RecyclerView.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT);*/

                dialog.setContentView(R.layout.dialog_food_selecting);

                final int position = (int) v.getTag();
                final TextView amountText = dialog.findViewById(R.id.amount);
                final TextView totalPrice = dialog.findViewById(R.id.total_price);
                final String foodName = menuVOS.get(position).getName();

                ( (TextView) dialog.findViewById(R.id.food_name)).setText(foodName);
                dialog.findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 문자열에서 숫자만 긁어오고 int로 변환
                        int price = Integer.parseInt( menuVOS.get(position).getPrice().replaceAll("[^0-9]", "") );
                        int amount = Integer.parseInt(String.valueOf(amountText.getText())) + 1;

                        amountText.setText( "" + amount );
                        totalPrice.setText( "" + amount * price );
                    }
                });

                dialog.findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 문자열에서 숫자만 긁어오고 int로 변환
                        int price = Integer.parseInt( menuVOS.get(position).getPrice().replaceAll("[^0-9]", "") );
                        int amount = Integer.parseInt(String.valueOf(amountText.getText())) - 1;

                        if ( amount != -1){
                            amountText.setText( "" + amount );
                            totalPrice.setText( "" + amount * price );
                        }
                    }
                });

                dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.findViewById(R.id.order_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BasketProvider basketProvider = new BasketProvider();
                        int amount = Integer.parseInt(String.valueOf(amountText.getText()));
                        int price = Integer.parseInt(String.valueOf(totalPrice.getText()));

                        basketProvider.setFoodName( menuVOS.get(position).getName() );
                        basketProvider.setAmount( amount );
                        basketProvider.setTotalPrice( price );

                        basketRecyclerViewAdapter.addData(basketProvider);

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initBasketRecyclerView(){
        basketProviders = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.basket_list);
        basketRecyclerViewAdapter = new BasketRecyclerViewAdapter(basketProviders);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(basketRecyclerViewAdapter);
    }

    private OrderCompleteListener orderCompleteListener = new OrderCompleteListener() {
        @Override
        public void orderComplete() {
            finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            String str = data.getStringExtra("result");

            if ( str.equals("complete") ){
                finish();
            }
        }
    }
}
