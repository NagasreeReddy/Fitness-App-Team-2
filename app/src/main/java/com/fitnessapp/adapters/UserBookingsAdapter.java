package com.fitnessapp.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.activities.AdminDashBoardActivity;
import com.fitnessapp.activities.GiveFeedbackActivity;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyBookingsPojo;
import com.fitnessapp.models.ResponseData;
import com.fitnessapp.models.UserBookingsPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserBookingsAdapter extends BaseAdapter {
    List<UserBookingsPojo> myBookingsPojos;
    Context cnt;

    public UserBookingsAdapter(List<UserBookingsPojo> myBookings, Context cnt) {
        this.myBookingsPojos = myBookings;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return myBookingsPojos.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_user_bookings, null);

        TextView tvCustomer = (TextView) obj2.findViewById(R.id.tvCustomer);
        tvCustomer.setText("Trainer Name: " + myBookingsPojos.get(pos).getTrainer());

        TextView tvMessage = (TextView) obj2.findViewById(R.id.tvMessage);
        tvMessage.setText("Message: " + myBookingsPojos.get(pos).getMsg());

        TextView tvDate = (TextView) obj2.findViewById(R.id.tvDate);
        tvDate.setText("Date: " + myBookingsPojos.get(pos).getDat());

        TextView tvTime = (TextView) obj2.findViewById(R.id.tvTime);
        tvTime.setText("Time: " + myBookingsPojos.get(pos).getTim());

        TextView tvStates = (TextView) obj2.findViewById(R.id.tvStates);
        tvStates.setText("Status: " + myBookingsPojos.get(pos).getStatus());

        Button btnFeedback = (Button) obj2.findViewById(R.id.btnFeedback);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, GiveFeedbackActivity.class);
                intent.putExtra("name",myBookingsPojos.get(pos).getTrainer());
                intent.putExtra("message",myBookingsPojos.get(pos).getMsg());
                intent.putExtra("date",myBookingsPojos.get(pos).getDat());
                intent.putExtra("time",myBookingsPojos.get(pos).getTim());
                intent.putExtra("status",myBookingsPojos.get(pos).getStatus());
                cnt.startActivity(intent);

            }
        });


        return obj2;
    }

    ProgressDialog progressDialog;
    public void UpdateTrainerStatus(String ID,String verify){
        progressDialog = new ProgressDialog(cnt);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.verifyTrainer(ID,verify);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(cnt,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(cnt, AdminDashBoardActivity.class);
                    cnt.startActivity(intent);
                    Toast.makeText(cnt," Trainer Verified successfully",Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(cnt, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}