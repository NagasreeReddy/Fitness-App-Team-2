package com.fitnessapp.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.activities.AdminDashBoardActivity;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyBookingsPojo;
import com.fitnessapp.models.MyFeedbackPojo;
import com.fitnessapp.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFeedbacksAdapter extends BaseAdapter {
    List<MyFeedbackPojo> myFeedbackPojo;
    Context cnt;

    public MyFeedbacksAdapter(List<MyFeedbackPojo> feedback, Context cnt) {
        this.myFeedbackPojo = feedback;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return myFeedbackPojo.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_my_feedbacks, null);

        TextView tvName = (TextView) obj2.findViewById(R.id.tvName);
        tvName.setText("Name: " + myFeedbackPojo.get(pos).getName());

        TextView tvMessage = (TextView) obj2.findViewById(R.id.tvMessage);
        tvMessage.setText("Message: " + myFeedbackPojo.get(pos).getMsg());

        TextView tvEmail = (TextView) obj2.findViewById(R.id.tvEmail);
        tvEmail.setText("Email: " + myFeedbackPojo.get(pos).getCustomer());

        RatingBar ratingBar=(RatingBar)obj2.findViewById(R.id.ratingBar);
        ratingBar.setRating(Float.parseFloat(myFeedbackPojo.get(pos).getRating()));


        return obj2;
    }
}