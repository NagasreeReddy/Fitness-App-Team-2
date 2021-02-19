package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fitnessapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnCoustomer=(Button)findViewById(R.id.btnCoustomer);
        btnCoustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CustomerLoginPage.class));
                //finish();

            }
        });
        Button btnTrainer=(Button)findViewById(R.id.btnTrainer);
        btnTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TrainerLoginPage.class));
                //finish();


            }
        });

        Button btnGuestUser=(Button)findViewById(R.id.btnGuestUser);
        btnGuestUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GuestWorkoutPlanActivity.class));
                //finish();


            }
        });
    }
}