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
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerLoginPage extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword;
    Button btn_signin;
    TextView tvSignupHere,tv_forgot_pass;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_login_page);

        getSupportActionBar().setTitle("Trainer Login");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextEmail=(TextInputEditText)findViewById(R.id.editTextEmail);
        editTextPassword=(TextInputEditText)findViewById(R.id.editTextPassword);

        tvSignupHere=(TextView)findViewById(R.id.tvSignupHere);
        tvSignupHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrainerLoginPage.this,TrainerRegistrationActivity.class));


            }
        });
        tv_forgot_pass=(TextView)findViewById(R.id.tv_forgot_pass);
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrainerLoginPage.this,ForgotPasswordActivity.class));


            }
        });

        btn_signin=(Button)findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(editTextEmail.getText().toString().isEmpty()){
                    Toast.makeText(TrainerLoginPage.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextPassword.getText().toString().isEmpty()){
                    Toast.makeText(TrainerLoginPage.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    loginTrainer();
                    // startActivity(new Intent(CustomerLoginPage.this, UserDashboardActivity.class));


                }

            }
        });

    }
    public  void loginTrainer() {
        pd= new ProgressDialog(TrainerLoginPage.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.trainerLogin(editTextEmail.getText().toString(),editTextPassword.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("user_name",editTextEmail.getText().toString());
                    et.commit();
                    startActivity(new Intent(TrainerLoginPage.this, TrainerDashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(TrainerLoginPage.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(TrainerLoginPage.this, t.getMessage(), Toast.LENGTH_LONG).show();
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