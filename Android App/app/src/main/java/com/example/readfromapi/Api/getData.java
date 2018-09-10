package com.example.rifat.readfromapi.Api;


import com.example.rifat.readfromapi.Model.dataModel;
import com.example.rifat.readfromapi.Model.dataModelList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface getData
{

    @Headers("Cache-Control: max-age=0")
    @GET
    Call<dataModelList> getAlldata(@Url String url);
}