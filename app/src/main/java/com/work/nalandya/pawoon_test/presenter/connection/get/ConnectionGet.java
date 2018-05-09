package com.work.nalandya.pawoon_test.presenter.connection.get;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;


import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;
import com.work.nalandya.pawoon_test.presenter.callback.CallbackConnection;
import com.work.nalandya.pawoon_test.presenter.connection.service.ApiClient;
import com.work.nalandya.pawoon_test.presenter.connection.service.ApiConfig;
import com.work.nalandya.pawoon_test.presenter.manager.IntentManager;
import com.work.nalandya.pawoon_test.presenter.utils.Util;
import com.work.nalandya.pawoon_test.presenter.utils.UtilsCodeCheck;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// this class represented all the get data proccess for the http request
@EBean
public class ConnectionGet {

    @RootContext
    protected Activity activity;

    @Bean
    protected UtilsCodeCheck utilsCodeCheck;
    @Bean
    protected IntentManager intentManager;
    @Bean
    protected Util util;

    private void failure(String message, AlertDialog alertDialog) {
        util.showDialogError(activity, message);
        alertDialog.dismiss();
    }

    public void getData(final ProgressDialog progressDialog, final CallbackConnection callbackConnection) {
        progressDialog.show();
        final ApiConfig apiConfig = ApiClient.getClient(activity).create(ApiConfig.class);
        Call<ArrayList<ResponseTodoList>> call = apiConfig.doGetData();
        call.enqueue(new Callback<ArrayList<ResponseTodoList>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseTodoList>> call, Response<ArrayList<ResponseTodoList>> response) {
                utilsCodeCheck.checkCodeGet(callbackConnection,response,progressDialog);
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseTodoList>> call, Throwable t) {
                failure(t.getMessage(),progressDialog);
            }
        });

    }


}
