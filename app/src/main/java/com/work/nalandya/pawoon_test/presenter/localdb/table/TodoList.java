package com.work.nalandya.pawoon_test.presenter.localdb.table;

import lombok.Data;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

@Data
public class TodoList {

    public static final String TABLE_NAME = "table_todo_list";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COMPLETE = "complete";

    private int userId;
    private int id;
    private String title;
    private int completed;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_USER_ID + " INTEGER,"
                    + COLUMN_ID + " INTEGER,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_COMPLETE + " INTEGER"
                    + ")";


    public TodoList() {

    }


}
