package com.work.nalandya.pawoon_test.model.response;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import lombok.Data;

@Data
public class ResponseTodoList {

    @SerializedName("userId")
    private int userId;
    private int id;
    private String title;
    private boolean completed;

}
