package com.work.nalandya.pawoon_test.presenter.impl;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;
import com.work.nalandya.pawoon_test.presenter.callback.RecyclerListener;
import com.work.nalandya.pawoon_test.presenter.localdb.DataBaseHelper;
import com.work.nalandya.pawoon_test.presenter.pres.TodoListPres;
import com.work.nalandya.pawoon_test.presenter.view.TodoListView;
import com.work.nalandya.pawoon_test.view.adapter.AdapterTodoList;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

@EBean
public class TodoListImpl extends BaseImpl<TodoListView> implements TodoListPres, RecyclerListener {

   private DataBaseHelper dataBaseHelper;


    @Override
    public void getData() {
        dataBaseHelper = new DataBaseHelper(activity);
        if(dataBaseHelper.getDataCount() > 0){
            viewAct.setList(dataBaseHelper.getAllTodoList());
        }
    }

    @Override
    public void onItemClick(Object o) {

    }

}
