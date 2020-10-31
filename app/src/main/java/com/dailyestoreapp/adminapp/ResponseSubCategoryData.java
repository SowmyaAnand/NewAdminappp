//Data package for Sub category in main category
package com.dailyestoreapp.adminapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSubCategoryData {

    @SerializedName("subId")
    @Expose
    private String subId;





    @SerializedName("typeId")
    @Expose
    private String typeId;
    @SerializedName("subName")
    @Expose
    private String subName;
    @SerializedName("subItemImage")
    @Expose
    private String subItemImage;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubItemImage() {
        return subItemImage;
    }

    public void setSubItemImage(String subItemImage) {
        this.subItemImage = subItemImage;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}


