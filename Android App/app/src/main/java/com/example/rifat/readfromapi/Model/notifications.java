package com.example.rifat.readfromapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class notifications
{
    @SerializedName("message")
    @Expose
    private String message;

    public notifications(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
