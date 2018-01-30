package com.bobteam.bobpool.introduce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.vo.ReviewVO;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-30.
 */

public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewViewHolder>{
    private ArrayList<ReviewVO> provider;
    private View.OnClickListener itemClickListener;

    public ReviewRecyclerViewAdapter(ArrayList<ReviewVO> provider, View.OnClickListener itemClickListener){
        this.provider = provider;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ReviewRecyclerViewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate( R.layout.recycler_item_menu, parent, false);

        view.setOnClickListener(itemClickListener);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.authorView.setText( provider.get(position).getAuther() );
        holder.contentView.setText( provider.get(position).getText() );
    }

    @Override
    public int getItemCount() {
        return provider.size();
    }

    public void addData(ArrayList<ReviewVO> vos){
        provider.addAll(vos);

        notifyDataSetChanged();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView authorView;
        TextView contentView;

        public ReviewViewHolder(View itemView) {
            super(itemView);

            authorView = itemView.findViewById(R.id.menu_name);
            contentView = itemView.findViewById(R.id.menu_price);
        }
    }
}
