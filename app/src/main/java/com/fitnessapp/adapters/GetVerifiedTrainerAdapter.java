package com.fitnessapp.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.activities.AdminDashBoardActivity;
import com.fitnessapp.activities.TrainersInfoActivity;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.ResponseData;
import com.fitnessapp.models.VerifyTrainerPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetVerifiedTrainerAdapter extends BaseAdapter {
    List<VerifyTrainerPojo> verifytrainer;
    Context cnt;

    public GetVerifiedTrainerAdapter(List<VerifyTrainerPojo> verifytrainer, Context cnt) {
        this.verifytrainer = verifytrainer;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return verifytrainer.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_verified_trainers, null);

        TextView tvName = (TextView) obj2.findViewById(R.id.tvName);
        tvName.setText(" Name  :" + verifytrainer.get(pos).getFname()+" "+verifytrainer.get(pos).getLname());

        TextView tvGender = (TextView) obj2.findViewById(R.id.tvGender);
        tvGender.setText(" Gender  :" + verifytrainer.get(pos).getGender());

        TextView tvEmail = (TextView) obj2.findViewById(R.id.tvEmail);
        tvEmail.setText(" Email  :" + verifytrainer.get(pos).getEmail());


        TextView tvExperience = (TextView) obj2.findViewById(R.id.tvExperience);
        tvExperience.setText(" Experience  :" + verifytrainer.get(pos).getExp());

        RatingBar ratingBar=(RatingBar)obj2.findViewById(R.id.ratingBar);
        ratingBar.setRating(Float.parseFloat(verifytrainer.get(pos).getRating()));

        //verifytrainer.get(pos).getPhone().toString()
        ImageView img_call=(ImageView)obj2.findViewById(R.id.img_call);
        img_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + verifytrainer.get(pos).getPhone().toString()));
                cnt.startActivity(intent);
            }
        });

        Button btnInfo = (Button) obj2.findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, TrainersInfoActivity.class);
                intent.putExtra("name",verifytrainer.get(pos).getFname()+" "+verifytrainer.get(pos).getLname());
                intent.putExtra("gender",verifytrainer.get(pos).getGender());
                intent.putExtra("email",verifytrainer.get(pos).getEmail());
                intent.putExtra("experience",verifytrainer.get(pos).getExp());
                intent.putExtra("rating",verifytrainer.get(pos).getRating());
                intent.putExtra("dob",verifytrainer.get(pos).getDob());
                cnt.startActivity(intent);




            }
        });

        return obj2;
    }


}