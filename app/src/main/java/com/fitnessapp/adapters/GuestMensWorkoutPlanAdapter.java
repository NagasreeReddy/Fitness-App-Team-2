package com.fitnessapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.fitnessapp.R;
import com.fitnessapp.activities.MensWorkoutDetailsActivity;
import com.fitnessapp.models.MensWorkoutPojo;

import java.util.List;

public class GuestMensWorkoutPlanAdapter extends BaseAdapter {
    List<MensWorkoutPojo> mensWorkoutPojos;
    Context cnt;
    String URL="http://getfitt.club/getfit/";

    public GuestMensWorkoutPlanAdapter(List<MensWorkoutPojo> mensWorkoutPojos, Context cnt) {
        this.mensWorkoutPojos = mensWorkoutPojos;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return mensWorkoutPojos.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup) {
        LayoutInflater obj1 = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2 = obj1.inflate(R.layout.adapter_guest_mens, null);

        TextView tvWorkoutLevel = (TextView) obj2.findViewById(R.id.tvWorkoutLevel);
        tvWorkoutLevel.setText(mensWorkoutPojos.get(pos).getLevel());

        TextView tvWorkoutName = (TextView) obj2.findViewById(R.id.tvWorkoutName);
        tvWorkoutName.setText(mensWorkoutPojos.get(pos).getWname());

        TextView tvTotalTime = (TextView) obj2.findViewById(R.id.tvTotalTime);
        tvTotalTime.setText(mensWorkoutPojos.get(pos).getTim());

        CardView cdMensWorkout=(CardView)obj2.findViewById(R.id.cdMensWorkout);


        Button btnTry=(Button)obj2.findViewById(R.id.btnTry);
        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, MensWorkoutDetailsActivity.class);
                intent.putExtra("image",mensWorkoutPojos.get(pos).getPhoto());
                intent.putExtra("wname",mensWorkoutPojos.get(pos).getWname());
                intent.putExtra("desc",mensWorkoutPojos.get(pos).getDes());
                intent.putExtra("time",mensWorkoutPojos.get(pos).getTim());
                intent.putExtra("url",mensWorkoutPojos.get(pos).getVlink());
                cnt.startActivity(intent);
            }
        });



        return obj2;
    }

}