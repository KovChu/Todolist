package com.demo.kuanyi.todolist;

import android.app.Activity;
import android.app.Fragment;

/**
 * The abstract class that contains the share methods of communications and actions
 * for the fragments that extends it
 * Created by kuanyi on 15/5/14.
 */
public abstract class AbstractToDoFragment extends Fragment {

    MainActivity checkActivity() {
        Activity activity = getActivity();
        if(activity != null && activity instanceof MainActivity) {
            return (MainActivity) activity;
        }
        return null;
    }

    void openList() {

    }

    void closeDetail() {

    }
}
