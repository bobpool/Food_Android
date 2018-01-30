package com.bobteam.bobpool.introduce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.introduce.adapter.MenuRecyclerViewAdapter;
import com.bobteam.bobpool.order.OrderActivity;
import com.bobteam.bobpool.task.BaseTask;
import com.bobteam.bobpool.task.BaseTaskJsonRead;
import com.bobteam.bobpool.task.TaskResultListener;
import com.bobteam.bobpool.task.manager.GetMenuTaskManager;
import com.bobteam.bobpool.vo.MenuVO;

import java.util.ArrayList;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

/**
 * Created by Osy on 2018-01-23.
 */

public class MenuFragment extends Fragment {
    private ArrayList<MenuVO> vos;
    private MenuRecyclerViewAdapter adapter;
    private String restaurantName;

    public static MenuFragment newInstance(){
        MenuFragment instance = new MenuFragment();

        Bundle args = new Bundle();
        instance.setArguments(args);

        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        vos = new ArrayList<>();
        restaurantName = getArguments().getString("name");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView(view);
        initButton(view);
        executeTask();
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new MenuRecyclerViewAdapter(vos, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initButton(View view){
        Button partyOrder = view.findViewById(R.id.order_party);
        partyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("menu", vos);

                startActivity(intent);
            }
        });

        Button normalOrder = view.findViewById(R.id.order_normal);
        normalOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void executeTask(){
        GetMenuTaskManager taskManager = new GetMenuTaskManager();
        taskManager.setTaskJsonRead(new BaseTaskJsonRead());
        taskManager.setTaskResultListener(new TaskResultListener<ArrayList<MenuVO>>() {
            @Override
            public void taskResult(ArrayList<MenuVO> result) {
                adapter.addData(result);
            }
        });

        BaseTask< ArrayList<MenuVO> > task = new BaseTask<>();
        task.setManager(taskManager);

        task.executeOnExecutor(THREAD_POOL_EXECUTOR, restaurantName);
    }
}