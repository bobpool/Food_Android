package com.bobteam.bobpool.introduce;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.introduce.adapter.ReviewRecyclerViewAdapter;
import com.bobteam.bobpool.task.BaseTask;
import com.bobteam.bobpool.task.BaseTaskJsonRead;
import com.bobteam.bobpool.task.TaskResultListener;
import com.bobteam.bobpool.task.manager.GetReviewTaskManager;
import com.bobteam.bobpool.vo.ReviewVO;

import java.util.ArrayList;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

/**
 * Created by Osy on 2018-01-23.
 */

public class ReviewFragment extends Fragment {
    private ArrayList<ReviewVO> vos;
    private ReviewRecyclerViewAdapter adapter;
    private String restaurantName;

    public static ReviewFragment newInstance(){
        ReviewFragment instance = new ReviewFragment();

        Bundle args = new Bundle();
        instance.setArguments(args);
        
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        restaurantName = getArguments().getString("name");
        vos = new ArrayList<>();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new ReviewRecyclerViewAdapter(vos, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        executeTask();
    }

    private void executeTask(){
        GetReviewTaskManager taskManager = new GetReviewTaskManager();
        taskManager.setTaskJsonRead(new BaseTaskJsonRead());
        taskManager.setTaskResultListener(new TaskResultListener<ArrayList<ReviewVO>>() {
            @Override
            public void taskResult(ArrayList<ReviewVO> result) {
                adapter.addData(result);
            }
        });

        BaseTask< ArrayList<ReviewVO> > task = new BaseTask<>();
        task.setManager(taskManager);

        task.executeOnExecutor(THREAD_POOL_EXECUTOR, restaurantName);
    }
}
