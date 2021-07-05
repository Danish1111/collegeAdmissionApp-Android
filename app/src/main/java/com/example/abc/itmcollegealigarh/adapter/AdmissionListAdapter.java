package com.example.abc.itmcollegealigarh.adapter;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc.itmcollegealigarh.R;
import com.example.abc.itmcollegealigarh.model.MyModel;

import java.util.List;

public class AdmissionListAdapter extends RecyclerView.Adapter<AdmissionListAdapter.MyViewHolder> {

    private List<MyModel> myModelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName,tvCoarse,tvStream,tvEmail,tvPhone;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvCoarse = view.findViewById(R.id.tv_coarse);
            tvStream = view.findViewById(R.id.tv_stream);
            tvEmail = view.findViewById(R.id.tv_email);
            tvPhone = view.findViewById(R.id.tv_phone);
        }
    }


    public AdmissionListAdapter(List<MyModel> myModelList) {
        this.myModelList = myModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admission_list_adapter_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyModel myModel = myModelList.get(position);
        holder.tvName.setText(myModel.getName());
        holder.tvCoarse.setText(myModel.getCourse());
        holder.tvStream.setText(myModel.getStream());
        holder.tvPhone.setText("Ph: "+myModel.getMobile());
        holder.tvEmail.setText("Email: "+myModel.getEmail());
    }

    @Override
    public int getItemCount() {
        return myModelList.size();
    }
}
