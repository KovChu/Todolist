package com.demo.kuanyi.todolist.list;

/**
 * This interface is used for communication between the fragment and the adapter
 * Created by kuanyi on 15/5/14.
 */
public interface AdapterCallback {

    /**
     * receive notification when the size of the adapter has change
     * @param size the size of the adapter after the change
     */
    void onAdapterItemSizeChange(int size);
}
