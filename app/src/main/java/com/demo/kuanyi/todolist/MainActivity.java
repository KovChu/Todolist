package com.demo.kuanyi.todolist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.demo.kuanyi.todolist.list.ToDoListFragment;
import com.demo.kuanyi.todolist.model.DataHelper;


public class MainActivity extends ActionBarActivity {

    //we need DataHelper to be static to avoid multiple instances of the DB,
    //which will cause the data to be corrupted.
    private static DataHelper mDataHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().
                beginTransaction().
                add(R.id.container,
                        ToDoListFragment.newInstance(),
                        "FRAGMENT_LIST").
                commit();
        mDataHelper = new DataHelper(this);
    }

    public DataHelper getDataHelper() {
        return mDataHelper;
    }

    public void onDestroy() {
        super.onDestroy();
        mDataHelper.close();
    }
}
