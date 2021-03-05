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

public class ScreenOne extends AppCompatActivity {
    EditText btnFeet,btnCm;
    RadioButton radioFt,radioInch;
    String ftInch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_one);

        getSupportActionBar().setTitle("Goal");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radioFt=(RadioButton)findViewById(R.id.radioFt);
        radioInch=(RadioButton)findViewById(R.id.radioInch);

        btnFeet=(EditText)findViewById(R.id.btnFeet);
        btnCm=(EditText)findViewById(R.id.btnCm);
        radioFunction();


        Button btnSubmit=(Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radioFt.isChecked()){

                    if(btnFeet.getText().toString().isEmpty()) {
                        Toast.makeText(ScreenOne.this, "Please enter feet", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        ftInch=btnFeet.getText().toString()+" Feet";

                    }
                }
                else if(radioInch.isChecked()){

                    if(btnCm.getText().toString().isEmpty()){
                        Toast.makeText(ScreenOne.this, "Please enter Cm", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        ftInch=btnCm.getText().toString()+" Inch";


                    }

                }
                else
                {
                    Toast.makeText(ScreenOne.this, "Please Select Height in Feet or Inch", Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent intent=new Intent(ScreenOne.this,ScreenTwo.class);
                intent.putExtra("height",ftInch);
                intent.putExtra("Goalname",getIntent().getStringExtra("Goalname"));
                startActivity(intent);
                finish();


            }
        });
    }
    RadioGroup radiotall;
    public void radioFunction(){

        radiotall = (RadioGroup) findViewById(R.id.radiotall);
        radiotall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(checkedId==R.id.radioFt)
                { btnCm.setVisibility(View.GONE);
                    btnFeet.setVisibility(View.VISIBLE);
                }

                if(checkedId==R.id.radioInch)
                { btnFeet.setVisibility(View.GONE);
                    btnCm.setVisibility(View.VISIBLE);

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