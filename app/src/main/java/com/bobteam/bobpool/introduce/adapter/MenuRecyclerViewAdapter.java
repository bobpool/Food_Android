package com.bobteam.bobpool.introduce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.vo.MenuVO;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-29.
 */

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder> {
    private ArrayList<MenuVO> provider;
    private View.OnClickListener itemClickListener;

    public MenuRecyclerViewAdapter(ArrayList<MenuVO> provider, View.OnClickListener itemClickListener){
        this.provider = provider;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate( R.layout.recycler_item_menu, parent, false);

        view.setOnClickListener(itemClickListener);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.view.setTag(position);
        holder.nameView.setText(provider.get(position).getName());
        holder.priceView.setText(provider.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return provider.size();
    }

    public void addData(ArrayList<MenuVO> vos){
        provider.addAll(vos);

        notifyDataSetChanged();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView nameView;
        TextView priceView;

        public MenuViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            nameView = itemView.findViewById(R.id.menu_name);
            priceView = itemView.findViewById(R.id.menu_price);
        }
    }
}
