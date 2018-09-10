package com.example.rifat.readfromapi.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.Date;

public class dataModel
{

    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("status")
    @Expose
    private String status;

    public dataModel( String timestamp, String status) {
        this.timestamp = timestamp;
        this.status = status;
    }



    public String getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
