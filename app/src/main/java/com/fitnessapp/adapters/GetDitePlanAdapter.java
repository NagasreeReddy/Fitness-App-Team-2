package com.fitnessapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fitnessapp.R;
import com.fitnessapp.models.GetDitePlanPojo;
import com.fitnessapp.models.MyFeedbackPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GetDitePlanAdapter extends BaseAdapter {
    List<GetDitePlanPojo> ditePlanPojo,searchplan;
    Context cnt;

    public GetDitePlanAdapter(List<GetDitePlanPojo> dplan, Context cnt) {
        this.searchplan=dplan;
        this.cnt = cnt;
        this.ditePlanPojo=new ArrayList<GetDitePlanPojo>();
        this.ditePlanPojo.addAll(dplan);

       /* this.searchplan=dplan;
        this.cnt=cnt;
        this.ditePlanPojo = new ArrayList<GetDitePlanPojo>();
        this.ditePlanPojo.addAll(dplan);*/
    }

    @Override
    public int getCount() {
        return ditePlanPojo.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_get_diteplan, null);

        TextView tvDitename = (TextView) obj2.findViewById(R.id.tvDitename);
        tvDitename.setText("Dite Name: " + ditePlanPojo.get(pos).getDname());

        TextView tvDesc = (TextView) obj2.findViewById(R.id.tvDesc);
        tvDesc.setText("Description: " + ditePlanPojo.get(pos).getDes());



        return obj2;
    }

    public void setSearchplan(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        ditePlanPojo.clear();
        if (charText.length() == 0) {
            ditePlanPojo.addAll(searchplan);
        } else {
            for (GetDitePlanPojo wp : searchplan) {
                if (wp.getDname().toLowerCase(Locale.getDefault()).contains(charText)) {
                    ditePlanPojo.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}