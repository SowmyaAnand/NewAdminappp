package com.dailyestoreapp.adminapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemActivateResponseData {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private Integer data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
