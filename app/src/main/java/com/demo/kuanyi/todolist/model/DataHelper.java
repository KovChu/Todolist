package com.demo.kuanyi.todolist.model;

import android.content.Context;
import android.os.Handler;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kuanyi on 15/5/14.
 */
public class DataHelper {
    private DBHelper mDBHelper;

    public DataHelper(Context context, Handler handler){
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
    public boolean createOrUpdateDetailItem(DetailItemTable listItemTable) {
        try {
            mDBHelper.getDetailItemTable().createOrUpdate(listItemTable);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<ListItemTable> queryForAllListItems() {
        try {
            return mDBHelper.getListItemTable().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<DetailItemTable> queryForAllDetailItems(String listId) {
        try {
            return mDBHelper.getDetailItemTable().queryForEq("listId", listId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        mDBHelper.close();
    }
}
