package com.demo.kuanyi.todolist.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The detail item table that holds the data.
 * Created by kuanyi on 15/5/14.
 */
@DatabaseTable(tableName = "detailItemTable")
public class DetailItemTable {

    //the ID of the list item, it is auto-generated, so it cannot be set
    @DatabaseField(generatedId = true)
    private int id;

    //the title or description of the item
    @DatabaseField
    private String title;

    //the id of the list it belongs to.
    @DatabaseField
    private int listId;

    //whether the item is complete
    @DatabaseField
    private boolean complete;

    public DetailItemTable() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
