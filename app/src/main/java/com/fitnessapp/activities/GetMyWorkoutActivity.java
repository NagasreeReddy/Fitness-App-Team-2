package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.adapters.GetMyWorkoutsAdapter;
import com.fitnessapp.adapters.VerifyTrainerAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyWorkoutsPojo;
import com.fitnessapp.models.VerifyTrainerPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMyWorkoutActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<MyWorkoutsPojo> myWorkoutsPojos;
    GetMyWorkoutsAdapter getMyWorkoutsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_my_workout);

        getSupportActionBar().setTitle("All Workouts");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        myWorkoutsPojos= new ArrayList<>();
        getMyWorkouts();

    }
    public void getMyWorkouts(){
        progressDialog = new ProgressDialog(GetMyWorkoutActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyWorkoutsPojo>> call = service.getworkouts();
        call.enqueue(new Callback<List<MyWorkoutsPojo>>() {
            @Override
            public void onResponse(Call<List<MyWorkoutsPojo>> call, Response<List<MyWorkoutsPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(GetMyWorkoutActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    myWorkoutsPojos=response.body();
                    getMyWorkoutsAdapter =new GetMyWorkoutsAdapter(myWorkoutsPojos,GetMyWorkoutActivity.this);
                    list_view.setAdapter(getMyWorkoutsAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<MyWorkoutsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GetMyWorkoutActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}