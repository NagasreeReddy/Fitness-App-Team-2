package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDitePlanActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    TextInputEditText etDescription;
    Spinner spinDitename;
    Button btnAddDite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dite_plan);

        getSupportActionBar().setTitle("Add Diet Plan");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinDitename=(Spinner)findViewById(R.id.spinDitename);
        etDescription=(TextInputEditText)findViewById(R.id.etDescription);
        btnAddDite=(Button)findViewById(R.id.btnAddDite);
        btnAddDite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinDitename.getSelectedItem().toString().equals("Select Diet Name")){
                    Toast.makeText(AddDitePlanActivity.this, "Please Select Diet plan name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etDescription.getText().toString().isEmpty()){
                    Toast.makeText(AddDitePlanActivity.this, "Please write Description", Toast.LENGTH_SHORT).show();
                    return;
                }
                addDiteDetails();
            }
        });

    }

    private void addDiteDetails() {
        progressDialog = new ProgressDialog(AddDitePlanActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.adddiet(spinDitename.getSelectedItem().toString(),etDescription.getText().toString());
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(AddDitePlanActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(AddDitePlanActivity.this, AdminDashBoardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(AddDitePlanActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddDitePlanActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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