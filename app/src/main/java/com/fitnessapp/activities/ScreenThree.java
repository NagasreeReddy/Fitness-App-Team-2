package com.fitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;

public class ScreenThree extends AppCompatActivity {
    RadioButton radio0,radio01,radio02;
    String cbodyfat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_three);


        getSupportActionBar().setTitle("Goal");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radio0=(RadioButton)findViewById(R.id.radio0);
        radio01=(RadioButton)findViewById(R.id.radio01);
        radio02=(RadioButton)findViewById(R.id.radio02);

        radio0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                radio0.setChecked(true);
                radio01.setChecked(false);
                radio02.setChecked(false);
            }
        });

        radio01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                radio0.setChecked(false);
                radio01.setChecked(true);
                radio02.setChecked(false);
            }
        });

        radio02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                radio0.setChecked(false);
                radio01.setChecked(false);
                radio02.setChecked(true);
            }
        });



        Button btnSubmit=(Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radio0.isChecked()){

                    cbodyfat="Athletes";
                }
                else if(radio01.isChecked()){
                    cbodyfat="Fitness";

                }
                else  if(radio02.isChecked()){
                    cbodyfat="Obese";

                }
                else {
                    Toast.makeText(ScreenThree.this, "Please Select Current Body Fat", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent=new Intent(ScreenThree.this, ScreenFour.class);
                intent.putExtra("currentbodyfat",cbodyfat);
                intent.putExtra("height", getIntent().getStringExtra("height"));
                intent.putExtra("weight", getIntent().getStringExtra("weight"));
                intent.putExtra("Goalname", getIntent().getStringExtra("Goalname"));
                startActivity(intent);
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