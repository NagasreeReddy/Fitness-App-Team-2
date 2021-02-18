package com.fitnessapp.adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyWorkoutsPojo;
import com.fitnessapp.models.ResponseData;
import com.fitnessapp.models.VerifyTrainerPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMyWorkoutsAdapter extends BaseAdapter {
    List<MyWorkoutsPojo> myWorkoutsPojos;
    Context cnt;

    public GetMyWorkoutsAdapter(List<MyWorkoutsPojo> myWorkoutsPojos, Context cnt) {
        this.myWorkoutsPojos = myWorkoutsPojos;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return myWorkoutsPojos.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_getmy_workout, null);

        TextView tvworkouttype = (TextView) obj2.findViewById(R.id.tvworkouttype);
        tvworkouttype.setText("Type :"+myWorkoutsPojos.get(pos).getWtype());

        TextView tvWorkoutLevel = (TextView) obj2.findViewById(R.id.tvWorkoutLevel);
        tvWorkoutLevel.setText("Level :"+myWorkoutsPojos.get(pos).getLevel());


        TextView tvWorkoutTime = (TextView) obj2.findViewById(R.id.tvWorkoutTime);
        tvWorkoutTime.setText("Time :"+myWorkoutsPojos.get(pos).getTim());

        TextView tvGender = (TextView) obj2.findViewById(R.id.tvGender);
        tvGender.setText("Gender :"+myWorkoutsPojos.get(pos).getGender());



        TextView tvWorkoutName = (TextView) obj2.findViewById(R.id.tvWorkoutName);
        tvWorkoutName.setText("Name :"+myWorkoutsPojos.get(pos).getWname());


        TextView tvworkoutDesc = (TextView) obj2.findViewById(R.id.tvworkoutDesc);
        tvworkoutDesc.setText("Desc :"+myWorkoutsPojos.get(pos).getDes());


        TextView tvVlink = (TextView) obj2.findViewById(R.id.tvVlink);
        tvVlink.setText("Link :"+myWorkoutsPojos.get(pos).getVlink());




        Button btnEdit = (Button) obj2.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cnt, "Coming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        Button btnDelete = (Button) obj2.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertbox(myWorkoutsPojos.get(pos).getId());

            }
        });



        return obj2;
    }
    public void alertbox(String Id){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(cnt);
        builder1.setMessage("Do you want to delete this workout.");
        builder1.setCancelable(true);

        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteContent(Id);
                        //dialog.cancel();
                    }
                });

        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    ProgressDialog progressDialog;
    public void deleteContent(String ID){
        progressDialog = new ProgressDialog(cnt);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deletecontent(ID);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(cnt,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(cnt, AdminDashBoardActivity.class);
                    cnt.startActivity(intent);
                    Toast.makeText(cnt," Workout Deleted successfully",Toast.LENGTH_SHORT).show();

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