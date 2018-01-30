package com.bobteam.bobpool.order;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bobteam.bobpool.R;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-29.
 */

public class BasketRecyclerViewAdapter extends RecyclerView.Adapter<BasketRecyclerViewAdapter.MenuViewHolder> {
    private ArrayList<BasketProvider> provider;
    private View.OnClickListener itemClickListener;

    public BasketRecyclerViewAdapter(ArrayList<BasketProvider> provider, View.OnClickListener itemClickListener){
        this.provider = provider;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate( R.layout.recycler_item_basket, parent, false);

        view.setOnClickListener(itemClickListener);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.foodName.setText( provider.get(position).getFoodName() );
        holder.amountText.setText( "" + provider.get(position).getAmount() );
        holder.priceView.setText( provider.get(position).getTotalPrice() + "원" );
    }

    @Override
    public int getItemCount() {
        return provider.size();
    }

    public void addData(ArrayList<BasketProvider> vos){
        provider.addAll(vos);

        notifyDataSetChanged();
    }

    public ArrayList<BasketProvider> getProvider(){
        return provider;
    }

    public void addData(BasketProvider vo){
        provider.add(vo);

        notifyDataSetChanged();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        TextView amountText;
        TextView priceView;

        MenuViewHolder(View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.food_name);
            amountText = itemView.findViewById(R.id.amount);
            priceView = itemView.findViewById(R.id.total_price);
        }
    }
}
