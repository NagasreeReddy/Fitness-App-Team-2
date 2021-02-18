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
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.ResponseData;
import com.fitnessapp.models.VerifyTrainerPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyTrainerAdapter extends BaseAdapter {
    List<VerifyTrainerPojo> verifytrainer;
    Context cnt;

    public VerifyTrainerAdapter(List<VerifyTrainerPojo> verifytrainer, Context cnt) {
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
        View obj2 = obj1.inflate(R.layout.adapter_verify_trainer, null);

        TextView tvName = (TextView) obj2.findViewById(R.id.tvName);
        tvName.setText("Name  :" + verifytrainer.get(pos).getFname()+" "+verifytrainer.get(pos).getLname());

        TextView tvGender = (TextView) obj2.findViewById(R.id.tvGender);
        tvGender.setText("Gender  :" + verifytrainer.get(pos).getGender());

        TextView tvEmail = (TextView) obj2.findViewById(R.id.tvEmail);
        tvEmail.setText("Email  :" + verifytrainer.get(pos).getEmail());

        TextView tvExperience = (TextView) obj2.findViewById(R.id.tvExperience);
        tvExperience.setText("Experience  :" + verifytrainer.get(pos).getExp());

        TextView tvStates = (TextView) obj2.findViewById(R.id.tvStates);
        tvStates.setText("Status  :" + verifytrainer.get(pos).getVerify());


        Button btnAccept = (Button) obj2.findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateTrainerStatus(verifytrainer.get(pos).getTid(),verifytrainer.get(pos).getVerify());

            }
        });


        Button btnDeny = (Button) obj2.findViewById(R.id.btnDeny);
        btnDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpdateTrainerStatus(verifytrainer.get(pos).getTid(),verifytrainer.get(pos).getVerify());

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