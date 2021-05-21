package com.example.bank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adp2 extends RecyclerView.Adapter<Hold2>
{

    TransferTo SendtoUser;
    List<Model> modelList1;

    public Adp2( TransferTo context,List<Model> modelList)
    {
        SendtoUser = context;
        modelList1 = modelList;

    }

    @NonNull
    @Override
    public Hold2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);

        Hold2 viewHolder = new Hold2(itemView);

        viewHolder.setOnClickListener(new Hold2.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SendtoUser.selectuser(position);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Hold2 holder, int position)
    {
        holder.mName.setText(modelList1.get(position).getName());
        holder.mPhonenumber.setText(modelList1.get(position).getPhoneno());
        holder.mBalance.setText(modelList1.get(position).getBalance());
    }

    @Override
    public int getItemCount()
    {
       return modelList1.size();
    }

    public void setFilter(ArrayList<Model> newList){
        modelList1 = new ArrayList<>();
        modelList1.addAll(newList);
        notifyDataSetChanged();
    }
}
