package com.work.nalandya.pawoon_test.presenter.impl;

import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;
import com.work.nalandya.pawoon_test.presenter.localdb.DataBaseHelper;
import com.work.nalandya.pawoon_test.presenter.pres.MainActivityPres;
import com.work.nalandya.pawoon_test.presenter.view.MainActivityView;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

//this class is the logical class, so all method on the view class will be declare here
@EBean
public class MainActivityImpl extends BaseImpl<MainActivityView> implements MainActivityPres {

    private DataBaseHelper dataBaseHelper;

    //this method call the get data proses from the url
    @Override
    public void getData() {
        dataBaseHelper = new DataBaseHelper(activity);
        if (dataBaseHelper.getDataCount() <= 0) {
            connectionGet.getData(progress(), this);
        }
    }

    //method for the button click
    @Override
    public void goToToDoList() {
        intentManager.goToTodoList();
    }


    //doing method when success get data from server
    @Override
    public void onSuccess(Object o) {
        ArrayList<ResponseTodoList> data = (ArrayList<ResponseTodoList>) o;
        saveDataToLocal(data);
    }

    //insert data from server to db local
    private void saveDataToLocal(ArrayList<ResponseTodoList> data) {
        dataBaseHelper.insertAllData(data);
    }
}
