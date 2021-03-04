package com.fitnessapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.adapters.TrainerBookingsAdapter;
import com.fitnessapp.adapters.VerifyTrainerAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyBookingsPojo;
import com.fitnessapp.models.VerifyTrainerPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainersBookingActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<MyBookingsPojo> myBookingsPojos;
    TrainerBookingsAdapter trainerBookingsAdapter;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_booking);

        getSupportActionBar().setTitle("My Bookings");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
         session = sharedPreferences.getString("user_name", "def-val");

        list_view=(ListView)findViewById(R.id.list_view);
        myBookingsPojos= new ArrayList<>();
        getMyBookings();
    }
    public void getMyBookings(){
        progressDialog = new ProgressDialog(TrainersBookingActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

