package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.adapters.WomensWorkoutPlanAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MensWorkoutPojo;
import com.fitnessapp.models.WomensWorkoutPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WomensWorkoutPlanActivity extends AppCompatActivity {
    GridView idGVcourses;
    ProgressDialog progressDialog;
    List<WomensWorkoutPojo> womensWorkoutPojos;
    WomensWorkoutPlanAdapter womensWorkoutPlanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womens_workout_plan);

        getSupportActionBar().setTitle("Womens Workouts");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idGVcourses=(GridView)findViewById(R.id.idGVcourses);
        womensWorkoutPojos= new ArrayList<>();
        GetWomensWorkOuts();
    }

    public void GetWomensWorkOuts(){
        progressDialog = new ProgressDialog(WomensWorkoutPlanActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<WomensWorkoutPojo>> call = service.getwomensworkouts();
        call.enqueue(new Callback<List<WomensWorkoutPojo>>() {
            @Override
            public void onResponse(Call<List<WomensWorkoutPojo>> call, Response<List<WomensWorkoutPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(WomensWorkoutPlanActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    womensWorkoutPojos=response.body();
                    womensWorkoutPlanAdapter =new WomensWorkoutPlanAdapter(womensWorkoutPojos,WomensWorkoutPlanActivity.this);
                    idGVcourses.setAdapter(womensWorkoutPlanAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<WomensWorkoutPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(WomensWorkoutPlanActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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