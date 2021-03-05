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
import com.fitnessapp.activities.UserDashboardActivity;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyFeedbackPojo;
import com.fitnessapp.models.MyGoals;
import com.fitnessapp.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyGoalsAdapter extends BaseAdapter {
    List<MyGoals> mygoals;
    Context cnt;

    public MyGoalsAdapter(List<MyGoals> mygoals, Context cnt) {
        this.mygoals = mygoals;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return mygoals.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_my_goals, null);

        TextView tvName = (TextView) obj2.findViewById(R.id.tvName);
        tvName.setText("Goal Name: " + mygoals.get(pos).getGname());

        TextView tvMessage = (TextView) obj2.findViewById(R.id.tvMessage);
        tvMessage.setText("Current Fat: " + mygoals.get(pos).getCurrent_bodyfat());

        TextView tvEmail = (TextView) obj2.findViewById(R.id.tvEmail);
        tvEmail.setText("Target Fat: " + mygoals.get(pos).getTarget_bodyfat());

        Button btndelete = (Button) obj2.findViewById(R.id.btndelete);

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGoal(mygoals.get(pos).getGid());
            }
        });

        return obj2;
    }
    ProgressDialog progressDialog;
    public void deleteGoal(String id){
        progressDialog = new ProgressDialog(cnt);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deleteGoal(id);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(cnt,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(cnt, UserDashboardActivity.class);
                    cnt.startActivity(intent);
                    Toast.makeText(cnt," Deleted successfully",Toast.LENGTH_SHORT).show();

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