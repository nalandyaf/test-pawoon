package com.work.nalandya.pawoon_test.presenter.view;


import com.work.nalandya.pawoon_test.presenter.localdb.table.TodoList;

import java.util.ArrayList;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

public interface TodoListView {
    void setList(ArrayList<TodoList> allTodoList);
}
