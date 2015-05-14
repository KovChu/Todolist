package com.demo.kuanyi.todolist.model;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kuanyi on 15/5/14.
 */
public class DataHelper {
    private DBHelper mDBHelper;

    public DataHelper(Context context){
        mDBHelper = OpenHelperManager.getHelper(context, DBHelper.class);
    }


    public boolean createOrUpdateListItem(ListItemTable listItemTable) {
        try {
            mDBHelper.getListItemTable().createOrUpdate(listItemTable);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeListItem(int id) {
        try {
            mDBHelper.getListItemTable().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<ListItemTable> queryForAllListItems() {
        try {
            return mDBHelper.getListItemTable().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void close() {
        mDBHelper.close();
    }
}
