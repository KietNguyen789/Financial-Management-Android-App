package com.group08.finotes.AppDataStructure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalDBModel extends SQLiteOpenHelper {
    public static final String BILL_TABLE = "BILL_TABLE";
    public static final String COLUMN_BILL_ID = "BILL_ID";
    public static final String COLUMN_AMOUNT_OF_MONEY = "AMOUNT_OF_MONEY";

    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    public static final String COLUMN_CREATION_DATE = "CREATION_DATE";
    public static final String COLUMN_ASSOCIATED_PERSON = "ASSOCIATED_PERSON";
    public static final String COLUMN_DATE_OF_NOTIFICATION = "DATE_OF_NOTIFICATION";
    public static final String COLUMN_WALLET_SOURCE = "WALLET_SOURCE";
    public static final String COLUMN_INTEREST_RATE = "INTEREST_RATE";
    public static final String WALLET_TABLE = "WALLET_TABLE";
    public static final String COLUMN_WALLET_NAME = "WALLET_NAME";
    public static final String COLUMN_CURRENCY = "CURRENCY";
    public static final String COLUMN_LEFT_OVER = "LEFT_OVER";

    public LocalDBModel(@Nullable Context context) {
        super(context, "local.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createBillTableStatement = "CREATE TABLE " + BILL_TABLE + " (" + COLUMN_BILL_ID + " INTEGER PRIMARY KEY, " + COLUMN_AMOUNT_OF_MONEY + " INTEGER, " + COLUMN_DESCRIPTION
                +" TEXT, " + COLUMN_CATEGORY + " TEXT, " + COLUMN_CREATION_DATE + " TEXT," + COLUMN_INTEREST_RATE + " FLOAT, " + COLUMN_ASSOCIATED_PERSON + " TEXT, " + COLUMN_DATE_OF_NOTIFICATION + " TEXT, " + COLUMN_WALLET_SOURCE + " TEXT)";
        String createWalletTableStatement = "CREATE TABLE " + WALLET_TABLE + " (" + COLUMN_WALLET_NAME + " TEXT PRIMARY KEY, " + COLUMN_CURRENCY + " TEXT, " + COLUMN_LEFT_OVER + " INTEGER)";
        db.execSQL(createBillTableStatement);
        db.execSQL(createWalletTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addBill(Bill bill){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean nullFlag = false;
        cv.put(COLUMN_BILL_ID,bill.getBillID());
        cv.put(COLUMN_AMOUNT_OF_MONEY,bill.getAmountOfMoney());
        cv.put(COLUMN_DESCRIPTION,bill.getDescription());
        cv.put(COLUMN_CATEGORY,bill.getCategory());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        cv.put(COLUMN_CREATION_DATE,dateFormat.format(bill.getDateOfCreation()));
        cv.put(COLUMN_ASSOCIATED_PERSON, bill.getPersonID());
        cv.put(COLUMN_INTEREST_RATE, bill.getInterestRate());
        if(bill.getDateOfNotification() != null) {
            cv.put(COLUMN_DATE_OF_NOTIFICATION, dateFormat.format(bill.getDateOfNotification()));
        }else{
            nullFlag = true;
        }
        cv.put(COLUMN_WALLET_SOURCE,bill.getWalletID());
        long flag = -1;
        if(nullFlag == false) {
            flag = db.insert(BILL_TABLE, null, cv);
        }else{
            flag = db.insert(BILL_TABLE, COLUMN_DATE_OF_NOTIFICATION, cv);
        }
        db.close();
        if(flag == -1){
            return false;
        }
        return true;
    }


    public List<Bill> getAllList() throws ParseException {
        List<Bill> returnList = new ArrayList<>();
        String queryCom = "SELECT * FROM " + BILL_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryCom,null);
        if(cursor.moveToFirst()){
            // If there are data (bills) this cursor will loop through the array result;
            do {
                String billID = cursor.getString(0);
                int amountOfMoney = cursor.getInt(1);
                String description = cursor.getString(2);
                String category = cursor.getString(3);
                DateFormat templateDate = new SimpleDateFormat("dd-MM-yyyy");
                Date creationDate = templateDate.parse(cursor.getString(4));
                float interestRate = cursor.getFloat(5);
                String personID = cursor.getString(6);
                Date notificationDate = templateDate.parse(cursor.getString(7));
                Wallet wallet = new Wallet(99999,"USD",cursor.getString(8));
                Bill temp = new Bill(billID,description,personID,category,wallet,notificationDate,creationDate,interestRate,amountOfMoney);
                returnList.add(temp);
            }while (cursor.moveToNext());
        }else{
            // failure read;
        }
        cursor.close();
        db.close();
        return returnList;
    }


}
