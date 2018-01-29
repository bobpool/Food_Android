package com.bobteam.bobpool.main_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.introduce.IntroduceActivity;
import com.bobteam.bobpool.task.BaseTask;
import com.bobteam.bobpool.task.BaseTaskJsonRead;
import com.bobteam.bobpool.task.TaskResultListener;
import com.bobteam.bobpool.task.manager.GetRestaurantsTaskManager;
import com.bobteam.bobpool.vo.RestaurantVO;

import java.util.ArrayList;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

/**
 * Created by Osy on 2018-01-14.
 */

public class RecyclerFragment extends Fragment {
    private ArrayList<RestaurantVO> restaurantVOS;

    private RestaurantRecyclerViewAdapter restaurantRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        restaurantVOS = new ArrayList<>();

        executeTask();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        restaurantRecyclerViewAdapter = new RestaurantRecyclerViewAdapter(restaurantVOS ,itemClickListener);
        recyclerView.setAdapter(restaurantRecyclerViewAdapter);
    }

    private void executeTask(){
        GetRestaurantsTaskManager manager = new GetRestaurantsTaskManager();

        manager.setTaskJsonRead(new BaseTaskJsonRead());
        manager.setTaskResultListener(new TaskResultListener<ArrayList<RestaurantVO>>() {
            @Override
            public void taskResult(ArrayList<RestaurantVO> result) {
                restaurantVOS.addAll(result);

                restaurantRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        BaseTask<ArrayList<RestaurantVO>> getRestaurantsTask = new BaseTask<>();
        getRestaurantsTask.setManager(manager);

        getRestaurantsTask.executeOnExecutor(THREAD_POOL_EXECUTOR, "restaurant_tb", "");
    }

    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), IntroduceActivity.class);
            intent.putExtra("data", (String) view.getTag() );
            //나중에 데이터 얹어서 추가로 보낼것.

            startActivity(intent);
        }
    };
}