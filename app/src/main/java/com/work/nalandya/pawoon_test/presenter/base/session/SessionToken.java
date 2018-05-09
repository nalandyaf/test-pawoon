package com.work.nalandya.pawoon_test.presenter.base.session;

import android.content.Context;
import android.content.SharedPreferences;


public class SessionToken {
    private final String PREF_NAME = "token";
    private final String KEY_NAME = "authToken";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public SessionToken(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void setToken(String token) {
        if (getToken() != null) clear();
        this.editor.putString(KEY_NAME, token);
        this.editor.commit();
    }

    public String getToken() {
        return this.sharedPreferences.getString(KEY_NAME, "kosong");
    }

    public void clear() {
        this.editor.clear();
        this.editor.commit();
    }
}
