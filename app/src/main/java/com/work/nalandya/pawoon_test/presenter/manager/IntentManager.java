package com.work.nalandya.pawoon_test.presenter.manager;

import android.app.Activity;


import com.work.nalandya.pawoon_test.view.activity.MainActivity_;
import com.work.nalandya.pawoon_test.view.activity.TodoListActivity_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;



@EBean
public class IntentManager {

    @RootContext
    protected Activity activity;


    public interface Code {
        int FORM = 6001;
        int REQUEST_CAMERA = 1;
        int REQUEST_CAMERA_FARMER = 2;
        int BACKGROUND = 1;
        int BITMAP = 2;

    }


    public void goToTodoList() {
        TodoListActivity_.intent(activity).start();
    }


}
