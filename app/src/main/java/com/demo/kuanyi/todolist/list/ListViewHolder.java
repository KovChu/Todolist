package com.demo.kuanyi.todolist.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.kuanyi.todolist.R;
import com.twotoasters.android.support.v7.widget.CardView;
import com.twotoasters.android.support.v7.widget.RecyclerView;

/**
 * The ViewHolder class holding the views for the list
 * Created by kuanyi on 15/5/14.
 */
public class ListViewHolder extends RecyclerView.ViewHolder{
    public final CardView cardView;
    public final TextView textView;
    public final ImageView checkImageView;

    public ListViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView;
        //set the shadow radius
        cardView.setRadius(itemView.getResources().getDimension(R.dimen.card_radius));

        textView = (TextView) cardView.findViewById(R.id.list_item_title_textview);
        checkImageView = (ImageView) cardView.findViewById(R.id.list_item_check_img);

    }
}
