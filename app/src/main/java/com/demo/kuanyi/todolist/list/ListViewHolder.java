package com.demo.kuanyi.todolist.list;

import android.view.View;
import android.widget.TextView;

import com.demo.kuanyi.todolist.R;
import com.twotoasters.android.support.v7.widget.CardView;
import com.twotoasters.android.support.v7.widget.RecyclerView;

/**
 * Created by kuanyi on 15/5/14.
 */
public class ListViewHolder extends RecyclerView.ViewHolder{
    public final CardView cardView;
    public final TextView textView;

    public ListViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView;
        cardView.setRadius(itemView.getResources().getDimension(R.dimen.card_radius));

        textView = (TextView) cardView.getChildAt(0);
    }
}
