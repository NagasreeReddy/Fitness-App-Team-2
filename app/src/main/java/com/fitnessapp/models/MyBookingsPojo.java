package com.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

public class MyBookingsPojo {
    @SerializedName("bid")
    public String bid;

    @SerializedName("customer")
    public String customer;

    @SerializedName("dat")
    public String dat;

    @SerializedName("msg")
    public String msg;

    @SerializedName("status")
    public String status;


    @SerializedName("tim")
    public String tim;

    @SerializedName("trainer")
    public String trainer;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTim() {
        return tim;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}
