package com.bobteam.bobpool.order;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bobteam.bobpool.R;
import com.bobteam.bobpool.vo.PartyVO;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-30.
 */

public class PartyRecyclerViewAdapter extends RecyclerView.Adapter<PartyRecyclerViewAdapter.PartyViewHolder> {
    private ArrayList<PartyVO> provider;
    private View.OnClickListener itemClickListener;

    public PartyRecyclerViewAdapter(ArrayList<PartyVO> provider, View.OnClickListener itemClickListener){
        this.provider = provider;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public PartyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate( R.layout.recycler_item_party, parent, false);

        return new PartyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PartyViewHolder holder, int position) {
        
    }

    @Override
    public int getItemCount() {
        return provider.size();
    }

    class PartyViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView priceView;

        public PartyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(itemClickListener);

            nameView = itemView.findViewById(R.id.menu_name);
            priceView = itemView.findViewById(R.id.menu_price);
        }
    }
}
