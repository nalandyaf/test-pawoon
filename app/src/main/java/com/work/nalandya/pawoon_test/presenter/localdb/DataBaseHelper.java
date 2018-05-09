package com.work.nalandya.pawoon_test.presenter.localdb;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;
import com.work.nalandya.pawoon_test.presenter.localdb.table.TodoList;

import java.util.ArrayList;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    // DB Version
    private static final int DATABASE_VERSION = 1;

    // DB Name
    private static final String DATABASE_NAME = "todo_list_sql_db";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoList.CREATE_TABLE);
    }

    //Upgrade DB if Data Existed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TodoList.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public boolean insertAllData(ArrayList<ResponseTodoList> data) {
        //open db transcation
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.beginTransaction();

        // for each data per row
        for (ResponseTodoList item : data) {
            ContentValues values = new ContentValues();
            values.put(TodoList.COLUMN_USER_ID, item.getUserId());
            values.put(TodoList.COLUMN_ID, item.getId());
            values.put(TodoList.COLUMN_TITLE, item.getTitle());
            values.put(TodoList.COLUMN_COMPLETE, (item.isCompleted()) ? 1 : 0);

            //insert data per row
            sqLiteDatabase.insert(TodoList.TABLE_NAME, null, values);
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        sqLiteDatabase.close();

        return true;
    }

    public ArrayList<TodoList> getAllTodoList() {
        ArrayList<TodoList> arrays = new ArrayList<>();
        // Query for Select All Data From Table
        String selectQuery = "SELECT  * FROM " + TodoList.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // loop all data from row and add to arraylist
        if (cursor.moveToFirst()) {
            do {
                TodoList item = new TodoList();
                item.setUserId(cursor.getInt(cursor.getColumnIndex(TodoList.COLUMN_USER_ID)));
                item.setId(cursor.getInt(cursor.getColumnIndex(TodoList.COLUMN_ID)));
                item.setTitle(cursor.getString(cursor.getColumnIndex(TodoList.COLUMN_TITLE)));
                item.setCompleted(cursor.getInt(cursor.getColumnIndex(TodoList.COLUMN_COMPLETE)));

                arrays.add(item);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return arrays;
    }

    //check data count
    public int getDataCount() {
        String countQuery = "SELECT  * FROM " + TodoList.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

}
