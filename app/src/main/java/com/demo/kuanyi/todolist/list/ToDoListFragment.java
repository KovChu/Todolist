package com.demo.kuanyi.todolist.list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.demo.kuanyi.todolist.AbstractToDoFragment;
import com.demo.kuanyi.todolist.R;
import com.demo.kuanyi.todolist.Utils;
import com.demo.kuanyi.todolist.model.ListItemTable;
import com.twotoasters.android.support.v7.widget.LinearLayoutManager;
import com.twotoasters.android.support.v7.widget.RecyclerView;
import com.twotoasters.anim.SlideItemAnimator;

import java.util.ArrayList;


/**
 *
 * A placeholder fragment containing a simple view.
 */
public class ToDoListFragment extends AbstractToDoFragment implements AdapterCallback{

    private static final int LOAD_LIST_DATA_COMPLETE = 0;

    private RecyclerView mRecyclerView = null;
    private ListAdapter mListAdapter = null;

    private View mHintTextView = null;

    public static ToDoListFragment newInstance() {
        return new ToDoListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerview);
        mHintTextView = parentView.findViewById(R.id.no_item_hint);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.getRecycledViewPool().clear();
        mRecyclerView.setItemAnimator(new SlideItemAnimator());
        return parentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ArrayList<ListItemTable> existingList = (ArrayList<ListItemTable>) Utils.getDataHelper().queryForAllListItems();
                if(existingList == null) {
                    existingList = new ArrayList<>();
                }
                Message message = new Message();
                message.what = LOAD_LIST_DATA_COMPLETE;
                message.obj = existingList;
                mHandler.dispatchMessage(message);
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle(getString(R.string.add_new_list_title));
            alertDialog.setMessage(getString(R.string.add_new_list_description));
            final EditText input = new EditText(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input); // uncomment this line
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //create a new list item and display
                    ListItemTable newListItemTable = new ListItemTable();
                    newListItemTable.setTitle(input.getText().toString());
                    Utils.getDataHelper().createOrUpdateListItem(newListItemTable);
                    mListAdapter.addNewListItem(newListItemTable);
                }
            });
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.what == LOAD_LIST_DATA_COMPLETE) {
            ArrayList<ListItemTable> listItemTables = (ArrayList<ListItemTable>) msg.obj;
            onAdapterItemSizeChange(listItemTables.size());
            mListAdapter = new ListAdapter(this, listItemTables);
            mRecyclerView.setAdapter(mListAdapter);
        }
        return false;
    }

    @Override
    public void onAdapterItemSizeChange(int size) {
        if(size == 0) {
            displayHint();
        }else {
            dismissHint();
        }
    }

    private void displayHint() {
        mHintTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void dismissHint() {
        mHintTextView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
