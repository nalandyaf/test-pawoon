package com.work.nalandya.pawoon_test.presenter.base.session;

import android.content.Context;
import android.content.SharedPreferences;



public class PersonalData {
    private final String PREF_NAME = "personaldata";
    private final String NAME_USER = "username";
    private final String PROF_PIC = "prof_pic";
    private final String TEAM = "team";
    private final String ID = "idUser";
    private final String USER_ID = "userId";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public PersonalData(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void setNameUser(String nameUser) {
        if (getNameUser() != null) clear();
        this.editor.putString(NAME_USER, nameUser);
        this.editor.commit();
    }

    public String getNameUser() {
        return this.sharedPreferences.getString(NAME_USER, null);
    }

    public void setProfPic(String profPic) {
        this.editor.putString(PROF_PIC, profPic);
        this.editor.commit();
    }

    public String getProfPic() { return this.sharedPreferences.getString(PROF_PIC, null); }

    public void setUserId(long userId) {
        this.editor.putLong(USER_ID, userId);
        this.editor.commit();
    }

    public long getUserId() { return this.sharedPreferences.getLong(USER_ID, 0); }

    public void setTeam(String team) {
        this.editor.putString(TEAM, team);
        this.editor.commit();
    }

    public String getTeam() { return this.sharedPreferences.getString(TEAM, null); }

    public void setId(long id){
        this.editor.putLong(ID, id);
        this.editor.commit();
    }

    public long getId(){ return this.sharedPreferences.getLong(ID, 0); }

    public void clear() {
        this.editor.clear();
        this.editor.commit();
    }

}
