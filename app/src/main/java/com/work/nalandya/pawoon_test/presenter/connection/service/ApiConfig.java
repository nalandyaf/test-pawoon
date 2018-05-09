package com.work.nalandya.pawoon_test.presenter.connection.service;


import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiConfig {



    @GET("todos")
    Call<ArrayList<ResponseTodoList>> doGetData();

//    @POST("login")
//    Call<ResponseLogin> doLogin(@Body RequestLogin requestLogin);

//    @GET("cities/{id}/districts")
//    Call<ResponseListDistrict> getListDistrict(@Path("id") int id);

//    @Multipart
//    @POST("images/upload")
//    Call<ResponseUploadPicture> doUploadImageUmkm(@Part MultipartBody.Part part, @Query("path") String path);
}
