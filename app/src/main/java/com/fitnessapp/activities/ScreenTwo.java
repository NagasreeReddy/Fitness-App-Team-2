package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fitnessapp.R;

public class ScreenTwo extends AppCompatActivity {
    EditText btnLbs, btnKg;
    RadioGroup radioWeight;
    RadioButton radiolbs, radioKg;
    String weightLbsKg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two);


        getSupportActionBar().setTitle("Goal");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radioWeight = (RadioGroup) findViewById(R.id.radioWeight);

        radiolbs = (RadioButton) findViewById(R.id.radiolbs);
        radioKg = (RadioButton) findViewById(R.id.radioKg);

        btnLbs = (EditText) findViewById(R.id.btnLbs);
        btnKg = (EditText) findViewById(R.id.btnKg);
        radioFunction();

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radiolbs.isChecked()) {

                    if (btnLbs.getText().toString().isEmpty()) {
                        Toast.makeText(ScreenTwo.this, "Please enter Weight in Lbs", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        weightLbsKg = btnLbs.getText().toString() + " Lbs";

                    }
                } else if (radioKg.isChecked()) {

                    if (btnKg.getText().toString().isEmpty()) {
                        Toast.makeText(ScreenTwo.this, "Please enter Weight in Kgs", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        weightLbsKg = btnKg.getText().toString() + " Kg";


                    }

                } else {
                    Toast.makeText(ScreenTwo.this, "Please Select Weight in Lbs or Kg", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(ScreenTwo.this, ScreenThree.class);
                intent.putExtra("weight", weightLbsKg);
                intent.putExtra("height", getIntent().getStringExtra("height"));
                intent.putExtra("Goalname", getIntent().getStringExtra("Goalname"));
                startActivity(intent);
                finish();


            }
        });
    }

    public void radioFunction() {
        radioWeight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radiolbs) {
                    btnKg.setVisibility(View.GONE);
                    btnLbs.setVisibility(View.VISIBLE);
                }

                if (checkedId == R.id.radioKg) {
                    btnLbs.setVisibility(View.GONE);
                    btnKg.setVisibility(View.VISIBLE);

                }
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