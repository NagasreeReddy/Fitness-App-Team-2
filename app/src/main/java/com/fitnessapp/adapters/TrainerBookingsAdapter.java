package com.fitnessapp.adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessapp.R;
import com.fitnessapp.activities.AdminDashBoardActivity;
import com.fitnessapp.activities.TrainersBookingActivity;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.api.RetroClient;
import com.fitnessapp.models.MyBookingsPojo;
import com.fitnessapp.models.ResponseData;
import com.fitnessapp.models.VerifyTrainerPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerBookingsAdapter extends BaseAdapter {
    List<MyBookingsPojo> myBookingsPojos;
    Context cnt;

    public TrainerBookingsAdapter(List<MyBookingsPojo> myBookings, Context cnt) {
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
        View obj2 = obj1.inflate(R.layout.adapter_trainer_bookings, null);

        TextView tvCustomer = (TextView) obj2.findViewById(R.id.tvCustomer);
        tvCustomer.setText("Name  :" + myBookingsPojos.get(pos).getCustomer());

        TextView tvMessage = (TextView) obj2.findViewById(R.id.tvMessage);
        tvMessage.setText("Message  :" + myBookingsPojos.get(pos).getMsg());

        TextView tvDate = (TextView) obj2.findViewById(R.id.tvDate);
        tvDate.setText("Date  :" + myBookingsPojos.get(pos).getDat());

        TextView tvTime = (TextView) obj2.findViewById(R.id.tvTime);
        tvTime.setText("Time  :" + myBookingsPojos.get(pos).getTim());

        TextView tvStates = (TextView) obj2.findViewById(R.id.tvStates);
        tvStates.setText("Status  :" + myBookingsPojos.get(pos).getStatus());

       /* Button btnAccept = (Button) obj2.findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //UpdateTrainerStatus(myBookingsPojos.get(pos).getBid(),myBookingsPojos.get(pos).getVerify());

            }
        });


        Button btnDeny = (Button) obj2.findViewById(R.id.btnDeny);
        btnDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // UpdateTrainerStatus(myBookingsPojos.get(pos).getTid(),myBookingsPojos.get(pos).getVerify());

            }
        });*/

        Spinner spinUpdateStatus=(Spinner)obj2.findViewById(R.id.spinUpdateStatus);
        spinUpdateStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Update Status"))
                {

                }
                else if(selectedItem.equals("Confirmed")){
                    UpdateBookingStatus(myBookingsPojos.get(pos).getBid(),"Confirmed");
                }

                else if(selectedItem.equals("Denied")){
                    UpdateBookingStatus(myBookingsPojos.get(pos).getBid(),"Denied");
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        return obj2;
    }

    ProgressDialog progressDialog;
    public void UpdateBookingStatus(String ID,String verify){
        progressDialog = new ProgressDialog(cnt);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.updatebooking(ID,verify);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(cnt,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(cnt, TrainersBookingActivity.class);
                    cnt.startActivity(intent);
                    ((Activity)cnt).finish();
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