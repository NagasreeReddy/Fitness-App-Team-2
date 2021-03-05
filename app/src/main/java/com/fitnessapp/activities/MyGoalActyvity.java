package com.fitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.fitnessapp.R;

public class MyGoalActyvity extends AppCompatActivity {
    CardView cdGetFittet, cdLoseWait, cdGainMuscle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goal_actyvity);

        getSupportActionBar().setTitle("Goal");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cdGetFittet = (CardView) findViewById(R.id.cdGetFittet);
        cdGetFittet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyGoalActyvity.this, ScreenOne.class));
                finish();

            }
        });
        cdLoseWait = (CardView) findViewById(R.id.cdLoseWait);
        cdLoseWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyGoalActyvity.this, ScreenOne.class));
                finish();

            }
        });
        cdGainMuscle = (CardView) findViewById(R.id.cdGainMuscle);
        cdGainMuscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyGoalActyvity.this, ScreenOne.class));
                finish();
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