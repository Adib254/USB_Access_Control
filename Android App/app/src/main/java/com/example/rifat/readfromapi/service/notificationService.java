package com.example.rifat.readfromapi.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.rifat.readfromapi.Api.ApiClient;
import com.example.rifat.readfromapi.Api.notify;
import com.example.rifat.readfromapi.Model.notifications;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class notificationService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public notificationService(String name) {
        super(name);
    }
    public notificationService() {
        super("Notifications");


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        getNotifications();
    }
    private void getNotifications()
    {

        notify apiService = ApiClient.getClient().create(notify.class);
        Call<notifications> call = apiService.getNotify("remoteunlock.php?P=12345","12345");
        call.enqueue(new Callback<notifications>() {
            @Override
            public void onResponse(Call<notifications> call, Response<notifications> response) {
                notifications listFoodModel = response.body();

            }

            @Override
            public void onFailure(Call<notifications> call, Throwable t) {


            }
        });// visible the progress bar

    }
}
