package com.fitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;

public class GuestWorkoutPlanActivity extends AppCompatActivity {
    Button btnMen,BtnWomens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_workout_plan);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnMen=(Button)findViewById(R.id.btnMen);
        btnMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GuestWorkoutPlanActivity.this,GuestMensWorkoutPlanActivity.class);
                startActivity(intent);
            }
        });
        BtnWomens=(Button)findViewById(R.id.BtnWomens);
        BtnWomens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GuestWorkoutPlanActivity.this,GuestWomensWorkoutPlanActivity.class);
                startActivity(intent);
            }
        });
        //btnMen.setBackgroundColor(Color.TRANSPARENT);
        //BtnWomens.setBackgroundColor(Color.TRANSPARENT);

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