package com.demo.kuanyi.todolist.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by kuanyi on 15/5/14.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "todolist.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<ListItemTable, Integer> listItemTable = null;
    private Dao<DetailItemTable, Integer> detailItemTable = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * What to do when your database needs to be created. Usually this entails creating the tables and loading any
     * initial data.
     * <p/>
     * <p>
     * <b>NOTE:</b> You should use the connectionSource argument that is passed into this method call or the one
     * returned by getConnectionSource(). If you use your own, a recursive call or other unexpected results may result.
     * </p>
     *
     * @param database         Database being created.
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ListItemTable.class);
            TableUtils.createTable(connectionSource, DetailItemTable.class);
        } catch (SQLException e) {
            Log.e(DBHelper.class.getName(), "Create Fail", e);
            e.printStackTrace();
        }
    }

    /**
     * What to do when your database needs to be updated. This could mean careful migration of old data to new data.
     * Maybe adding or deleting database columns, etc..
     * <p/>
     * <p>
     * <b>NOTE:</b> You should use the connectionSource argument that is passed into this method call or the one
     * returned by getConnectionSource(). If you use your own, a recursive call or other unexpected results may result.
     * </p>
     *
     * @param database         Database being upgraded.
     * @param connectionSource To use get connections to the database to be updated.
     * @param oldVersion       The version of the current database so we can know what to do to the database.
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }


    @Override
    public void close() {
        super.close();
        listItemTable = null;
        detailItemTable = null;
    }

    public Dao<ListItemTable, Integer> getListItemTable() throws SQLException {
        if (listItemTable == null) {
            listItemTable = getDao(ListItemTable.class);
        }
        return listItemTable;
    }

    public Dao<DetailItemTable, Integer> getDetailItemTable() throws SQLException {
        if (detailItemTable == null) {
            detailItemTable = getDao(DetailItemTable.class);
        }
        return detailItemTable;
    }

}
