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
 * The adapter also holds the data, and does the modifications on the data
 * Created by kuanyi on 15/5/14.
 */
public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private ArrayList<ListItemTable> mAllListItemTableList = null;

    //the list of the current display list, this will be the same with
    //mAllListItemTableList when the filter is not applied
    private ArrayList<ListItemTable> mDisplayingItemTableList = null;

    //the callback for communicating with fragment
    private AdapterCallback mAdapterCallback;

    //whether the list is filtered or not
    private boolean isFiltered = false;


    public ListAdapter(Fragment fragment, ArrayList<ListItemTable> listItemTables) {
        try {
            this.mAdapterCallback = ((AdapterCallback) fragment);
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement AdapterCallback.");
        }
        mAllListItemTableList = listItemTables;
        mDisplayingItemTableList = new ArrayList<>();
        mDisplayingItemTableList.addAll(listItemTables);
    }

    /**
     * adding a new list item to the list
     * @param listItemTable the item to be added
     */
    public void addNewListItem(ListItemTable listItemTable) {
        int position = mAllListItemTableList.size();
        mAllListItemTableList.add(listItemTable);
        mDisplayingItemTableList.add(listItemTable);
        //notify the fragment that the size has been changed
        notifyAdapterSizeChange();
        notifyItemInserted(position);
    }

    //notify the fragment that the size of the adapter has changed
    private void notifyAdapterSizeChange() {
        mAdapterCallback.onAdapterItemSizeChange(mAllListItemTableList.size());
    }

    // remove an item from the list
    private void removeItem(ListItemTable itemTable) {
        int position = mDisplayingItemTableList.indexOf(itemTable);
        //notify need to go first before the actual removing from list so the animation
        //can run correctly
        notifyItemRemoved(position);
        mAllListItemTableList.remove(itemTable);
        mDisplayingItemTableList.remove(position);
        //notify the fragment that the size has been changed
        notifyAdapterSizeChange();
    }

    /**
     * remove all items from the list
     */
    public void removeAllItems() {
        mAllListItemTableList.clear();
        mDisplayingItemTableList.clear();
        notifyDataSetChanged();
    }

    // notify the item has been changed
    private void changeItem(ListItemTable itemTable){
        int position = mDisplayingItemTableList.indexOf(itemTable);
        notifyItemChanged(position);
    }

    /**
     * mark every item as complete or incomplete
     * @param complete flags for marking the data
     */
    public void markAllAsComplete(boolean complete) {
        DataHelper helper = Utils.getDataHelper();
        for(ListItemTable itemTable : mAllListItemTableList) {
            itemTable.setIsComplete(complete);
            //update the data in the database
            helper.createOrUpdateListItem(itemTable);
        }
        filterData(false);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, final int position) {
        final ListItemTable itemTable = mDisplayingItemTableList.get(position);
        listViewHolder.textView.setText(itemTable.getTitle());
        listViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when the item is clicked, revert its complete state.
                itemTable.setIsComplete(!itemTable.isComplete());
                Utils.getDataHelper().createOrUpdateListItem(itemTable);
                changeItem(itemTable);
            }
        });
        listViewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //when the item is long-clicked, remove the item
                removeItem(itemTable);
                Utils.getDataHelper().removeListItem(itemTable.getId());
                return true;
            }
        });

        if(itemTable.isComplete()) {
            //when the item is complete, display the check and change the background color
            listViewHolder.textView.setBackgroundResource(R.color.complete);
            listViewHolder.checkImageView.setVisibility(View.VISIBLE);
        }else {
            //when the item is not complete, hide the check and change back the background color
            listViewHolder.textView.setBackgroundResource(R.color.transparent);
            listViewHolder.checkImageView.setVisibility(View.GONE);
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
        return mDisplayingItemTableList.size();
    }

    public void filterData(boolean change) {
        mDisplayingItemTableList = new ArrayList<>();
        if(change) {
            //revert the isFilter flag
            isFiltered = !isFiltered;
        }
        if(isFiltered) {
            for (ListItemTable itemTable : mAllListItemTableList) {
                if(!itemTable.isComplete()) {
                    //only add the incomplete item to the list
                    mDisplayingItemTableList.add(itemTable);
                }
            }
        }else {
            //add all items to the list
            mDisplayingItemTableList.addAll(mAllListItemTableList);
        }
        notifyDataSetChanged();
    }
}
