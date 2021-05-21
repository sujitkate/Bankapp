package com.example.bank;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdaptor extends RecyclerView.Adapter<Hold2>
{

    HistoryList historyList;
    List<Model> modelList;
    TextView mTransc_status;

    public HistoryAdaptor(HistoryList historyList1, List<Model> modelList1) {
        historyList = historyList1;
        modelList = modelList1;
    }


    @NonNull
    @Override
    public Hold2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.historysinglerow, parent, false);

        mTransc_status = itemView.findViewById(R.id.transaction_status);


        Hold2 viewHolder = new Hold2(itemView);

        viewHolder.setOnClickListener(new Hold2.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Hold2 holder, int position)
    {
        holder.mName1.setText(modelList.get(position).getName1());
        holder.mName2.setText(modelList.get(position).getName2());

        holder.mBalance.setText(modelList.get(position).getBalance());
        holder.mDate.setText(modelList.get(position).getDate());
        holder.mTransc_status.setText(modelList.get(position).getTransaction_status());

        if(modelList.get(position).getTransaction_status().equals("Failed")){
            holder.mTransc_status.setTextColor(Color.parseColor("#f40404"));
        }else{
            holder.mTransc_status.setTextColor(Color.parseColor("#4BB543"));
        }


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
