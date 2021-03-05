package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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
                Intent i = new Intent(MyGoalActyvity.this, ScreenOne.class);
                i.putExtra("Goalname", "Get Fitter");
                startActivity(i);
                finish();

            }
        });
        cdLoseWait = (CardView) findViewById(R.id.cdLoseWait);
        cdLoseWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MyGoalActyvity.this, ScreenOne.class);
                i.putExtra("Goalname", "Gain Muscle");
                startActivity(i);
                finish();

            }
        });
        cdGainMuscle = (CardView) findViewById(R.id.cdGainMuscle);
        cdGainMuscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyGoalActyvity.this, ScreenOne.class);
                i.putExtra("Goalname", "Lose Weight");
                startActivity(i);
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