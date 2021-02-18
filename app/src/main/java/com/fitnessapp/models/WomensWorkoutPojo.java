package com.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

public class WomensWorkoutPojo {


    @SerializedName("des")
    public String des;

    @SerializedName("gender")
    public String gender;

    @SerializedName("id")
    public String id;

    @SerializedName("photo")
    public String photo;

    @SerializedName("level")
    public String level;

    @SerializedName("tim")
    public String tim;

    @SerializedName("vlink")
    public String vlink;

    @SerializedName("wname")
    public String wname;

    @SerializedName("wtype")
    public String wtype;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTim() {
        return tim;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    public String getVlink() {
        return vlink;
    }

    public void setVlink(String vlink) {
        this.vlink = vlink;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }
}
