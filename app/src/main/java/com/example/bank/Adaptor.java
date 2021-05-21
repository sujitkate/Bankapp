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

public class Adaptor extends RecyclerView.Adapter<Adaptor.RecyclerViewHolder> {
Context context;
List<Model> list;

    static RecyclerClickInterface recyclerClickInterfaceVAR;

    public Adaptor(Context context1,List<Model> list1)
    { context=context1;
    list=list1;
   }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(context).inflate(R.layout.singlerow,parent,false);
     return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.mName.setText(list.get(position).getName());
        holder.mPhonenumber.setText(list.get(position).getPhoneno());
        holder.mBalnace.setText(list.get(position).getBalance());

    }

    @Override
    public int getItemCount() {
       return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv,mName,mPhonenumber,mBalnace;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mName=itemView.findViewById(R.id.custNameID);
            mBalnace=itemView.findViewById(R.id.balID);
            mPhonenumber=itemView.findViewById(R.id.mobID);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context,InfoSingle.class);
                    int posi = getAdapterPosition();
                  //  String p = posi+"";

                    i.putExtra("pos",list.get(posi).getPhoneno());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }
}
