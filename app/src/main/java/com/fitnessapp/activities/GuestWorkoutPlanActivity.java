package com.fitnessapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;

public class GuestWorkoutPlanActivity extends AppCompatActivity {
    Button btnMen,BtnWomens;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_workout_plan);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Would u like to Register ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(GuestWorkoutPlanActivity.this,"Would u like to Register clicked yes button",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(GuestWorkoutPlanActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.cancel();
                //Toast.makeText(GuestWorkoutPlanActivity.this,"You clicked no button",Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
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