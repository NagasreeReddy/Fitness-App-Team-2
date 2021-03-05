package com.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

public class VerifyTrainerPojo {

    @SerializedName("dob")
    public String dob;

    @SerializedName("tid")
    public String tid;

    @SerializedName("email")
    public String email;

    @SerializedName("exp")
    public String exp;

    @SerializedName("fname")
    public String fname;

    @SerializedName("gender")
    public String gender;

    @SerializedName("lname")
    public String lname;

    @SerializedName("password")
    public String password;

    @SerializedName("rating")
    public String rating;

    @SerializedName("utype")
    public String utype;

    @SerializedName("phone")
    private String phone;


    @SerializedName("verify")
    public String verify;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
