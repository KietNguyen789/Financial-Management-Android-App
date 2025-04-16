

package com.group08.finotes;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.group08.finotes.AppDataStructure.Bill;
//import com.group08.finotes.AppDataStructure.LocalDBModel;
import com.group08.finotes.AppDataStructure.LocalDBModel;
import com.group08.finotes.AppDataStructure.Wallet;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Create_bill extends AppCompatActivity {
    private Button VNDbtn, cancel, save;
    private Spinner cate_Spin, wallet;
    private TextInputEditText money, Description, e_day, e_month, e_year, e_who, e_IR,e_daynoti,e_monthnoti,e_yearnoti;
    private TextView day, month, year, who,IR, daynoti, monthnoti,yearnoti,t1,t2,dateofnoti;

    private LocalDBModel localDBModel;

    private void init() {
        // code
        setContentView(R.layout.activity_create_bill);
        VNDbtn = findViewById(R.id.VND);
        cate_Spin = findViewById(R.id.spinner_cate);
        money = findViewById(R.id.Input_amount_money);
        who = findViewById(R.id.text_who);
        IR = findViewById(R.id.text_IR);
        e_who = findViewById(R.id.edittext_who);
        e_IR = findViewById(R.id.edittext_IR);
        Description = findViewById((R.id.Des));
        cancel = findViewById((R.id.cancel_btn));
        save = findViewById(R.id.save_btn);
        day = findViewById(R.id.txt_day);
        month = findViewById(R.id.txt_month);
        year = findViewById(R.id.txt_year);
        e_day = findViewById(R.id.edittext_day);
        e_month = findViewById(R.id.edittext_month);
        e_year = findViewById(R.id.edittext_year2);
        wallet = findViewById(R.id.spinner_wallet);
        daynoti = findViewById(R.id.txt_daynoti);
        monthnoti = findViewById(R.id.txt_monthnoti);
        yearnoti = findViewById(R.id.txt_yearnoti);
        e_daynoti=findViewById(R.id.edittext_daynoti);
        e_monthnoti=findViewById(R.id.edittext_monthnoti);
        e_yearnoti=findViewById(R.id.edittext_yearnoti);
        t1=findViewById(R.id.textViewnoti1);
        t2=findViewById(R.id.textViewnoti2);
        dateofnoti=findViewById(R.id.textViewdatenoti);
        ArrayList<String> array_cate = new ArrayList<String>();
        ArrayList<String> array_wallet = new ArrayList<String>();
        array_cate.add("none");
        array_cate.add("Food & Drink");
        array_cate.add("Transportation");
        array_cate.add("Water Bill");
        array_cate.add("Phone Bill");
        array_cate.add("Electricity Bill");
        array_cate.add("Gas Bill");
        array_cate.add("Relax Bill");
        array_cate.add("Internet Bill");
        array_cate.add("Other Expenses");
        array_cate.add("Loan");
        array_cate.add("Dept");
        array_cate.add("Income");
        array_cate.add("Vehicle Maintenance");
        ArrayAdapter arrayAdapter_cate = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, array_cate);
        cate_Spin.setAdapter(arrayAdapter_cate);
        array_wallet.add("Default");
        ArrayAdapter arrayAdapter_wallet = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, array_wallet);
        wallet.setAdapter(arrayAdapter_wallet);
        e_who.setVisibility(View.INVISIBLE);
        e_IR.setVisibility(View.INVISIBLE);
        who.setVisibility(View.INVISIBLE);
        IR.setVisibility(View.INVISIBLE);
        daynoti.setVisibility(View.INVISIBLE);
        monthnoti.setVisibility(View.INVISIBLE);
        yearnoti.setVisibility(View.INVISIBLE);
        e_daynoti.setVisibility(View.INVISIBLE);
        e_monthnoti.setVisibility(View.INVISIBLE);
        e_yearnoti.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        dateofnoti.setVisibility(View.INVISIBLE);
        localDBModel = new LocalDBModel(this);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void spin_change() {
        cate_Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                chose_cate();



            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        wallet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    private void US_VNDListener() {
        VNDbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VNDbtn.getText().toString() == "VND") {
                    chose_US();
                } else {
                    chose_VND();
                }

            }
        });
    }
    public boolean checkdate(String s_day,String s_month,String s_year){
        int month =Integer.parseInt(s_month);
        int year =Integer.parseInt(s_year);
        int day =Integer.parseInt(s_day);
        if (month ==2){
        boolean leap = false;

        // if the year is divided by 4
        if (year % 4 == 0) {

            // if the year is century
            if (year % 100 == 0) {

                // if year is divided by 400
                // then it is a leap year
                if (year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            }

            // if the year is not century
            else
                leap = true;
        }

        else
            leap = false;
        if (leap == false){
            if(day>28){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            if(day>29){
                return false;
            }
            else{
                return true;
            }
        }
        }
        else{
            if(month ==1||month ==3||month==5||month==7||month==8||month==10||month==12){
                if(day>31){
                    return false;
                }
                else{
                    return true;
                }
            }
            else{
                if(day>30){
                    return false;

                }
                else{
                    return true;
                }
            }
        }
    }
    private void saveListener() {
        save.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (money.getText().toString().equals("")  || cate_Spin.getSelectedItem().toString().trim().equals("") || e_day.getText().toString().trim().equals("") || e_month.getText().toString().trim().equals("") || e_year.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Not enough Infomation!!!", Toast.LENGTH_LONG).show();

                } else if (cate_Spin.getSelectedItem().toString().equals("Dept") || cate_Spin.getSelectedItem().toString().equals("Loan")) {

                    if (money.getText().toString().equals("") || e_who.getText().toString().equals("") || e_IR.getText().toString().equals("") || e_day.getText().toString().equals("") || e_month.getText().toString().equals("") || e_year.getText().toString().equals("") || e_daynoti.getText().toString().equals("") || e_monthnoti.getText().toString().equals("") || e_yearnoti.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Not enough Infomation!!!", Toast.LENGTH_LONG).show();
                    }else{
                        if(isNumeric(money.getText().toString()) && isNumeric(e_day.getText().toString()) &&isNumeric(e_month.getText().toString()) && isNumeric(e_year.getText().toString())&&isNumeric(e_IR.getText().toString())&&isNumeric(e_daynoti.getText().toString())&&isNumeric(e_monthnoti.getText().toString())&&isNumeric(e_yearnoti.getText().toString())){
                            if(Float.parseFloat(money.getText().toString()) >=0&&Float.parseFloat(e_day.getText().toString()) >0&&Float.parseFloat(e_month.getText().toString()) >0&&Float.parseFloat(e_year.getText().toString()) >0&&Float.parseFloat(e_IR.getText().toString()) >0&&Float.parseFloat(e_daynoti.getText().toString())>0&&Float.parseFloat(e_monthnoti.getText().toString()) >0 &&Float.parseFloat(e_yearnoti.getText().toString()) >0){
                                if (checkdate(e_day.getText().toString(),e_month.getText().toString(),e_year.getText().toString())&&checkdate(e_daynoti.getText().toString(),e_monthnoti.getText().toString(),e_yearnoti.getText().toString())){
                                    Boolean flag = localDBModel.addBill(save());
                                    if(flag == true) {
                                        Toast.makeText(getApplicationContext(), "save successfully!", Toast.LENGTH_LONG).show();
                                    }
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Incorrect Infomation!!!", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Incorrect Infomation!!!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Incorrect Infomation!!!", Toast.LENGTH_LONG).show();
                        }

                    }

                } else {
                    if(isNumeric(money.getText().toString()) && isNumeric(e_day.getText().toString()) &&isNumeric(e_month.getText().toString()) && isNumeric(e_year.getText().toString())){
                        if(Float.parseFloat(money.getText().toString()) >=0&&Float.parseFloat(e_day.getText().toString()) >0&&Float.parseFloat(e_month.getText().toString()) >0&&Float.parseFloat(e_year.getText().toString()) >0){
                            if (checkdate(e_day.getText().toString(),e_month.getText().toString(),e_year.getText().toString())){
                                Boolean flag = localDBModel.addBill(save());
                                if (flag == true) {
                                    Toast.makeText(getApplicationContext(), "save successfully!", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Incorrect Infomation!!!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Incorrect Infomation!!!", Toast.LENGTH_LONG).show();
                         }
                    }
                    else {
                    Toast.makeText(getApplicationContext(), "Incorrect Infomation!!!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bill);
        init();
        US_VNDListener();
        saveListener();
        spin_change();

    }

    public String chose_cate() {

        if (cate_Spin.getSelectedItem().toString().equals("Dept") || cate_Spin.getSelectedItem().toString().equals("Loan")) {


            e_who.setVisibility(View.VISIBLE);
            e_IR.setVisibility(View.VISIBLE);
            who.setVisibility(View.VISIBLE);
            IR.setVisibility(View.VISIBLE);
            daynoti.setVisibility(View.VISIBLE);
            monthnoti.setVisibility(View.VISIBLE);
            yearnoti.setVisibility(View.VISIBLE);
            e_daynoti.setVisibility(View.VISIBLE);
            e_monthnoti.setVisibility(View.VISIBLE);
            e_yearnoti.setVisibility(View.VISIBLE);
            t1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.VISIBLE);
            dateofnoti.setVisibility(View.VISIBLE);
        } else {

            who.setVisibility(View.INVISIBLE);
            IR.setVisibility(View.INVISIBLE);
            e_who.setVisibility(View.INVISIBLE);
            e_IR.setVisibility(View.INVISIBLE);
            daynoti.setVisibility(View.INVISIBLE);
            monthnoti.setVisibility(View.INVISIBLE);
            yearnoti.setVisibility(View.INVISIBLE);
            e_daynoti.setVisibility(View.INVISIBLE);
            e_monthnoti.setVisibility(View.INVISIBLE);
            e_yearnoti.setVisibility(View.INVISIBLE);
            t1.setVisibility(View.INVISIBLE);
            t2.setVisibility(View.INVISIBLE);
            dateofnoti.setVisibility(View.INVISIBLE);
        }

        return cate_Spin.getSelectedItem().toString();
    }


    public void chose_US() {
        VNDbtn.setText("US");

    }

    public void chose_VND() {
        VNDbtn.setText("VND");

    }

    public Date ConverttoDate(String A) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Log.println(010, "Date", A);
        Date date = null;
        try {
            date = format.parse(A);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public Date get_current_date() {
        Date c = Calendar.getInstance().getTime();
        return c;
    }
    public Bill save() {


        if(cate_Spin.getSelectedItem().toString().equals("Dept")||cate_Spin.getSelectedItem().toString().equals("Loan")) {
            Bill bill = new Bill(Description.getText().toString(),e_who.getText().toString(),cate_Spin.getSelectedItem().toString(),ConverttoDate(e_daynoti.getText().toString().trim()+"-"+e_monthnoti.getText().toString().trim()+"-"+e_yearnoti.getText().toString().trim()) ,new Wallet(10000,"VND","Default"),ConverttoDate(e_day.getText().toString().trim()+"-"+e_month.getText().toString().trim()+"-"+e_year.getText().toString().trim()),Float.parseFloat(e_IR.getText().toString()),Integer.parseInt(String.valueOf(money.getText())));
            return bill;
        }
        else{
            Bill bill = new Bill(Description.getText().toString(),cate_Spin.getSelectedItem().toString(),ConverttoDate(e_day.getText().toString().trim()+"-"+e_month.getText().toString().trim()+"-"+e_year.getText().toString().trim()),Integer.parseInt(String.valueOf(money.getText())),new Wallet(10000,"VND","Default"));
            return bill;
        }


    }
}