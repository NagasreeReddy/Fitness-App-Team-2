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
import android.widget.EditText;
import android.widget.RatingBar;
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

public class GiveFeedbackActivity extends AppCompatActivity {
    TextView tvCustomer,tvMessage,tvDate,tvTime,tvStates;
    TextInputEditText etMessage,etName;
    RatingBar ratingBar;
    Button btnFeedback;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_feedback);

        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
         session = sharedPreferences.getString("user_name", "def-val");

        tvCustomer=(TextView)findViewById(R.id.tvCustomer);
        tvMessage=(TextView)findViewById(R.id.tvMessage);
        tvDate=(TextView)findViewById(R.id.tvDate);
        tvTime=(TextView)findViewById(R.id.tvTime);
        tvStates=(TextView)findViewById(R.id.tvStates);

        tvCustomer.setText("Trainer Name: "+getIntent().getStringExtra("name"));
        tvMessage.setText("Message: "+getIntent().getStringExtra("message"));
        tvDate.setText("Date: "+getIntent().getStringExtra("date"));
        tvTime.setText("Time: "+getIntent().getStringExtra("time"));
        tvStates.setText("Status: "+getIntent().getStringExtra("status"));

        etMessage=(TextInputEditText)findViewById(R.id.etMessage);
        etName=(TextInputEditText)findViewById(R.id.etName);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);

        btnFeedback=(Button)findViewById(R.id.btnFeedback);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMessage.getText().toString().isEmpty()){
                    Toast.makeText(GiveFeedbackActivity.this, "Please Give feedback", Toast.LENGTH_SHORT).show();
                    return;
                }
                giveFeedback();

            }
        });

    }
    ProgressDialog pd;
    public  void giveFeedback() {
        pd= new ProgressDialog(GiveFeedbackActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
