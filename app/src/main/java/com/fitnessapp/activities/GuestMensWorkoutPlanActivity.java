package com.fitnessapp.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;
import com.fitnessapp.adapters.GuestMensWorkoutPlanAdapter;
import com.fitnessapp.adapters.MensWorkoutPlanAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MensWorkoutPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestMensWorkoutPlanActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<MensWorkoutPojo> mensWorkoutPojos;
    GuestMensWorkoutPlanAdapter guestMensWorkoutPlanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_mens_workout_plan);


        getSupportActionBar().setTitle("Mens Workouts");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        mensWorkoutPojos= new ArrayList<>();
        getMensWorkoutplan();
    }

    public void getMensWorkoutplan(){
        progressDialog = new ProgressDialog(GuestMensWorkoutPlanActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MensWorkoutPojo>> call = service.getmensworkouts();
        call.enqueue(new Callback<List<MensWorkoutPojo>>() {
            @Override
            public void onResponse(Call<List<MensWorkoutPojo>> call, Response<List<MensWorkoutPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(GuestMensWorkoutPlanActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    mensWorkoutPojos=response.body();
                    guestMensWorkoutPlanAdapter =new GuestMensWorkoutPlanAdapter(mensWorkoutPojos, GuestMensWorkoutPlanActivity.this);
                    list_view.setAdapter(guestMensWorkoutPlanAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<MensWorkoutPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GuestMensWorkoutPlanActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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