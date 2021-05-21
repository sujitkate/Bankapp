package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryList extends AppCompatActivity {
    List<Model> histoty_list = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    HistoryAdaptor adapter;

    TextView history_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        history_empty = findViewById(R.id.txtHistory);

        showData();
    }

    private void showData() {
        histoty_list.clear();
        Cursor cursor = new DatabaseHelper(this).readtransferdata();

        while (cursor.moveToNext()) {
            String balancefromdb = cursor.getString(4);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            Model model = new Model(cursor.getString(2), cursor.getString(3), price, cursor.getString(1), cursor.getString(5));
            histoty_list.add(model);
        }


        adapter = new HistoryAdaptor(HistoryList.this, histoty_list);
        mRecyclerView.setAdapter(adapter);

        if(histoty_list.size() == 0){
            history_empty.setVisibility(View.VISIBLE);
        }

    }
}