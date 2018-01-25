package com.bobteam.bobpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobteam.bobpool.introduce.IntroduceActivity;
import com.bobteam.bobpool.list.ListProvider;
import com.bobteam.bobpool.list.MyListAdapter;
import com.bobteam.bobpool.vo.RestaurantVO;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-14.
 */

public class RecyclerFragment extends Fragment {
    private ArrayList<ListProvider> providers;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        testProvider();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        MyListAdapter myListAdapter = new MyListAdapter(providers, itemClickListener);
        recyclerView.setAdapter(myListAdapter);
    }

    public void testProvider(){
        providers = new ArrayList<>();

        providers.add(new ListProvider().setName("숲노을").setAddress("제대 후문 치킨 파는 곳"));
        providers.add(new ListProvider().setName("짬스뽕스").setAddress("제대 후문 짬뽕 파는 곳"));
        providers.add(new ListProvider().setName("배꼽시계").setAddress("제대 후문 한식 파는 곳"));
        providers.add(new ListProvider().setName("월궁").setAddress("제대 후문 중식 파는 곳"));
        providers.add(new ListProvider().setName("꽁냥꽁냥").setAddress("제대 후문 일식 파는 곳"));
        providers.add(new ListProvider().setName("밥먹젠").setAddress("제대 정문 한식 파는 곳"));
        providers.add(new ListProvider().setName("하오돈부리").setAddress("제대 정문 일식 파는 곳"));
        providers.add(new ListProvider().setName("다래향").setAddress("제대 정문 중식 파는 곳"));
        providers.add(new ListProvider().setName("지혁이네").setAddress("제대 정문 부대찌개 파는 곳"));
        providers.add(new ListProvider().setName("김가네").setAddress("제대 정문 사라진 곳"));
        providers.add(new ListProvider().setName("봄봄").setAddress("제대 정문 커피 파는 곳"));
    }

    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), IntroduceActivity.class);
            intent.putExtra("data", testVO() );
            //나중에 데이터 얹어서 추가로 보낼것.

            startActivity(intent);
        }
    };

    private RestaurantVO testVO(){
        RestaurantVO vo = new RestaurantVO();
        vo.setName("우리집");
        vo.setAddress("우리집");
        vo.setTelNum("010-1010-1010");

        return vo;
    }

}
