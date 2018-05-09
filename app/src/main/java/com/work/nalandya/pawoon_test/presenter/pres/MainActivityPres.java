package com.work.nalandya.pawoon_test.presenter.pres;


import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

public interface MainActivityPres {
    void getData();
    void goToToDoList();

}
