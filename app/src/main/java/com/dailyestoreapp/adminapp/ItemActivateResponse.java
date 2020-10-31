package com.dailyestoreapp.adminapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemActivateResponse {
    @SerializedName("responsedata")
    @Expose
    private ItemActivateResponseData responsedata;

    public ItemActivateResponseData getResponsedata() {
        return responsedata;
    }

    public void setResponsedata(ItemActivateResponseData responsedata) {
        this.responsedata = responsedata;

    }
}