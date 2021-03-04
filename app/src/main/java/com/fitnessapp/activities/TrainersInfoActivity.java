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
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainersInfoActivity extends AppCompatActivity {
    TextView tvName,tvGender,tvEmail,tvExperience,tvDob;
    RatingBar ratingBar;
    TextInputEditText etMessage;
    ProgressDialog pd;
    String session,Date;
    SharedPreferences sharedPreferences;
    Button btnBook;
    Spinner spinTimings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainers_info);


        getSupportActionBar().setTitle("Book Trainer");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
         session = sharedPreferences.getString("user_name", "def-val");
        //Toast.makeText(this, ""+session+Date, Toast.LENGTH_SHORT).show();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
         Date = df.format(c);

        tvName=(TextView)findViewById(R.id.tvName);
        tvGender=(TextView)findViewById(R.id.tvGender);
        tvEmail=(TextView)findViewById(R.id.tvEmail);
        tvExperience=(TextView)findViewById(R.id.tvExperience);
        tvDob=(TextView)findViewById(R.id.tvDob);
        etMessage=(TextInputEditText)findViewById(R.id.etMessage);
        spinTimings=(Spinner)findViewById(R.id.spinTimings);

        tvName.setText("Name  :"+getIntent().getStringExtra("name"));
        tvGender.setText("Gender  :"+getIntent().getStringExtra("gender"));
        tvEmail.setText("Email  :"+getIntent().getStringExtra("email"));
        tvExperience.setText("Experience  :"+getIntent().getStringExtra("experience"));
        tvDob.setText("Date of Birth  :"+getIntent().getStringExtra("dob"));

        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setRating(Float.parseFloat(getIntent().getStringExtra("rating")));

        btnBook=(Button)findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinTimings.getSelectedItem().toString().equals("Timings")){
                    Toast.makeText(TrainersInfoActivity.this, "Please Choose timings", Toast.LENGTH_SHORT).show();
                    return;
                }
                bookATrainer();
            }
        });

    }

    public  void bookATrainer() {
        pd= new ProgressDialog(TrainersInfoActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();

