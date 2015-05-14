package com.demo.kuanyi.todolist;

import android.content.Context;

import com.demo.kuanyi.todolist.model.DataHelper;

/**
 * The Utils class that holds the reference to a singleton DataHelper class
 * for easy retrieve for classes across the application.
 * Created by kuanyi on 15/5/14.
 */
public class Utils {

    //we need DataHelper to be static to avoid multiple instances of the DB,
    //which will cause the data to be corrupted.
    private static DataHelper mDataHelper = null;

    public static void initDataHelper(Context context) {
        mDataHelper = new DataHelper(context);
    }

    public static DataHelper getDataHelper() {
        return mDataHelper;
    }

}
