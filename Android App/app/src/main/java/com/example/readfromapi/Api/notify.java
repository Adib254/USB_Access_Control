package com.example.rifat.readfromapi.Api;

import com.example.rifat.readfromapi.Model.dataModelList;
import com.example.rifat.readfromapi.Model.notifications;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface notify {

    @Headers("Cache-Control: max-age=0")
    @FormUrlEncoded
    @POST
    Call<notifications> getNotify(@Url String url, @Field("P") String code);
}