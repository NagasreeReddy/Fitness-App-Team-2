package com.fitnessapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
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

public class AdminLoginPage extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword;
    Button btn_signin;
    TextView tvSignupHere,tv_forgot_pass;
    ProgressDialog pd;
    AutoCompleteTextView spin_drop_down;
    String[] roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getSupportActionBar().setTitle("Admin Login");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editTextEmail=(TextInputEditText)findViewById(R.id.editTextEmail);
        editTextPassword=(TextInputEditText)findViewById(R.id.editTextPassword);

        tvSignupHere=(TextView)findViewById(R.id.tvSignupHere);
        tvSignupHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this,TrainerRegistrationActivity.class));


            }
        });
        tv_forgot_pass=(TextView)findViewById(R.id.tv_forgot_pass);
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this,ForgotPasswordActivity.class));


            }
        });

        btn_signin=(Button)findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextEmail.getText().toString().isEmpty()){
                    Toast.makeText(AdminLoginPage.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextPassword.getText().toString().isEmpty()){
                    Toast.makeText(AdminLoginPage.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(editTextEmail.getText().toString().equals("admin") && editTextPassword.getText().toString().equals("admin")) {
                        startActivity(new Intent(AdminLoginPage.this, AdminDashBoardActivity.class));

                    }else {
                        Toast.makeText(getApplicationContext(), "WrongCredentials", Toast.LENGTH_SHORT).show();
                    }
                }
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