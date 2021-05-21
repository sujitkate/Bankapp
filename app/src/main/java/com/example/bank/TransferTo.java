package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransferTo extends AppCompatActivity  {
RecyclerView recyclerView;
List<Model> list;
TransferAdaptor transferAdaptor;
//DatabaseHelper databaseHelper;
SQLiteDatabase db;
Button btn;

Adp2 ad;
EditText ac,name,mail,bal;
String date,restbal,b;
String phonenumber,currentamount,transferamount,name1,selectuser_phonenumber,remainingamount;
String username,userbal,selectuser_name,selectuser_balance;
final static String TAG="msg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to);


      //  btn=findViewById(R.id.btnAddID);

        recyclerView=findViewById(R.id.RecyclerID1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
        date = simpleDateFormat.format(calendar.getTime());
        //Toast.makeText(this, "got1", Toast.LENGTH_SHORT).show();

     Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            phonenumber = bundle.getString("phonenumber");
            name1 = bundle.getString("name");
            currentamount = bundle.getString("currentamount");
            transferamount = bundle.getString("transferamount");
            showData(phonenumber);
        }
        //Toast.makeText(this, "CurrentAmount"+currentamount, Toast.LENGTH_SHORT).show();
  }

    private void showData(String phonenumber)
    {
        list.clear();
        Log.d("DEMO",phonenumber);
        Cursor cursor = new DatabaseHelper(this).readselectuserdata(phonenumber);
        while(cursor.moveToNext()){
            String balancefromdb = cursor.getString(2);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            Model model = new Model(cursor.getString(0), cursor.getString(1), price);
            list.add(model);
        }

       // transferAdaptor = new TransferAdaptor(TransferTo.this, list,this);
       ad=new Adp2(TransferTo.this,list);
        recyclerView.setAdapter(ad);
    }


    public void selectuser(int position) {
        selectuser_phonenumber = list.get(position).getPhoneno();
        //Toast.makeText(this, "gnow"+selectuser_phonenumber, Toast.LENGTH_SHORT).show();
        Cursor cursor = new DatabaseHelper(this).readparticulardata(selectuser_phonenumber);
        while(cursor.moveToNext()) {
            selectuser_name = cursor.getString(1);
             selectuser_balance = cursor.getString(2);
           // Toast.makeText(this, "name"+selectuser_name, Toast.LENGTH_SHORT).show();
           // Toast.makeText(this, "name"+selectuser_balance, Toast.LENGTH_SHORT).show();

            Double Dselectuser_balance = Double.parseDouble(selectuser_balance);
            Double Dselectuser_transferamount = Double.parseDouble(transferamount);
            Double Dselectuser_remainingamount = Dselectuser_balance + Dselectuser_transferamount;

     //       Toast.makeText(this, "nRest"+Dselectuser_remainingamount, Toast.LENGTH_SHORT).show();


            new DatabaseHelper(this).insertTransferData(date, name1, selectuser_name, transferamount, "Success");
            new DatabaseHelper(this).updateAmount(selectuser_phonenumber, Dselectuser_remainingamount.toString());
            calculateAmount();
            Toast.makeText(this, "Transaction Successful!", Toast.LENGTH_LONG).show();
           startActivity(new Intent(TransferTo.this, MainActivity.class));
            finish();


        }
    }

    private void calculateAmount() {
        Double Dcurrentamount = Double.parseDouble(currentamount);
        Double Dtransferamount = Double.parseDouble(transferamount);
        Double Dremainingamount = Dcurrentamount - Dtransferamount;
        remainingamount = Dremainingamount.toString();
        new DatabaseHelper(this).updateAmount(phonenumber, remainingamount);
    }



}




