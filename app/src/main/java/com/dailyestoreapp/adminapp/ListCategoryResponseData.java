//Data package for  main category
package com.dailyestoreapp.adminapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCategoryResponseData {
    @SerializedName("bookingType")
    @Expose
    private String bookingType;

    @SerializedName("preBookingDate")
    @Expose
    private String preBookingDate;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("TotalOrdersPrice")
    @Expose
    private String TotalOrdersPrice;
    @SerializedName("AmountReducedPrice")
    @Expose
    private String AmountReducedPrice;
    @SerializedName("CouponApplied")
    @Expose
    private String CouponApplied;
    @SerializedName("count")
    @Expose
    private String count;

    @SerializedName("typeId")
    @Expose
    private String typeId;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemImage")
    @Expose
    private String itemImage;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("subId")
    @Expose
    private String subId;

    @SerializedName("subName")
    @Expose
    private String subName;
    @SerializedName("subItemImage")
    @Expose
    private String subItemImage;
    @SerializedName("itemId")
    @Expose
    private String itemId;

    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("cId")
    @Expose
    private String cId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("cartId")
    @Expose
    private String cartId;




    @SerializedName("type")
    @Expose
    private String type;


    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("phone")
    @Expose
    private String phone;


    @SerializedName("pinCode")
    @Expose
    private String pinCode;
    @SerializedName("postCode")
    @Expose
    private String postCode;
    @SerializedName("dob")
    @Expose
    private String dob;



    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("categoryImage")
    @Expose
    private String categoryImage;

    @SerializedName("flyId")
    @Expose
    private String flyId;

    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;
    @SerializedName("dealId")
    @Expose
    private String dealId;

    @SerializedName("couponId")
    @Expose
    private String couponId;
    @SerializedName("offer")
    @Expose
    private String offer;
    @SerializedName("couponName")
    @Expose
    private String couponName;
    @SerializedName("ofdescription")
    @Expose
    private String ofdescription;
    @SerializedName("percent")
    @Expose
    private String percent;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
    public String getoffer() {
        return offer;
    }

    public void setoffer(String offer) {
        this.offer = offer;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }



    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }



    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }


    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }



    public String getFlyId() {
        return flyId;
    }

    public void setFlyId(String flyId) {
        this.flyId = flyId;
    }


    public String getpaymentType() {
        return paymentType;
    }

    public void setpaymentType(String paymentType) {
        this.paymentType = paymentType;
    }



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getofdescription() {
        return ofdescription;
    }

    public void setofdescription(String ofdescription) {
        this.ofdescription = ofdescription;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }







    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getTotalOrdersPrice() {
        return TotalOrdersPrice;
    }

    public void setTotalOrdersPrice(String TotalOrdersPrice) {
        this.TotalOrdersPrice = TotalOrdersPrice;
    }
    public String getAmountReducedPrice() {
        return AmountReducedPrice;
    }

    public void setAmountReducedPrice(String AmountReducedPrice) {
        this.AmountReducedPrice = AmountReducedPrice;
    }
    public String getCouponApplied() {
        return CouponApplied;
    }

    public void setCouponApplied(String CouponApplied) {
        this.CouponApplied = CouponApplied;
    }





    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }




    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedBy() {
        return createdBy;
    }



    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
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
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }



    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getbookingType() {
        return bookingType;
    }

    public void setbookingType(String bookingType) {
        this.bookingType = bookingType;
    }


    public String getpreBookingDate() {
        return preBookingDate;
    }

    public void setpreBookingDate(String preBookingDate) {
        this.preBookingDate = preBookingDate;
    }
}
