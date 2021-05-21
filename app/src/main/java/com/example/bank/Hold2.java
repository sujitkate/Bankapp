package com.example.bank;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Hold2 extends RecyclerView.ViewHolder
{

    TextView mName, mPhonenumber, mBalance, mRupee, mRupeeslash, mName1, mName2, mDate, mTransc_status;
    ImageView mPhone, mArrow;
    View mView;

    public Hold2(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        mName = itemView.findViewById(R.id.custNameID);
        mPhonenumber = itemView.findViewById(R.id.mobID);
        mBalance = itemView.findViewById(R.id.balID);
       mRupee = itemView.findViewById(R.id.rupee);
        mName1 = itemView.findViewById(R.id.name1);
        mName2 = itemView.findViewById(R.id.name2);
        mDate = itemView.findViewById(R.id.date);
        mTransc_status = itemView.findViewById(R.id.transaction_status);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
    }

    private Hold2.ClickListener mClickListener;

    public interface  ClickListener
    {
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(Hold2.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
