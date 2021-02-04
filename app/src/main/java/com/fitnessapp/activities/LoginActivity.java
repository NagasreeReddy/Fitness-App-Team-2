package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    Button btn_signup,btn_signin,btnTrainerSignup,btnGuestUSer;
    AutoCompleteTextView spin_drop_down;
    String[] roles;
    TextInputEditText editTextEmail,editTextPassword;
    ProgressDialog pd;
    TextView tv_forgot_pass,tvGuestUSer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail=(TextInputEditText)findViewById(R.id.editTextEmail);
        editTextPassword=(TextInputEditText)findViewById(R.id.editTextPassword);



        roles = new String[] {"Admin", "User", "Trainer"};

        spin_drop_down = findViewById(R.id.spin_drop_down);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), R.layout.support_simple_spinner_dropdown_item, roles);
        spin_drop_down.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        spin_drop_down.setAdapter(adapter);
        tv_forgot_pass=(TextView)findViewById(R.id.tv_forgot_pass);
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));

            }
        });


        btn_signup=(Button)findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });
        btnTrainerSignup=(Button)findViewById(R.id.btnTrainerSignup);
        btnTrainerSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,TrainerRegistrationActivity.class));
            }
        });
        tvGuestUSer=(TextView) findViewById(R.id.tvGuestUSer);
        tvGuestUSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,TrainerRegistrationActivity.class));
            }
        });


        btn_signin=(Button)findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spin_drop_down.getText().toString().equals("Admin")){

                    if(editTextEmail.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(editTextPassword.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        loginFunction();

                    }

                }
                else if (spin_drop_down.getText().toString().equals("User")) {
                    if(editTextEmail.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(editTextPassword.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        loginUser();
                    }

                }
                else if (spin_drop_down.getText().toString().equals("Trainer")) {
                    if(editTextEmail.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(editTextPassword.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        loginTrainer();
                    }

                }
                else{
                    Toast.makeText(LoginActivity.this, "Please choose Logintype", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public  void loginFunction() {
        pd= new ProgressDialog(LoginActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.adminLogin(editTextEmail.getText().toString(),editTextPassword.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("user_name",editTextEmail.getText().toString());
                    et.commit();
                    startActivity(new Intent(LoginActivity.this, AdminDashBoardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void loginUser() {
        pd= new ProgressDialog(LoginActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.userLogin(editTextEmail.getText().toString(),editTextPassword.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("user_name",editTextEmail.getText().toString());
                    et.commit();
                    startActivity(new Intent(LoginActivity.this, UserDashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void loginTrainer() {
        pd= new ProgressDialog(LoginActivity.this);
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
                    startActivity(new Intent(LoginActivity.this, TrainerDashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}