package com.fitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.fitnessapp.R;
import com.google.android.material.navigation.NavigationView;

public class AdminDashBoardActivity extends AppCompatActivity {
    private ActionBarDrawerToggle t;
    private NavigationView navigation_view;
    private DrawerLayout layout_drawer;
    CardView cdVerifyTrainer, cdAddworkouts,cdAddDitePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        cdVerifyTrainer = (CardView) findViewById(R.id.cdVerifyTrainer);
        cdVerifyTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verifytrainer = new Intent(getApplicationContext(), VerifyTrainersActivity.class);
                startActivity(verifytrainer);

            }
        });

        cdAddworkouts = (CardView) findViewById(R.id.cdAddworkouts);
        cdAddworkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verifytrainer = new Intent(getApplicationContext(), AddWorkOutsActivity.class);
                startActivity(verifytrainer);

            }
        });


        cdAddDitePlan = (CardView) findViewById(R.id.cdAddDitePlan);
        cdAddDitePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verifytrainer = new Intent(getApplicationContext(), AddDitePlanActivity.class);
                startActivity(verifytrainer);

            }
        });
        navigationView();
    }

    private void navigationView() {
        layout_drawer = (DrawerLayout) findViewById(R.id.layout_drawer);
        t = new ActionBarDrawerToggle(this, layout_drawer, R.string.Open, R.string.Close);
        layout_drawer.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.myprofile:
                        Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.create_training:
                        Intent intent1 = new Intent(getApplicationContext(), CreateTrainingActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.edit_training:
                        Intent view_jobs = new Intent(getApplicationContext(), EditTrainingActivity.class);
                        startActivity(view_jobs);
                        break;

                    case R.id.view_training_content:
                        Intent trainngcontent = new Intent(getApplicationContext(), GetMyWorkoutActivity.class);
                        startActivity(trainngcontent);
                        break;

                    case R.id.logout:
                        Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(logout);
                        finish();
                        break;

                    default:
                        return true;
                }
                layout_drawer.closeDrawer(GravityCompat.START);
                return true;

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (layout_drawer.isDrawerOpen(GravityCompat.START)) {
            layout_drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (layout_drawer.isDrawerOpen(GravityCompat.START)) {
            layout_drawer.closeDrawer(GravityCompat.START);
        } else {
            layout_drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}