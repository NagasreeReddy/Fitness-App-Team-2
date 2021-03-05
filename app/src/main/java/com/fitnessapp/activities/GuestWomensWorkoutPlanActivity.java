package com.fitnessapp.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;
import com.fitnessapp.adapters.GuestWomensWorkoutPlanAdapter;
import com.fitnessapp.adapters.WomensWorkoutPlanAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.WomensWorkoutPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestWomensWorkoutPlanActivity extends AppCompatActivity {
    GridView idGVcourses;
    ProgressDialog progressDialog;
    List<WomensWorkoutPojo> womensWorkoutPojos;
    GuestWomensWorkoutPlanAdapter guestWomensWorkoutPlanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_womens_workout_plan);

        getSupportActionBar().setTitle("Womens Workouts");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idGVcourses=(GridView)findViewById(R.id.idGVcourses);
        womensWorkoutPojos= new ArrayList<>();
        GetWomensWorkOuts();
    }

    public void GetWomensWorkOuts(){
        progressDialog = new ProgressDialog(GuestWomensWorkoutPlanActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<WomensWorkoutPojo>> call = service.guestwomens();
        call.enqueue(new Callback<List<WomensWorkoutPojo>>() {
            @Override
            public void onResponse(Call<List<WomensWorkoutPojo>> call, Response<List<WomensWorkoutPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(GuestWomensWorkoutPlanActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    womensWorkoutPojos=response.body();
                    guestWomensWorkoutPlanAdapter =new GuestWomensWorkoutPlanAdapter(womensWorkoutPojos, GuestWomensWorkoutPlanActivity.this);
                    idGVcourses.setAdapter(guestWomensWorkoutPlanAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<WomensWorkoutPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GuestWomensWorkoutPlanActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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