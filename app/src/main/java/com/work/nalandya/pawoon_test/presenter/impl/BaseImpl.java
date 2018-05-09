package com.work.nalandya.pawoon_test.presenter.impl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;


import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.presenter.callback.CallbackConnection;
import com.work.nalandya.pawoon_test.presenter.connection.get.ConnectionGet;
import com.work.nalandya.pawoon_test.presenter.manager.DialogManager;
import com.work.nalandya.pawoon_test.presenter.manager.IntentManager;
import com.work.nalandya.pawoon_test.presenter.utils.FragmentManagerUtils;
import com.work.nalandya.pawoon_test.presenter.utils.PermissionMarshmellow;
import com.work.nalandya.pawoon_test.presenter.utils.UtilsLayout;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import lombok.Getter;
import lombok.Setter;


@EBean
public class BaseImpl<T> implements CallbackConnection {
    @RootContext
    protected Activity activity;

    @Bean
    protected IntentManager intentManager;

    @Setter
    protected T viewAct;
    @Bean
    protected UtilsLayout utilsLayout;
    @Getter
    @Bean
    protected FragmentManagerUtils fragmentManagerUtils;
    @Bean
    protected DialogManager dialogManager;
    @Bean
    protected PermissionMarshmellow permissionMarshmellow;
    @Bean
    protected ConnectionGet connectionGet;
    private ProgressDialog progressDialog;


    @AfterInject
    protected void inject() {

    }

    public void setFragmentManager(FragmentManager fragmentManager, String className) {
        fragmentManagerUtils.setFragmentManager(fragmentManager);
        fragmentManagerUtils.setCallback(this);
        fragmentManagerUtils.setClassName(className);
        dialogManager.setFragmentManager(fragmentManager);
    }

    public ProgressDialog progress(){
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setMax(4);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage(activity.getResources().getString(R.string.content_progress));
        return progressDialog;
    }

    public void setLayoutFragment(int layoutFragment) {
        fragmentManagerUtils.setLayoutFragment(layoutFragment);
    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onSuccessNull() {

    }
}
