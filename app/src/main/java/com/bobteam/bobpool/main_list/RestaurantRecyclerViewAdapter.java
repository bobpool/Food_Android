package com.bobteam.bobpool.main_list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.vo.RestaurantVO;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-14.
 */

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<RestaurantVO> providers;
    private View.OnClickListener itemClickListener;

    public RestaurantRecyclerViewAdapter(ArrayList<RestaurantVO> provider, View.OnClickListener itemClickListener){
        this.providers = provider;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate(  R.layout.recycler_item_restaurant, parent, false);
        view.setOnClickListener(itemClickListener);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(providers.get(position).getName());
        holder.address.setText(providers.get(position).getAddress());

        holder.view.setTag(providers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        Log.e(this.toString(), "getItemCount: " + providers.size() );
        return providers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView name;
        private TextView address;

        private MyViewHolder(View view) {
            super(view);

            this.view = view;
            name = view.findViewById(R.id.name);
            address = view.findViewById(R.id.address_search);
        }
    }

}
