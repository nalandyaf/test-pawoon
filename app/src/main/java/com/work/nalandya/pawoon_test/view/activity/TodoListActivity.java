package com.work.nalandya.pawoon_test.view.activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;
import com.work.nalandya.pawoon_test.presenter.base.activity.BaseActivity;
import com.work.nalandya.pawoon_test.presenter.callback.RecyclerListener;
import com.work.nalandya.pawoon_test.presenter.impl.TodoListImpl;
import com.work.nalandya.pawoon_test.presenter.localdb.table.TodoList;
import com.work.nalandya.pawoon_test.presenter.view.TodoListView;
import com.work.nalandya.pawoon_test.view.adapter.AdapterTodoList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

@EActivity(R.layout.activity_todo_list)
public class TodoListActivity extends BaseActivity implements TodoListView,RecyclerListener {


    //Set Presenter Class For Logical Method for this activity
    @Bean
    protected TodoListImpl todoList;
    @ViewById(R.id.list_item)
    protected RecyclerView list;


    @AfterViews
    protected void init(){
        todoList.setViewAct(this);
        todoList.getData();
    }

    //set List When Retrieve Data
    @Override
    public void setList(ArrayList<TodoList> allTodoList) {
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new AdapterTodoList(allTodoList,this));
    }

    @Override
    public void onItemClick(Object o) {

    }
}
