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
import com.fitnessapp.adapters.MyFeedbacksAdapter;
import com.fitnessapp.adapters.TrainerBookingsAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyBookingsPojo;
import com.fitnessapp.models.MyFeedbackPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFeedBackActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<MyFeedbackPojo> myFeedbackPojos;
    MyFeedbacksAdapter myFeedbacksAdapter;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feeedback);

        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
         session = sharedPreferences.getString("user_name", "def-val");

        list_view=(ListView)findViewById(R.id.list_view);
        myFeedbackPojos= new ArrayList<>();
        getMyFeedbacks();
    }
    public void getMyFeedbacks(){
        progressDialog = new ProgressDialog(MyFeedBackActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

