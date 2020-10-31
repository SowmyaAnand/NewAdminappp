//Package for retrieving all types of get data and get success
package com.dailyestoreapp.adminapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCategoryResponseResponseData {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private List<ListCategoryResponseData> data = null;
    private List<ResponseSubCategoryData> sub_data = null;
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }



        public List<ListCategoryResponseData> getData() {
        return data;
    }
        public void setData(List<ListCategoryResponseData> data) {
        this.data = data;
    }

}
