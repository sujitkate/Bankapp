package com.example.bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{

     private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Us.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9569568495,'Sujit',100.00,'sujit59@gmail.com','XXXXXXX8659','SBI00KL2031')");
        db.execSQL("insert into user_table values(8979260135,'Tejas',200.00,'ty@gmail.com','XXXXXXX8362','SBI00KL2031')");
        db.execSQL("insert into user_table values(9879213065,'Suyog',365.00,'suyog@gmail.com','XXXXX6359','SBI00KL2031')");

        db.execSQL("insert into user_table values(8796859456,'Girish',235.00,'giri@gmail.com','XXXXXX8623','SBI00KL2032')");
        db.execSQL("insert into user_table values(8897598569,'Shaharukh',523.00,'srk32@gmail.com','XXXXX2653','SBI00KL2032')");
        db.execSQL("insert into user_table values(7789586826,'Prashant',235.00,'prash@gmail.com','XXXXX4586','SBI00KL2031')");
        db.execSQL("insert into user_table values(8975986585,'Saurabh',485.00,'sourabh12@gmail.com','XXXXXX836','SBI00KL2032')");
        db.execSQL("insert into user_table values(986973246,'Akhil',100.00,'akhil23@gmail.com','XXXXXXX8785','SBI00KL2031')");

        db.execSQL("insert into user_table values(894658243,'Panini',200.00,'panini132@gmail.com','XXXXXX8626','SBI00KL2031')");
        db.execSQL("insert into user_table values(8797598652,'Aniket',205.00,'ani96@gmail.com','XXXXXXX8265','SBI00KL2031')");
        db.execSQL("insert into user_table values(9698695980,'Atharv',205.00,'atharv63@gmail.com','XXXXXX8265','SBI00KL2031')");
 }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }


    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

}
