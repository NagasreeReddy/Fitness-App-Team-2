package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScreenFour extends AppCompatActivity {
    RadioButton radio0, radio01, radio02;
    String target_bodyfat;
    SharedPreferences sharedPreferences;
    String session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_four);


        getSupportActionBar().setTitle("Goal");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_name", "def-val");

        radio0 = (RadioButton) findViewById(R.id.radio0);
        radio01 = (RadioButton) findViewById(R.id.radio01);
        radio02 = (RadioButton) findViewById(R.id.radio02);

        radio0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                radio0.setChecked(true);
                radio01.setChecked(false);
                radio02.setChecked(false);
            }
        });

        radio01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                radio0.setChecked(false);
                radio01.setChecked(true);
                radio02.setChecked(false);
            }
        });

        radio02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                radio0.setChecked(false);
                radio01.setChecked(false);
                radio02.setChecked(true);
            }
        });

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radio0.isChecked()) {
                    target_bodyfat = "10-20";
                } else if (radio01.isChecked()) {
                    target_bodyfat = "20-40";

                } else if (radio02.isChecked()) {
                    target_bodyfat = "40-70";

                } else {
                    Toast.makeText(ScreenFour.this, "Please Select Target Body Fat", Toast.LENGTH_SHORT).show();
                    return;
                }

                submitGoalDetails();

            }
        });
    }

    ProgressDialog progressDialog;

    private void submitGoalDetails() {
        String goalname = getIntent().getStringExtra("Goalname");
        String height = getIntent().getStringExtra("height");
        String weight = getIntent().getStringExtra("weight");
        String currentbodyfat = getIntent().getStringExtra("currentbodyfat");

        progressDialog = new ProgressDialog(ScreenFour.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.addgoal(goalname, height, weight, currentbodyfat, target_bodyfat, session);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(ScreenFour.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ScreenFour.this, UserDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(ScreenFour.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ScreenFour.this, t.getMessage(), Toast.LENGTH_LONG).show();
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