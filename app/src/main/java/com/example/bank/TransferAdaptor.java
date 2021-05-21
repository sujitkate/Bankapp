package com.example.bank;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransferAdaptor extends RecyclerView.Adapter<TransferAdaptor.viewHold>
{
   Context context;
   List<Model> list;
    static RecyclerClickInterface recyclerClickInterfaceVAR;

    TransferTo transferTo;

    public TransferAdaptor(Context context, List<Model> list,RecyclerClickInterface recyclerClickInterfaceVAR1) {
        this.context = context;
        this.list = list;
        recyclerClickInterfaceVAR=recyclerClickInterfaceVAR1;
    }

    @NonNull
    @Override
    public viewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlerow,parent,false);
        return new viewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHold holder, int position)
    {
        holder.mName.setText(list.get(position).getName());
        holder.mPhonenumber.setText(list.get(position).getPhoneno());
        holder.mBalnace.setText(list.get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHold extends RecyclerView.ViewHolder
   {
       TextView tv,mName,mPhonenumber,mBalnace;

       public viewHold(@NonNull View itemView) {
           super(itemView);
           mName=itemView.findViewById(R.id.custNameID);
           mBalnace=itemView.findViewById(R.id.balID);
           mPhonenumber=itemView.findViewById(R.id.mobID);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   recyclerClickInterfaceVAR.OnClick(getAdapterPosition());

               }
           });
       }
   }
}