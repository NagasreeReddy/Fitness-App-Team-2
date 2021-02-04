package com.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

public class EditProfilePojo {

    @SerializedName("dob")
    public String dob;

    @SerializedName("email")
    public String email;

    @SerializedName("fname")
    public String fname;

    @SerializedName("gender")
    public String gender;

    @SerializedName("lname")
    public String lname;

    @SerializedName("utype")
    public String utype;

    @SerializedName("pass")
    public String pass;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
