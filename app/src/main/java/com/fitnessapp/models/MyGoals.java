package com.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

public class MyGoals {

    //[{"gid":"1","gname":"Get Fitter","tall":"5.7","weight":"60","current_bodyfat":"20","target_bodyfat":"10"}]
    @SerializedName("gid")
    private String gid;

    @SerializedName("gname")
    private String gname;

    @SerializedName("tall")
    private String tall;

    @SerializedName("weight")
    private String weight;

    @SerializedName("current_bodyfat")
    private String current_bodyfat;

    @SerializedName("target_bodyfat")
    private String target_bodyfat;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getTall() {
        return tall;
    }

    public void setTall(String tall) {
        this.tall = tall;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCurrent_bodyfat() {
        return current_bodyfat;
    }

    public void setCurrent_bodyfat(String current_bodyfat) {
        this.current_bodyfat = current_bodyfat;
    }

    public String getTarget_bodyfat() {
        return target_bodyfat;
    }

    public void setTarget_bodyfat(String target_bodyfat) {
        this.target_bodyfat = target_bodyfat;
    }
}
