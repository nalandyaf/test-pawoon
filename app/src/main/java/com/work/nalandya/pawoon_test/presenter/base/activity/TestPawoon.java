package com.work.nalandya.pawoon_test.presenter.base.activity;

import android.app.Activity;
import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.fabric.sdk.android.Fabric;


public class TestPawoon extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build()).build());
    }
}
