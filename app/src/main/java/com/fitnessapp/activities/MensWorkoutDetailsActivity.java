package com.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fitnessapp.R;

public class MensWorkoutDetailsActivity extends AppCompatActivity {
    String URL="http://getfitt.club/getfit/";
    TextView tv_Workoutname,tvtim;
    ImageView Image_play;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mens_workout_details);

        getSupportActionBar().setTitle("Workouts");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageview=(ImageView)findViewById(R.id.imageview);
        Glide.with(this).load(URL+getIntent().getStringExtra("image")).into(imageview);

        TextView tv_Workoutname=(TextView)findViewById(R.id.tv_Workoutname);
        TextView tvWorkoutDesc=(TextView)findViewById(R.id.tvWorkoutDesc);
        TextView tvtim=(TextView)findViewById(R.id.tvtim);

        tv_Workoutname.setText(getIntent().getStringExtra("wname"));
        tvWorkoutDesc.setText(getIntent().getStringExtra("desc"));
        tvtim.setText(getIntent().getStringExtra("time"));
        url=getIntent().getStringExtra("url");

        Image_play=(ImageView)findViewById(R.id.Image_play);
        Image_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+url)));

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