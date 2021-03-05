package com.fitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;

public class ScreenTwo extends AppCompatActivity {
    EditText btnLbs,btnKg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two);


        getSupportActionBar().setTitle("Goal");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLbs=(EditText)findViewById(R.id.btnLbs);
        btnKg=(EditText)findViewById(R.id.btnKg);

        Button btnSubmit=(Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnLbs.getText().toString().isEmpty()){
                    Toast.makeText(ScreenTwo.this, "Please enter Lbs", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(btnKg.getText().toString().isEmpty()){
                    Toast.makeText(ScreenTwo.this, "Please enter in Kgs", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent=new Intent(ScreenTwo.this,ScreenThree.class);
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