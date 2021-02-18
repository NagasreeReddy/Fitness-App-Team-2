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

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyBookingsPojo>> call = service.myrequests(session);
        call.enqueue(new Callback<List<MyBookingsPojo>>() {
            @Override
            public void onResponse(Call<List<MyBookingsPojo>> call, Response<List<MyBookingsPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(TrainersBookingActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    myBookingsPojos=response.body();
                    trainerBookingsAdapter =new TrainerBookingsAdapter(myBookingsPojos, TrainersBookingActivity.this);
                    list_view.setAdapter(trainerBookingsAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<MyBookingsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TrainersBookingActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}