package com.demo.kuanyi.todolist.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The database table storing all the data for the TODOlist item
 * Created by kuanyi on 15/5/14.
 */

@DatabaseTable(tableName = "listItemTable")
public class ListItemTable {

    //the ID of the list item, it is auto-generated, so it cannot be set
    @DatabaseField(generatedId = true)
    private int id;

    //the title or description of the item
    @DatabaseField
    private String title;

    //whether the task is complete or not
    @DatabaseField
    private boolean isComplete;

    public ListItemTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}
