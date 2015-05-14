package com.demo.kuanyi.todolist.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The detail item table that holds the data.
 * Created by kuanyi on 15/5/14.
 */
@DatabaseTable(tableName = "DetailItemTable")
public class DetailItemTable {


    @DatabaseField(id = true, canBeNull = false, generatedId = true)
    private int id;




}
