package com.fitnessapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.adapters.MyFeedbacksAdapter;
import com.fitnessapp.adapters.TrainerBookingsAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyBookingsPojo;
import com.fitnessapp.models.MyFeedbackPojo;
import com.fitnessapp.models.ResponseData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFeedBackActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<MyFeedbackPojo> myFeedbackPojos;
    MyFeedbacksAdapter myFeedbacksAdapter;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feeedback);

        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
         session = sharedPreferences.getString("user_name", "def-val");

        list_view=(ListView)findViewById(R.id.list_view);
        myFeedbackPojos= new ArrayList<>();
        getMyFeedbacks();
    }
    public void getMyFeedbacks(){
        progressDialog = new ProgressDialog(MyFeedBackActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.addfeedback(session,getIntent().getStringExtra("name"),etMessage.getText().toString(),etName.getText().toString(), String.valueOf(ratingBar.getRating()));

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(GiveFeedbackActivity.this, "Feedback Given Succussfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GiveFeedbackActivity.this, UserBookingsActivity.class));
                    finish();
                } else {
                    Toast.makeText(GiveFeedbackActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(GiveFeedbackActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
