package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.adapters.VerifyTrainerAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.VerifyTrainerPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyTrainersActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<VerifyTrainerPojo> verifyTrainers;
    VerifyTrainerAdapter verifyTrainerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_trainers);

        getSupportActionBar().setTitle("Verify Trainer");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        verifyTrainers= new ArrayList<>();


         GetTrainers();
    }
    public void GetTrainers(){
        progressDialog = new ProgressDialog(VerifyTrainersActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<VerifyTrainerPojo>> call = service.getAllTrainers();
        call.enqueue(new Callback<List<VerifyTrainerPojo>>() {
            @Override
            public void onResponse(Call<List<VerifyTrainerPojo>> call, Response<List<VerifyTrainerPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(VerifyTrainersActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    verifyTrainers=response.body();
                    verifyTrainerAdapter =new VerifyTrainerAdapter(verifyTrainers,VerifyTrainersActivity.this);
                    list_view.setAdapter(verifyTrainerAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<VerifyTrainerPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(VerifyTrainersActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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