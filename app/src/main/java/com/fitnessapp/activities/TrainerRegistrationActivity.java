package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerRegistrationActivity extends AppCompatActivity {
    Button btn_signin;
    TextView tv_already_have_acc;
    TextInputEditText etFirstName,etLastName,etEmail,etPassword,ertDateOfBirth,etExperience,etPhone;
    ProgressDialog progressDialog;
    RadioGroup radioSex;
    RadioButton radioMale,radioFemale;
    int mYear,mMonth,mDay;
    String DAY,MONTH,YEAR;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_registration);

        getSupportActionBar().setTitle("Trainer Registration");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etFirstName=(TextInputEditText)findViewById(R.id.etFirstName);
        etLastName=(TextInputEditText)findViewById(R.id.etLastName);
        ertDateOfBirth=(TextInputEditText)findViewById(R.id.ertDateOfBirth);
        etExperience=(TextInputEditText)findViewById(R.id.etExperience);
        radioSex=(RadioGroup)findViewById(R.id.radioSex);
        radioMale=(RadioButton)findViewById(R.id.radioMale);
        radioFemale=(RadioButton)findViewById(R.id.radioFemale);
        etEmail=(TextInputEditText)findViewById(R.id.etEmail);
        etPassword=(TextInputEditText)findViewById(R.id.etPassword);
        etPhone=(TextInputEditText)findViewById(R.id.etPhone);

        ertDateOfBirth.setFocusable(false);
        ertDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker();
            }
        });


        getGender();
        tv_already_have_acc=(TextView) findViewById(R.id.tv_already_have_acc);
        tv_already_have_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(TrainerRegistrationActivity.this, TrainerLoginPage.class));

            }
        });

        btn_signin=(Button)findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etFirstName.getText().toString().isEmpty()){
                    Toast.makeText(TrainerRegistrationActivity.this, "Please Enter Name..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etEmail.getText().toString().isEmpty()){
                    Toast.makeText(TrainerRegistrationActivity.this, "Please Enter Email..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(TrainerRegistrationActivity.this, "Please Enter password..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    startActivity(new Intent(TrainerRegistrationActivity.this, CustomerLoginPage.class));
                    submitData();

                }            }
        });
    }
    private void submitData() {
        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String experience = etExperience.getText().toString();
        String dob = ertDateOfBirth.getText().toString();
        String phone = etPhone.getText().toString();
        progressDialog = new ProgressDialog(TrainerRegistrationActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.trainerRegistration(fname,lname, email,password,experience,gender,dob,phone,"Trainer");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(TrainerRegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(TrainerRegistrationActivity.this, TrainerLoginPage.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(TrainerRegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TrainerRegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void datepicker() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DAY = dayOfMonth + "";
                        MONTH = monthOfYear + 1 + "";
                        YEAR = year + "";

                        ertDateOfBirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void getGender(){
        if (radioMale.isChecked()) {
            gender="Male";
        }
        else {
            gender="Female";

        }
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