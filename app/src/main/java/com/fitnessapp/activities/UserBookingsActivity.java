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
import com.fitnessapp.adapters.UserBookingsAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.UserBookingsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserBookingsActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<UserBookingsPojo> myBookingsPojos;
    UserBookingsAdapter userBookingsAdapter;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booking);

        getSupportActionBar().setTitle("My Bookings");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_name", "def-val");

        list_view = (ListView) findViewById(R.id.list_view);
        myBookingsPojos = new ArrayList<>();
        getMyBookings();
    }

    public void getMyBookings() {
        progressDialog = new ProgressDialog(UserBookingsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();



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
    ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
    Call<List<UserBookingsPojo>> call = service.mybookings(session);
        call.enqueue(new Callback<List<UserBookingsPojo>>() {
        @Override
        public void onResponse(Call<List<UserBookingsPojo>> call, Response<List<UserBookingsPojo>> response) {
            progressDialog.dismiss();
            if (response.body() == null) {
                Toast.makeText(UserBookingsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
            } else {
                myBookingsPojos = response.body();
                userBookingsAdapter = new UserBookingsAdapter(myBookingsPojos, UserBookingsActivity.this);
                list_view.setAdapter(userBookingsAdapter);

            }
        }

        @Override
        public void onFailure(Call<List<UserBookingsPojo>> call, Throwable t) {
            progressDialog.dismiss();
            Toast.makeText(UserBookingsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
        }
    });
}