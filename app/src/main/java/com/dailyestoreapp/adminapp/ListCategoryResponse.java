package com.dailyestoreapp.adminapp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCategoryResponse {

    @SerializedName("responsedata")
    @Expose
    private ListCategoryResponseResponseData responsedata;

    public ListCategoryResponseResponseData getResponsedata() {
        return responsedata;
    }

    public void setResponsedata(ListCategoryResponseResponseData responsedata) {
        this.responsedata = responsedata;
    }
}
