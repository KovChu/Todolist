package com.demo.kuanyi.todolist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.demo.kuanyi.todolist.list.ToDoListFragment;


public class MainActivity extends ActionBarActivity {


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
        Utils.initDataHelper(this);
    }

}
