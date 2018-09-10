package com.example.rifat.readfromapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataModelList

{

    @SerializedName("data")
    private List<dataModel> dataList;
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    public dataModelList(List<dataModel> dataList) {
        this.dataList = dataList;

    }
    public dataModelList(List<dataModel> dataList, int status, String message) {
        this.dataList = dataList;
        this.status = status;
        this.message = message;
    }

    public List<dataModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<dataModel> dataList) {
        this.dataList = dataList;
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
