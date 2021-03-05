package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.adapters.GetVerifiedTrainerAdapter;
import com.fitnessapp.adapters.SearchTrainerAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.VerifyTrainerPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTrainerActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<VerifyTrainerPojo> verifyTrainers;
    SearchTrainerAdapter searchTrainerAdapter;
    RadioButton radioMale,radioFemale;
    RadioGroup radioSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_trainer);

        getSupportActionBar().setTitle("Search Trainer");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        radioMale=(RadioButton)findViewById(R.id.radioMale);
        radioFemale=(RadioButton)findViewById(R.id.radioFemale);

        radioMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchTrainers("Male");

            }
        });


        radioFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchTrainers("Female");

            }
        });

       /* if(radioMale.isChecked()){
            SearchTrainers("Male");
        }
        else {
            SearchTrainers("Female");
        }*/

    }
    public void SearchTrainers(String gender){
        progressDialog = new ProgressDialog(SearchTrainerActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<VerifyTrainerPojo>> call = service.searchtrainer(gender);
        call.enqueue(new Callback<List<VerifyTrainerPojo>>() {
            @Override
            public void onResponse(Call<List<VerifyTrainerPojo>> call, Response<List<VerifyTrainerPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(SearchTrainerActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    verifyTrainers=response.body();
                    searchTrainerAdapter =new SearchTrainerAdapter(verifyTrainers,SearchTrainerActivity.this);
                    list_view.setAdapter(searchTrainerAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<VerifyTrainerPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SearchTrainerActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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