package com.fitnessapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerForgotPasswordActivity extends AppCompatActivity {
    TextInputEditText et_email;
    Button btn_submit,btn_otp,btn_pwd;
    SharedPreferences sharedPreferences;
    String uname;
    String sendotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().setTitle("Forget Password");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname=sharedPreferences.getString("user_name","");

        btn_submit=(Button)findViewById(R.id.btn_submit);
        et_email=(TextInputEditText) findViewById(R.id.et_email);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter your Email", Toast.LENGTH_LONG).show();
                    return;
                }
                forgotPassword();
            }
        });

    }
    public  void forgotPassword()
    {
        String email=et_email.getText().toString();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.forgottrainer(email);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().message.equals("true")) {
                    Toast.makeText(TrainerForgotPasswordActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Log.i("msg", "" + response.body().message);
                    finish();
                } else {
                    Toast.makeText(TrainerForgotPasswordActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
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
