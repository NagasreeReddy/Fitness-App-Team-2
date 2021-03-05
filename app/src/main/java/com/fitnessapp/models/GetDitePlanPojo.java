package com.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

public class GetDitePlanPojo {
    @SerializedName("did")
    public String did;

    @SerializedName("dname")
    public String dname;

    @SerializedName("des")
    public String des;

    public GetDitePlanPojo(String did, String dname, String des) {
        this.did = did;
        this.dname = dname;
        this.des = des;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
