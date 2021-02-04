package com.fitnessapp.api;



import com.fitnessapp.models.EditProfilePojo;
import com.fitnessapp.models.ResponseData;
import com.fitnessapp.models.TrainerEditProfilePojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
//"http://getfitt.club/"
    @GET("/getfit/getfit_registration.php")
    Call<ResponseData> userRegistration(
            @Query("fname") String fname,
            @Query("lname") String lname,
            @Query("email") String email,
            @Query("pass") String pass,
            @Query("gender") String gender,
            @Query("dob") String dob,
            @Query("utype") String utype);

    @GET("/getfit/trainer_registration.php")
    Call<ResponseData> trainerRegistration(
            @Query("fname") String fname,
            @Query("lname") String lname,
            @Query("email") String email,
            @Query("pass") String pass,
            @Query("exp") String exp,
            @Query("gender") String gender,
            @Query("dob") String dob,
            @Query("utype") String utype);

    @GET("/getfit/getfit_login.php")
    Call<ResponseData> userLogin(
            @Query("email") String email,
            @Query("password") String password);

    @GET("/getfit/trainer_login.php?")
    Call<ResponseData> trainerLogin(
            @Query("email") String email,
            @Query("password") String password);

    @GET("/getfit/admin.php?")
    Call<ResponseData> adminLogin(
            @Query("email") String email,
            @Query("password") String password);


    @GET("/getfit/myprofile.php?")
    Call<List<EditProfilePojo>> editProfile(
            @Query("email") String email);


    @GET("/getfit/trainerprofile.php?")
    Call<List<TrainerEditProfilePojo>> trainereditProfile(
            @Query("email") String email);

    @GET("/getfit/updateprofile.php")
    Call<ResponseData> updateProfile(
            @Query("name") String name,
            @Query("email") String email,
            @Query("lname") String lname,
            @Query("utype") String utype,
            @Query("pass") String pass);


    @GET("/getfit/updatetrainerprofile.php")
    Call<ResponseData> updateTrainerProfile(
            @Query("name") String name,
            @Query("email") String email,
            @Query("lname") String lname,
            @Query("exp") String exp,
            @Query("rating") String rating,
            @Query("status") String status,
            @Query("utype") String utype,
            @Query("tid") String tid,
            @Query("password") String password);


    @GET("getfit/forgotPassword.php")
    Call<ResponseData> forgotPassword
            (
                    @Query("emailid") String emailid
            );
}
