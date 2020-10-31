package com.dailyestoreapp.adminapp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("responsedata")
    @Expose
    private LoginResponseData responsedata;

    public LoginResponseData getResponsedata() {
        return responsedata;
    }

    public void setResponsedata(LoginResponseData responsedata) {
        this.responsedata = responsedata;
    }
}
