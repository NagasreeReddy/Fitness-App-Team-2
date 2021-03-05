package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.adapters.MyFeedbacksAdapter;
import com.fitnessapp.adapters.MyGoalsAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyFeedbackPojo;
import com.fitnessapp.models.MyGoals;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMyGoalsActivity extends AppCompatActivity {

    ListView list_view;
    ProgressDialog progressDialog;
    List<MyGoals> mygoals;
    MyGoalsAdapter myGoalsAdapter;
    SharedPreferences sharedPreferences;
    String session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_goals);

        getSupportActionBar().setTitle("My Goals");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_name", "def-val");

        list_view=(ListView)findViewById(R.id.list_view);
        mygoals= new ArrayList<>();
        getMyGoals();
    }
    public void getMyGoals(){
        progressDialog = new ProgressDialog(ViewMyGoalsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyGoals>> call = service.getmygoals(session);
        call.enqueue(new Callback<List<MyGoals>>() {
            @Override
            public void onResponse(Call<List<MyGoals>> call, Response<List<MyGoals>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(ViewMyGoalsActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }
                if(response.body().size()==0){
                    Toast.makeText(ViewMyGoalsActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    mygoals=response.body();
                    myGoalsAdapter =new MyGoalsAdapter(mygoals, ViewMyGoalsActivity.this);
                    list_view.setAdapter(myGoalsAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<MyGoals>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewMyGoalsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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