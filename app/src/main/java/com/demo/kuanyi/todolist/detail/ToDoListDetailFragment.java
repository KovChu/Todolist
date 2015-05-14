package com.demo.kuanyi.todolist.detail;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.kuanyi.todolist.AbstractToDoFragment;
import com.demo.kuanyi.todolist.R;

/**
 *
 * Created by kuanyi on 15/5/14.
 */
public class ToDoListDetailFragment extends AbstractToDoFragment{

    private static final String KEY_ID = "KEY_ID";

    private int mListId;


    public static ToDoListDetailFragment newInstance(int id) {
        ToDoListDetailFragment f = new ToDoListDetailFragment();
        Bundle b = new Bundle();
        b.putInt(KEY_ID, id);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // onCreate it's a good point to read the arguments
        Bundle b = getArguments();
        mListId = b.getInt(KEY_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
    }


    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
