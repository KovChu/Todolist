package com.demo.kuanyi.todolist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;

/**
 * The abstract class that contains the share methods of communications and actions
 * for the fragments that extends it
 * Created by kuanyi on 15/5/14.
 */
public abstract class AbstractToDoFragment extends Fragment implements Handler.Callback{

    protected Handler mHandler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHandler = new Handler(this);
    }

    public MainActivity checkActivity() {
        Activity activity = getActivity();
        if(activity != null && activity instanceof MainActivity) {
            return (MainActivity) activity;
        }
        return null;
    }
}
