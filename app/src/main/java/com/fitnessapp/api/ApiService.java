package com.fitnessapp.api;



import com.fitnessapp.activities.GetMyWorkoutActivity;
import com.fitnessapp.models.EditProfilePojo;
import com.fitnessapp.models.MensWorkoutPojo;
import com.fitnessapp.models.MyBookingsPojo;
import com.fitnessapp.models.MyFeedbackPojo;
import com.fitnessapp.models.MyWorkoutsPojo;
import com.fitnessapp.models.ResponseData;
import com.fitnessapp.models.TrainerEditProfilePojo;
import com.fitnessapp.models.UserBookingsPojo;
import com.fitnessapp.models.VerifyTrainerPojo;
import com.fitnessapp.models.WomensWorkoutPojo;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {

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

    @GET("/getfit/addfeedback.php?")
    Call<ResponseData> addfeedback(
            @Query("customer") String customer,
            @Query("trainer") String trainer,
            @Query("msg") String msg,
            @Query("name") String name,
            @Query("rating") String rating);




    @GET("/getfit/booktrainer.php?")
    Call<ResponseData> booktrainer(
            @Query("customer") String customer,
            @Query("trainer") String trainer,
            @Query("msg") String msg,
            @Query("dat") String dat,
            @Query("tim") String tim,
            @Query("status") String status);




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
    Call<ResponseData> forgotPassword(@Query("emailid") String emailid);

    @GET("getfit/myrequests.php")
    Call<List<MyBookingsPojo>> myrequests(@Query("email") String email);



    @GET("getfit/mybookings.php")
    Call<List<UserBookingsPojo>> mybookings(@Query("email") String email);



    @GET("getfit/myfeedbacks.php")
    Call<List<MyFeedbackPojo>> myfeedbacks(@Query("email") String email);


    @GET("getfit/getalltrainers.php")
    Call<List<VerifyTrainerPojo>> getAllTrainers();


    @GET("getfit/getworkouts.php")
    Call<List<MyWorkoutsPojo>> getworkouts();


    @GET("getfit/getmensworkouts.php")
    Call<List<MensWorkoutPojo>> getmensworkouts();


    @GET("getfit/getwomensworkouts.php")
    Call<List<WomensWorkoutPojo>> getwomensworkouts();


    @GET("getfit/getusertrainers.php")
    Call<List<VerifyTrainerPojo>> getVerifiedTrainers();

    @GET("getfit/verifytrainer.php")
    Call<ResponseData> verifyTrainer (
            @Query("tid") String tid,
            @Query("verify") String verify
    );


    @GET("getfit/deletecontent.php")
    Call<ResponseData> deletecontent (
            @Query("id") String id);

    @Multipart
    @POST("getfit/addcontent.php")
    Call<ResponseData> userRegistration(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap
    );
}
