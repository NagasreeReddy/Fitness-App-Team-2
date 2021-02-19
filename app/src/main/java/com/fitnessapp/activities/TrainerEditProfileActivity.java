package com.fitnessapp.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.EditProfilePojo;
import com.fitnessapp.models.ResponseData;
import com.fitnessapp.models.TrainerEditProfilePojo;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  TrainerEditProfileActivity extends AppCompatActivity {
    TextInputEditText etFirstName,etLastName,etEmail,etPassword,ertDateOfBirth,etExperience,etStatus;
    ProgressDialog progressDialog;
    RadioGroup radioSex;
    RadioButton radioMale,radioFemale;
    int mYear,mMonth,mDay;
    String DAY,MONTH,YEAR;
    Button btnUpdateProfile;
    SharedPreferences sharedPreferences;
    List<TrainerEditProfilePojo> a1;
    ResponseData a2;
    String utype,rating,tid,status;
    RatingBar ratingBar;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_edit_profile);

        getSupportActionBar().setTitle("My Profile");
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
        etStatus=(TextInputEditText)findViewById(R.id.etStatus);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        ertDateOfBirth.setFocusable(false);
        ertDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker();
            }
        });

        etEmail.setEnabled(false);

        getMyProfile();
        btnUpdateProfile=(Button)findViewById(R.id.btnUpdateProfile);
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateProfile();
            }
        });
    }
    public void getMyProfile(){
        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        String session = sharedPreferences.getString("user_name", "def-val");
        progressDialog = new ProgressDialog(TrainerEditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<TrainerEditProfilePojo>> call = service.trainereditProfile(session);
        call.enqueue(new Callback<List<TrainerEditProfilePojo>>() {
            @Override
            public void onResponse(Call<List<TrainerEditProfilePojo>> call, Response<List<TrainerEditProfilePojo>> response) {
                progressDialog.dismiss();
                a1 = response.body();
                TrainerEditProfilePojo trainereditprofile = a1.get(0);

                etFirstName.setText(trainereditprofile.getFname());
                etLastName.setText(trainereditprofile.getLname());
                etEmail.setText(trainereditprofile.getEmail());
                etPassword.setText(trainereditprofile.getPassword());
                etExperience.setText(trainereditprofile.getExp());
                ertDateOfBirth.setText(trainereditprofile.getDob());
                etStatus.setText(trainereditprofile.getStatus());
                ratingBar.setRating(Float.parseFloat(trainereditprofile.getRating()));

                 utype=trainereditprofile.getUtype();
                 rating=trainereditprofile.getRating();
                 tid=trainereditprofile.getTid();
                 status=trainereditprofile.getStatus();

            }

            @Override
            public void onFailure(Call<List<TrainerEditProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TrainerEditProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void updateProfile () {
        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String exp = etExperience.getText().toString();

        progressDialog = new ProgressDialog(TrainerEditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.updateTrainerProfile(fname,email,lname,exp,rating,status,utype,tid,password);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                progressDialog.dismiss();
                a2 = response.body();

                if (response.body().status.equals("true")) {
                    Toast.makeText(TrainerEditProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(TrainerEditProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TrainerEditProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

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