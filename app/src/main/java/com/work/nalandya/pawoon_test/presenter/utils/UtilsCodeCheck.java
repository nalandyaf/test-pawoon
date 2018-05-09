package com.work.nalandya.pawoon_test.presenter.utils;

import android.app.Activity;
import android.app.ProgressDialog;

import com.google.gson.Gson;
import com.work.nalandya.pawoon_test.presenter.callback.CallbackConnection;
import com.work.nalandya.pawoon_test.presenter.manager.IntentManager;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.IOException;

import retrofit2.Response;

@EBean
public class UtilsCodeCheck {

    @RootContext
    protected Activity activity;
    @Bean
    protected IntentManager intentManager;
    @Bean
    protected Util util;

    public <E> void checkCodeGet(CallbackConnection callbackConnection, Response<E> o, ProgressDialog progressDialog) {
        switch (o.code()) {
            case 200:callbackConnection.onSuccess(o.body());break;
            case 203:showNotMatch(o);break;
            case 204:callbackConnection.onSuccessNull();break;
            case 403:util.showExpired(activity);break;
            case 400:showErrorValidate(o);break;
            case 409:util.showUnauthorized(activity);break;
            default:showError(o);break;
        }
        progressDialog.dismiss();
    }

    public <E> void showNotMatch(Response<E> o) {
//        ModelError modelError = null;
//        modelError = new Gson().fromJson(String.valueOf(o.body()), ModelError.class);
        util.showDialogError(activity, "Terjadi kesalahan harap hubungi admin");
    }

    public <E> void showError(Response<E> o) {
//        ModelError modelError = null;
//        try {
//            modelError = new Gson().fromJson(o.errorBody().string(), ModelError.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        util.showDialogError(activity, modelError.getMessage());
    }

    private <E> void showErrorValidate(Response<E> o) {
//        ModelError modelError = null;
//        String error = "";
//
//        try {
//            modelError = new Gson().fromJson(o.errorBody().string(), ModelError.class);
//            if (modelError.getErrors() != null) {
//                for (int i = 0; i < modelError.getErrors().length; i++) {
//                    error = error + "- " + modelError.getErrors()[i] + "\n";
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        util.showDialogError(activity, modelError.getMessage() + ":\n\n" + error);
    }


}
