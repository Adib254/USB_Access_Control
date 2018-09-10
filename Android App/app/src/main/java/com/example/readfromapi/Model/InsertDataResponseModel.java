package com.example.rifat.readfromapi.Model;

/**
 * Created by abdullah on 10/28/17.
 */
import com.google.gson.annotations.SerializedName;

public class InsertDataResponseModel {
    @SerializedName("success")
    private int status;
    @SerializedName("message")
    private String message;

    public InsertDataResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public InsertDataResponseModel() {
        this.status = 0;
        this.message ="null";
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


