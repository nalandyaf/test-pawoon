package com.work.nalandya.pawoon_test.view.activity;


import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;
import com.work.nalandya.pawoon_test.presenter.base.activity.BaseActivity;

import com.work.nalandya.pawoon_test.presenter.impl.MainActivityImpl;
import com.work.nalandya.pawoon_test.presenter.view.MainActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainActivityView {


    //declare MainActivity Implement, all method on this layout are stored on MainActivityImpl class
    @Bean
    protected MainActivityImpl mainActivity;


    //run all method when all compontent are builded
    @AfterViews
    protected void init() {
        //setInterfaceView
        mainActivity.setViewAct(this);
        //run method getData
        mainActivity.getData();

    }


    //click on button
    @Click(R.id.to_do_list)
    protected void goTodoList() {
        mainActivity.goToToDoList();
    }


}
