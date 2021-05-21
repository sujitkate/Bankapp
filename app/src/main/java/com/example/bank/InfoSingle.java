package com.example.bank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InfoSingle extends AppCompatActivity {
    TextView name, mobile, mail, account, code, bal;
    Double newbalance;
    Button btn;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_single);
        name = findViewById(R.id.username);
        mobile = findViewById(R.id.userphonenumber);
        mail = findViewById(R.id.email);
        account = findViewById(R.id.account_no);
        code = findViewById(R.id.ifsc_code);
        bal = findViewById(R.id.balance);

        btn = findViewById(R.id.transfer_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
sendAmount();
            }
        });
        String a = getIntent().getStringExtra("pos");

        String aa = a + "";
        showData(a);

    }


    private void showData(String aa) {
        //String sa=a+"";
        Cursor cursor = new DatabaseHelper(this).readparticulardata(aa);
        while (cursor.moveToNext()) {
            String balancefromdb = cursor.getString(2);
            newbalance = Double.parseDouble(balancefromdb);
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(newbalance);
            mobile.setText(cursor.getString(0));
            name.setText(cursor.getString(1));
            mail.setText(cursor.getString(3));
            bal.setText(price);
            account.setText(cursor.getString(4));
            code.setText(cursor.getString(5));
        }
    }


    private void sendAmount()
    {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(InfoSingle.this);
        View mView = getLayoutInflater().inflate(R.layout.dialongtransfermoney, null);
        mBuilder.setTitle("Enter amount").setView(mView).setCancelable(false);

        final EditText mAmount = (EditText) mView.findViewById(R.id.enter_money);

        mBuilder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                transactionCancel();
            }
        });

        dialog = mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAmount.getText().toString().isEmpty()) {
                    mAmount.setError("Amount can't be empty");
                } else if (Double.parseDouble(mAmount.getText().toString()) > newbalance) {
                    mAmount.setError("Your account don't have enough balance");
                } else {
                    Intent intent = new Intent(InfoSingle.this, TransferTo.class);
                    intent.putExtra("phonenumber", mobile.getText().toString());
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("currentamount", newbalance.toString());
                    intent.putExtra("transferamount", mAmount.getText().toString());
                    startActivity(intent);
                    finish();
                }

           }
        });

    }

    private void transactionCancel()
    {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(InfoSingle.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
                        String date = simpleDateFormat.format(calendar.getTime());

                        new DatabaseHelper(InfoSingle.this).insertTransferData(date, name.getText().toString(), "Cancel by User", "0", "Failed");
                        Toast.makeText(InfoSingle.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                sendAmount();
            }
        });
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();

    }
}


