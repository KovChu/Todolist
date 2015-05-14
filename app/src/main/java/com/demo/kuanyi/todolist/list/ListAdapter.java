package com.demo.kuanyi.todolist.list;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.kuanyi.todolist.R;
import com.demo.kuanyi.todolist.Utils;
import com.demo.kuanyi.todolist.model.DataHelper;
import com.demo.kuanyi.todolist.model.ListItemTable;
import com.twotoasters.android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A list adapter that takes in a list of ListItemTable and display it.
 * Created by kuanyi on 15/5/14.
 */
public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private ArrayList<ListItemTable> mListItemTableList = null;

    private AdapterCallback mAdapterCallback;


    public ListAdapter(Fragment fragment, ArrayList<ListItemTable> listItemTables) {
        try {
            this.mAdapterCallback = ((AdapterCallback) fragment);
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement AdapterCallback.");
        }
        mListItemTableList = listItemTables;
    }

    public void addNewListItem(ListItemTable listItemTable) {
        int position = mListItemTableList.size();
        mListItemTableList.add(listItemTable);
        //notify the fragment that the size has been changed
        notifyAdapterSizeChange();
        notifyItemInserted(position);
    }

    private void notifyAdapterSizeChange() {
        mAdapterCallback.onAdapterItemSizeChange(mListItemTableList.size());
    }

    public void removeItem(ListItemTable itemTable) {
        int position = mListItemTableList.indexOf(itemTable);
        notifyItemRemoved(position);
        mListItemTableList.remove(position);
        //notify the fragment that the size has been changed
        notifyAdapterSizeChange();
    }

    public void removeAllItems() {
        mListItemTableList.clear();
        notifyDataSetChanged();
    }

    public void changeItem(ListItemTable itemTable){
        int position = mListItemTableList.indexOf(itemTable);
        notifyItemChanged(position);
    }

    public void markAllAsRead() {
        DataHelper helper = Utils.getDataHelper();
        for(ListItemTable itemTable : mListItemTableList) {
            itemTable.setIsComplete(true);
            helper.createOrUpdateListItem(itemTable);
        }
        notifyDataSetChanged();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, final int position) {
        final ListItemTable itemTable = mListItemTableList.get(position);
        listViewHolder.textView.setText(itemTable.getTitle());
        listViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemTable.setIsComplete(!itemTable.isComplete());
                Utils.getDataHelper().createOrUpdateListItem(itemTable);
                changeItem(itemTable);
            }
        });
        listViewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeItem(itemTable);
                Utils.getDataHelper().removeListItem(itemTable.getId());
                return true;
            }
        });
        if(itemTable.isComplete()) {
            listViewHolder.textView.setBackgroundResource(R.color.complete);
        }else {
            listViewHolder.textView.setBackgroundResource(R.color.transparent);
        }
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mListItemTableList.size();
    }

}
