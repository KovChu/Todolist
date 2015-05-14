package com.demo.kuanyi.todolist.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by kuanyi on 15/5/14.
 */

@DatabaseTable(tableName = "ListItemTable")
public class ListItemTable {

    @DatabaseField(id = true, canBeNull = false, generatedId = true)
    private int id;


}
