package com.work.nalandya.pawoon_test.presenter.connection.service;

import android.app.Activity;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.work.nalandya.pawoon_test.BuildConfig;
import com.work.nalandya.pawoon_test.presenter.base.session.SessionToken;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient extends Activity {
    public static final String BASE_URL = BuildConfig.URL_BASE;
    private static Retrofit retrofit = null;


    public static Retrofit getClient(Activity activity) {
        final SessionToken token = new SessionToken(activity);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request().newBuilder()
                        .build();
                return chain.proceed(request);
            }
        };
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(5, TimeUnit.MINUTES);
        httpClient.readTimeout(5, TimeUnit.MINUTES);
        OkHttpClient client = httpClient.build();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(rxAdapter)
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
