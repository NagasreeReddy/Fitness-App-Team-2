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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.EditProfilePojo;
import com.fitnessapp.models.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    TextInputEditText etFirstName,etLastName,etEmail,etPassword,ertDateOfBirth;
    Button btnUpdateProfile;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    RadioGroup radioSex;
    RadioButton radioMale,radioFemale;
    int mYear,mMonth,mDay;
    String DAY,MONTH,YEAR;
    List<EditProfilePojo> a1;
    ResponseData a2;
    String utype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etFirstName=(TextInputEditText)findViewById(R.id.etFirstName);
        etLastName=(TextInputEditText)findViewById(R.id.etLastName);
        ertDateOfBirth=(TextInputEditText)findViewById(R.id.ertDateOfBirth);
        radioSex=(RadioGroup)findViewById(R.id.radioSex);
        radioMale=(RadioButton)findViewById(R.id.radioMale);
        radioFemale=(RadioButton)findViewById(R.id.radioFemale);
        etEmail=(TextInputEditText)findViewById(R.id.etEmail);
        etPassword=(TextInputEditText)findViewById(R.id.etPassword);

        etEmail.setEnabled(false);

        ertDateOfBirth.setFocusable(false);
        ertDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker();
            }
        });



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
        progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<EditProfilePojo>> call = service.editProfile(session);
        call.enqueue(new Callback<List<EditProfilePojo>>() {
            @Override
            public void onResponse(Call<List<EditProfilePojo>> call, Response<List<EditProfilePojo>> response) {
                progressDialog.dismiss();
                a1 = response.body();
                EditProfilePojo user1 = a1.get(0);
                if(user1.getGender().equals("Male")){
                    radioMale.setChecked(true);
                }
                else {
                    radioFemale.setChecked(true);
                }
                etFirstName.setText(user1.getFname());
                etLastName.setText(user1.getLname());
                etEmail.setText(user1.getEmail());
                etPassword.setText(user1.getPass());
                ertDateOfBirth.setText(user1.getDob());
                 utype=user1.getUtype();

            }

            @Override
            public void onFailure(Call<List<EditProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void updateProfile () {
        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String dob = ertDateOfBirth.getText().toString();

        progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.updateProfile(fname,email,lname,utype,password);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                progressDialog.dismiss();
                a2 = response.body();

                if (response.body().status.equals("true")) {
                    Toast.makeText(EditProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EditProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

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