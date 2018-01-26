package com.bobteam.bobpool.main_list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bobteam.bobpool.R;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-14.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<ListProvider> listProvider;
    private View.OnClickListener itemClickListener;

    public MyRecyclerViewAdapter(ArrayList<ListProvider> provider , View.OnClickListener itemClickListener){
        this.listProvider = provider;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        view.setOnClickListener(itemClickListener);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(listProvider.get(position).getName());
        holder.address.setText(listProvider.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        Log.e(this.toString(), "getItemCount: " + listProvider.size() );
        return listProvider.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView address;

        private MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            address = view.findViewById(R.id.address_search);
        }
    }

}
