package com.bobteam.bobpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bobteam.bobpool.order.BasketProvider;
import com.bobteam.bobpool.order.BasketRecyclerViewAdapter;
import com.bobteam.bobpool.order.OrderCompleteListener;
import com.bobteam.bobpool.task.BaseTask;
import com.bobteam.bobpool.task.BaseTaskJsonRead;
import com.bobteam.bobpool.task.TaskResultListener;
import com.bobteam.bobpool.task.manager.OrderTaskManager;

import java.util.ArrayList;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

/**
 * Created by Osy on 2018-01-30.
 */

public class OrderCompleteActivity extends AppCompatActivity {
    private ArrayList<BasketProvider> providers;
    private OrderCompleteListener orderCompleteListener;

    private Spinner collegeSpinner;
    private Spinner collegeAddrSpinner;
    private EditText roomNumEdit;

    private String restaurantName;
    private String userName;
    private String menu;
    private String price;
    private String collegeAddr;
    private String roomNum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);

        providers = (ArrayList<BasketProvider>)getIntent().getSerializableExtra("basketData");
        orderCompleteListener = (OrderCompleteListener)getIntent().getSerializableExtra("listener");

        initRecyclerView();
        initExtendedPriceText();
        initAddressSetting();
        initBottomBtn();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.basket_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        BasketRecyclerViewAdapter basketRecyclerViewAdapter = new BasketRecyclerViewAdapter(providers, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recyclerView.setAdapter(basketRecyclerViewAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initExtendedPriceText(){
        TextView extendedText = findViewById(R.id.extended_price);
        int extendedPrice = 0;
        for (BasketProvider provider:
             providers) {
            extendedPrice += provider.getTotalPrice();
        }

        extendedText.setText( "" + extendedPrice);
    }

    private void initAddressSetting(){
        collegeSpinner = findViewById(R.id.college);
        ArrayList collegeArray = new ArrayList();
        collegeArray.add("공과");
        collegeArray.add("해양");
        collegeArray.add("사범");
        collegeArray.add("사회과학");
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, collegeArray);
        collegeSpinner.setAdapter(adapter);

        collegeAddrSpinner = findViewById(R.id.college_addr);
        ArrayList collegeAddrArray = new ArrayList();
        collegeAddrArray.add("1");
        collegeAddrArray.add("2");
        collegeAddrArray.add("3");
        collegeAddrArray.add("4");
        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, collegeAddrArray);
        collegeAddrSpinner.setAdapter(adapter2);

        roomNumEdit = findViewById(R.id.room_num);
    }

    private void initBottomBtn(){
        Button cancel = findViewById(R.id.back);
        Button orderOk = findViewById(R.id.order_ok);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        orderOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeTask();
            }
        });

    }

    private void executeTask(){
        BaseTask<String> baseTask = new BaseTask<>();
        OrderTaskManager manager = new OrderTaskManager();
        manager.setTaskJsonRead(new BaseTaskJsonRead());
        manager.setTaskResultListener(new TaskResultListener<String>() {
            @Override
            public void taskResult(String result) {
                if ( result.equals("fail")){
                    Toast.makeText(OrderCompleteActivity.this, "파티매칭 실패 ㅠㅠ;;", Toast.LENGTH_SHORT).show();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result","18");
                    setResult(1,returnIntent);
                    finish();
                }
                else{
                    orderCompleteListener.orderComplete();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result","complete");
                    setResult(1,returnIntent);
                    finish();
                }
            }
        });

        baseTask.setManager(manager);
        baseTask.executeOnExecutor(THREAD_POOL_EXECUTOR, restaurantName, userName, menu, price, collegeAddr, roomNum);
    }
}