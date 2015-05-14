package com.demo.kuanyi.todolist.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
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

}
