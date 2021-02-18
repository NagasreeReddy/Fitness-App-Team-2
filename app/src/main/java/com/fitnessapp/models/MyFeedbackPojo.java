package com.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

public class MyFeedbackPojo {
    @SerializedName("customer")
    public String customer;

    @SerializedName("fid")
    public String fid;

    @SerializedName("msg")
    public String msg;

    @SerializedName("name")
    public String name;

    @SerializedName("rating")
    public String rating;

    @SerializedName("trainer")
    public String trainer;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}
