package com.demo.kuanyi.todolist.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.kuanyi.todolist.R;
import com.demo.kuanyi.todolist.model.ListItemTable;

import java.util.ArrayList;

/**
 * A list adapter that takes in a list of ListItemTable and display it.
 * Created by kuanyi on 15/5/14.
 */
public class ListAdapter extends BaseAdapter {

    private ArrayList<ListItemTable> mListItemTableList = null;
    private LayoutInflater mLayoutInflater = null;

    public ListAdapter(Context context, ArrayList<ListItemTable> listItemTables) {
        mLayoutInflater = LayoutInflater.from(context);
        mListItemTableList = listItemTables;
    }

    public void addNewListItem(ListItemTable listItemTable) {
        mListItemTableList.add(listItemTable);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mListItemTableList.remove(position);
        notifyDataSetChanged();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mListItemTableList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public ListItemTable getItem(int position) {
        return mListItemTableList.get(position);
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

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemHolder holder;
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            holder = new ListItemHolder();
            holder.title = (TextView) convertView.findViewById(R.id.list_item_textview);
            convertView.setTag(holder);
        }else {
            holder = (ListItemHolder) convertView.getTag();
        }
        holder.title.setText(getItem(position).getTitle());
        return convertView;
    }


    private static class ListItemHolder {
        TextView title;
    }
}
