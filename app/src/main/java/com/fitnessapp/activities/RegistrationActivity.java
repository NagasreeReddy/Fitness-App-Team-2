package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    Button btn_signin;
    TextView tv_already_have_acc;
    TextInputEditText etName,etMobilenumber,etEmail,etPassword;
    AutoCompleteTextView spin_drop_down;
    String[] roles;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName=(TextInputEditText)findViewById(R.id.etName);
        etMobilenumber=(TextInputEditText)findViewById(R.id.etMobilenumber);
        etEmail=(TextInputEditText)findViewById(R.id.etEmail);
        etPassword=(TextInputEditText)findViewById(R.id.etPassword);

        roles = new String[] {"Admin", "User"};
        spin_drop_down = findViewById(R.id.spin_drop_down);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), R.layout.support_simple_spinner_dropdown_item, roles);
        spin_drop_down.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        spin_drop_down.setAdapter(adapter);


        tv_already_have_acc=(TextView) findViewById(R.id.tv_already_have_acc);
        tv_already_have_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

            }
        });

        btn_signin=(Button)findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Name..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etMobilenumber.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter MObile number..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etEmail.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Email..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter password..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    //startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    submitData();

                }            }
        });
    }

    private void submitData() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String mobileno = etMobilenumber.getText().toString();
        String utype = spin_drop_down.getText().toString();

        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.userRegistration(name, email, mobileno,password,"User");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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