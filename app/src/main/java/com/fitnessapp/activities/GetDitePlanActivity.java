package com.fitnessapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;
import com.fitnessapp.Utils;
import com.fitnessapp.adapters.GetDitePlanAdapter;
import com.fitnessapp.adapters.MyFeedbacksAdapter;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.GetDitePlanPojo;
import com.fitnessapp.models.MyFeedbackPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDitePlanActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<GetDitePlanPojo> ditePlanPojo;
    GetDitePlanAdapter getDitePlanAdapter;
    Spinner spinDitename;
    String[] diteplannames = {"The Paleo Diet", "The Vegan Diet"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_diteplan);

        getSupportActionBar().setTitle("Diet Plans");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        list_view = (ListView) findViewById(R.id.list_view);
        ditePlanPojo = new ArrayList<>();
        getDitePlan();

        spinDitename = (Spinner) findViewById(R.id.spinDitename);
        spinDitename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    getDitePlanAdapter.setSearchplan(spinDitename.getSelectedItem().toString());
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

      /*  ArrayAdapter aa = new ArrayAdapter(GetDitePlanActivity.this, android.R.layout.simple_spinner_item, diteplannames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDitename.setAdapter(aa);

        spinDitename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if (pos > 0) {
                    getDitePlanAdapter.searchDitePlan(spinDitename.getSelectedItem().toString());



                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


    }

    public void getDitePlan() {
        progressDialog = new ProgressDialog(GetDitePlanActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetDitePlanPojo>> call = service.getdietplans();
        call.enqueue(new Callback<List<GetDitePlanPojo>>() {
            @Override
            public void onResponse(Call<List<GetDitePlanPojo>> call, Response<List<GetDitePlanPojo>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(GetDitePlanActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    ditePlanPojo = response.body();
                    getDitePlanAdapter = new GetDitePlanAdapter(ditePlanPojo, GetDitePlanActivity.this);
                    list_view.setAdapter(getDitePlanAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<GetDitePlanPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GetDitePlanActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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