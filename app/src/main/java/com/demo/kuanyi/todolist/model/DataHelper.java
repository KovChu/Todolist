package com.demo.kuanyi.todolist.model;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

/**
 * The helper class that handles the actions on the database
 * Created by kuanyi on 15/5/14.
 */
public class DataHelper {
    private DBHelper mDBHelper;

    public DataHelper(Context context){
        mDBHelper = OpenHelperManager.getHelper(context, DBHelper.class);
    }


    /**
     * create or update a ListItemTable
     * @param listItemTable the item to be save
     */
    public void createOrUpdateListItem(ListItemTable listItemTable) {
        try {
            mDBHelper.getListItemTable().createOrUpdate(listItemTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * remove a list item from the database
     * @param id the id of the item to be removed
     */
    public void removeListItem(int id) {
        try {
            mDBHelper.getListItemTable().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * clear all the items from the ListItemTable
     */
    public void clearAllItem() {
        mDBHelper.clearListItemTable();
    }


    /**
     * query and return all the items that are stored in the database
     * @return the List containing all the items
     */
    public List<ListItemTable> queryForAllListItems() {
        try {
            return mDBHelper.getListItemTable().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
