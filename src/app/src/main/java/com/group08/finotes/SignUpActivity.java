package com.group08.finotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.group08.finotes.AppDataStructure.Address;
import com.group08.finotes.AppDataStructure.CloudDBModel;
import com.group08.finotes.AppDataStructure.User;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editTextEmail, editTextName;
    private EditText editTextPassword;
    private Button button;
    private ProgressDialog progressDialog;
    Spinner spinnerTP;
    Spinner spinnerHuyen;
    Spinner spinnerXa;
    ArrayAdapter<String> arrayAdapterHuyen,arrayAdapterXa;

    CloudDBModel myCloudDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
        // Initialize views from the XML layout
        spinnerHuyen.setEnabled(false);
        spinnerHuyen.setSelection(0);
        spinnerXa.setEnabled(false);
        spinnerXa.setSelection(0);
        ArrayList<String> arrayTP=new ArrayList<String>();
        arrayTP.add("None");
        arrayTP.add("Hồ Chí Minh");
        arrayTP.add("Đà Nẵng");
        arrayTP.add("An Giang");
        arrayTP.add("Bà Rịa - Vũng Tàu");
        arrayTP.add("Bắc Giang");
        arrayTP.add("Bắc Kạn");
        arrayTP.add("Bạc Liêu");
        arrayTP.add("Bắc Ninh");
        arrayTP.add("Bến Tre");
        arrayTP.add("Bình Định");
        arrayTP.add("Bình Dương");
        arrayTP.add("Bình Phước");
        arrayTP.add("Bình Thuận");
        arrayTP.add("Cà Mau");
        arrayTP.add("Cao Bằng");
        arrayTP.add("Đắk Lắk");
        arrayTP.add("Đắk Nông");
        arrayTP.add("Điện Biên");
        arrayTP.add("Đồng Nai");
        arrayTP.add("Đồng Tháp");
        arrayTP.add("Gia Lai");
        arrayTP.add("Hà Giang");
        arrayTP.add("Hà Nam");
        arrayTP.add("Hà Tĩnh");
        arrayTP.add("Hà Nội");
        arrayTP.add("Hải Dương");
        arrayTP.add("Hải Phòng");
        arrayTP.add("Hậu Giang");
        arrayTP.add("Hồ Chí Minh");
        arrayTP.add("Hòa Bình");
        arrayTP.add("Hưng Yên");
        arrayTP.add("Khánh Hòa");
        arrayTP.add("Kiên Giang");
        arrayTP.add("Kon Tum");
        arrayTP.add("Lai Châu");
        arrayTP.add("Lâm Đồng");
        arrayTP.add("Lạng Sơn");
        arrayTP.add("Lào Cai");
        arrayTP.add("Long An");
        arrayTP.add("Nam Định");
        arrayTP.add("Nghệ An");
        arrayTP.add("Ninh Bình");
        arrayTP.add("Ninh Thuận");
        arrayTP.add("Phú Thọ");
        arrayTP.add("Phú Yên");
        arrayTP.add("Quảng Bình");
        arrayTP.add("Quảng Nam");
        arrayTP.add("Quảng Ngãi");
        arrayTP.add("Quảng Ninh");
        arrayTP.add("Quảng Trị");
        arrayTP.add("Sóc Trăng");
        arrayTP.add("Sơn La");
        arrayTP.add("Tây Ninh");
        arrayTP.add("Thái Bình");
        arrayTP.add("Thái Nguyên");
        arrayTP.add("Thanh Hóa");
        arrayTP.add("Thừa Thiên Huế");
        arrayTP.add("Tiền Giang");
        arrayTP.add("Trà Vinh");
        arrayTP.add("Tuyên Quang");
        arrayTP.add("Vĩnh Long");
        arrayTP.add("Vĩnh Phúc");
        arrayTP.add("Yên Bái");
        ArrayAdapter arrayAdapterTP=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayTP);
        spinnerTP.setAdapter(arrayAdapterTP);
        ArrayList<String> arrayHCM,arrayNotHCM;
        //Start Huyen
        arrayHCM = new ArrayList<String>();
        arrayHCM.add("None");
        arrayHCM.add("Quận 1");
        arrayHCM.add("Quận 3");
        arrayHCM.add("Quận 4");
        arrayHCM.add("Quận 5");
        arrayHCM.add("Quận 6");
        arrayHCM.add("Quận 7");
        arrayHCM.add("Quận 8");
        arrayHCM.add("Quận 10");
        arrayHCM.add("Quận 11");
        arrayHCM.add("Quận 12");
        arrayHCM.add("Quận Bình Tân");
        arrayHCM.add("Quận Bình Thạnh");
        arrayHCM.add("Quận Gò Vấp");
        arrayHCM.add("Quận Phú Nhuận");
        arrayHCM.add("Quận Tân Bình");
        arrayHCM.add("Quận Tân Phú");
        arrayHCM.add("Huyện Bình Chánh");
        arrayHCM.add("Huyện Cần Giờ");
        arrayHCM.add("Huyện Củ Chi");
        arrayHCM.add("Huyện Hóc Môn");
        arrayHCM.add("Huyện Nhà Bè");
        arrayHCM.add("Thành phố Thủ Đức");

        arrayNotHCM=new ArrayList<String>();
        arrayNotHCM.add("None");

        spinnerTP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==1)
                {
                    arrayAdapterHuyen=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayHCM);
                }
                if (i!=1)
                {
                    arrayAdapterHuyen=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayNotHCM);

                }
                spinnerHuyen.setAdapter(arrayAdapterHuyen);
                String selectedTP = (String) spinnerTP.getItemAtPosition(i);

                if (selectedTP.equals("None")) {
                    // Nếu người dùng chọn None, thì tắt Spinner thứ hai và Spinner thứ ba
                    spinnerHuyen.setEnabled(false);
                    spinnerHuyen.setSelection(0); // Đặt giá trị None làm giá trị mặc định
                    spinnerXa.setEnabled(false);
                    spinnerXa.setSelection(0); // Đặt giá trị None làm giá trị mặc định
                } else {
                    // Nếu người dùng chọn một giá trị khác None, thì mở khóa Spinner thứ hai và Spinner thứ ba
                    spinnerHuyen.setEnabled(true);
                    spinnerHuyen.setSelection(0); // Đặt giá trị None làm giá trị mặc định
                    spinnerXa.setEnabled(false);
                    spinnerXa.setSelection(0); // Đặt giá trị None làm giá trị mặc định
                }
            }
            //End Huyen
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                arrayAdapterHuyen=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayNotHCM);
                spinnerHuyen.setAdapter(arrayAdapterHuyen);
            }
        });
        ArrayList<String> arrayQ1,arrayQ3,arrayQ4,arrayQ5,arrayQ6,arrayQ7,arrayQ8,arrayQ10,arrayQ11,arrayQ12;
        ArrayList<String> arrayBTan,arrayBThanh,arrayGV,arrayPN,arrayTB,arrayTPhu,arrayBC,arrayCG,arrayCC,arrayHM,arrayNB,arrayTD;

        //Start Phuong
        arrayQ1=new ArrayList<String>();
        arrayQ1.add("None");
        arrayQ1.add("Bến Nghé");
        arrayQ1.add("Cô Giang");
        arrayQ1.add("Bến Thành");
        arrayQ1.add("Cầu Kho");
        arrayQ1.add("Đa Kao");
        arrayQ1.add("Cầu Ông Lãnh");
        arrayQ1.add("Nguyễn Thái Bình");
        arrayQ1.add("Nguyễn Cư Trinh");
        arrayQ1.add("Phạm Ngũ Lão");
        arrayQ1.add("Tân Định");

        arrayQ3=new ArrayList<String>();
        arrayQ3.add("Phường 1");
        arrayQ3.add("Phường 2");
        arrayQ3.add("Phường 3");
        arrayQ3.add("Phường 4");
        arrayQ3.add("Phường 5");
        arrayQ3.add("Phường 6");
        arrayQ3.add("Phường 7");
        arrayQ3.add("Phường 8");
        arrayQ3.add("Phường 9");
        arrayQ3.add("Phường 10");
        arrayQ3.add("Phường 11");
        arrayQ3.add("Phường 12");
        arrayQ3.add("Phường 13");
        arrayQ3.add("Phường 14");

        arrayQ4=new ArrayList<String>();
        arrayQ4.add("Phường 1");
        arrayQ4.add("Phường 2");
        arrayQ4.add("Phường 3");
        arrayQ4.add("Phường 4");
        arrayQ4.add("Phường 6");
        arrayQ4.add("Phường 8");
        arrayQ4.add("Phường 9");
        arrayQ4.add("Phường 10");
        arrayQ4.add("Phường 13");
        arrayQ4.add("Phường 14");
        arrayQ4.add("Phường 15");
        arrayQ4.add("Phường 16");
        arrayQ4.add("Phường 18");

        arrayQ5=new ArrayList<String>();
        arrayQ5.add("Phường 1");
        arrayQ5.add("Phường 2");
        arrayQ5.add("Phường 3");
        arrayQ5.add("Phường 4");
        arrayQ5.add("Phường 5");
        arrayQ5.add("Phường 6");
        arrayQ5.add("Phường 7");
        arrayQ5.add("Phường 8");
        arrayQ5.add("Phường 9");
        arrayQ5.add("Phường 10");
        arrayQ5.add("Phường 11");
        arrayQ5.add("Phường 12");
        arrayQ5.add("Phường 13");
        arrayQ5.add("Phường 14");
        arrayQ5.add("Phường 15");

        arrayQ6=new ArrayList<String>();
        arrayQ6.add("Phường 1");
        arrayQ6.add("Phường 2");
        arrayQ6.add("Phường 3");
        arrayQ6.add("Phường 4");
        arrayQ6.add("Phường 5");
        arrayQ6.add("Phường 6");
        arrayQ6.add("Phường 7");
        arrayQ6.add("Phường 8");
        arrayQ6.add("Phường 9");
        arrayQ6.add("Phường 10");
        arrayQ6.add("Phường 11");
        arrayQ6.add("Phường 12");
        arrayQ6.add("Phường 13");
        arrayQ6.add("Phường 14");

        arrayQ7=new ArrayList<String>();
        arrayQ7.add("Bình Thuận");
        arrayQ7.add("Phú Mỹ");
        arrayQ7.add("Phú Thuận");
        arrayQ7.add("Tân Hưng");
        arrayQ7.add("Tân Kiểng");
        arrayQ7.add("Tân Phong");
        arrayQ7.add("Tân Phú");
        arrayQ7.add("Tân Quy");
        arrayQ7.add("Tân Thuận Đông");
        arrayQ7.add("Tân Thuận Tây");

        arrayQ8=new ArrayList<String>();
        arrayQ8.add("Phường 1");
        arrayQ8.add("Phường 2");
        arrayQ8.add("Phường 3");
        arrayQ8.add("Phường 4");
        arrayQ8.add("Phường 5");
        arrayQ8.add("Phường 6");
        arrayQ8.add("Phường 7");
        arrayQ8.add("Phường 8");
        arrayQ8.add("Phường 9");
        arrayQ8.add("Phường 10");
        arrayQ8.add("Phường 11");
        arrayQ8.add("Phường 12");
        arrayQ8.add("Phường 13");
        arrayQ8.add("Phường 14");
        arrayQ8.add("Phường 15");
        arrayQ8.add("Phường 16");

        arrayQ10=new ArrayList<String>();
        arrayQ10.add("Phường 1");
        arrayQ10.add("Phường 2");
        arrayQ10.add("Phường 3");
        arrayQ10.add("Phường 4");
        arrayQ10.add("Phường 5");
        arrayQ10.add("Phường 6");
        arrayQ10.add("Phường 7");
        arrayQ10.add("Phường 8");
        arrayQ10.add("Phường 9");
        arrayQ10.add("Phường 10");
        arrayQ10.add("Phường 11");
        arrayQ10.add("Phường 12");
        arrayQ10.add("Phường 13");
        arrayQ10.add("Phường 14");
        arrayQ10.add("Phường 15");

        arrayQ11=new ArrayList<String>();
        arrayQ11.add("Phường 1");
        arrayQ11.add("Phường 2");
        arrayQ11.add("Phường 3");
        arrayQ11.add("Phường 4");
        arrayQ11.add("Phường 5");
        arrayQ11.add("Phường 6");
        arrayQ11.add("Phường 7");
        arrayQ11.add("Phường 8");
        arrayQ11.add("Phường 9");
        arrayQ11.add("Phường 10");
        arrayQ11.add("Phường 11");
        arrayQ11.add("Phường 12");
        arrayQ11.add("Phường 13");
        arrayQ11.add("Phường 14");
        arrayQ11.add("Phường 15");
        arrayQ11.add("Phường 16");

        arrayQ12 = new ArrayList<String>();
        arrayQ12.add("An Phú Đông");
        arrayQ12.add("Đông Hưng Thuận");
        arrayQ12.add("Hiệp Thành");
        arrayQ12.add("Tân Chánh Hiệp");
        arrayQ12.add("Tân Hưng Thuận");
        arrayQ12.add("Tân Thới Hiệp");
        arrayQ12.add("Tân Thới Nhất");
        arrayQ12.add("Thạnh Lộc");
        arrayQ12.add("Thạnh Xuân");
        arrayQ12.add("Thới An");
        arrayQ12.add("Trung Mỹ Tây");

        arrayBTan = new ArrayList<String>();

        arrayBTan.add("An Lạc");
        arrayBTan.add("An Lạc A");
        arrayBTan.add("Bình Hưng Hòa");
        arrayBTan.add("Bình Hưng Hòa A");
        arrayBTan.add("Bình Hưng Hòa B");
        arrayBTan.add("Bình Trị Đông");
        arrayBTan.add("Bình Trị Đông A");
        arrayBTan.add("Bình Trị Đông B");
        arrayBTan.add("Tân Tạo");
        arrayBTan.add("Tân Tạo A");

        arrayBThanh=new ArrayList<String>();
        arrayBThanh.add("Phường 1");
        arrayBThanh.add("Phường 2");
        arrayBThanh.add("Phường 3");
        arrayBThanh.add("Phường 5");
        arrayBThanh.add("Phường 6");
        arrayBThanh.add("Phường 7");
        arrayBThanh.add("Phường 11");
        arrayBThanh.add("Phường 12");
        arrayBThanh.add("Phường 13");
        arrayBThanh.add("Phường 14");
        arrayBThanh.add("Phường 15");
        arrayBThanh.add("Phường 17");
        arrayBThanh.add("Phường 19");
        arrayBThanh.add("Phường 21");
        arrayBThanh.add("Phường 22");
        arrayBThanh.add("Phường 24");
        arrayBThanh.add("Phường 25");
        arrayBThanh.add("Phường 26");
        arrayBThanh.add("Phường 27");
        arrayBThanh.add("Phường 28");

        arrayGV=new ArrayList<String>();
        arrayGV.add("Phường 1");
        arrayGV.add("Phường 3");
        arrayGV.add("Phường 4");
        arrayGV.add("Phường 5");
        arrayGV.add("Phường 6");
        arrayGV.add("Phường 7");
        arrayGV.add("Phường 8");
        arrayGV.add("Phường 9");
        arrayGV.add("Phường 10");
        arrayGV.add("Phường 11");
        arrayGV.add("Phường 12");
        arrayGV.add("Phường 13");
        arrayGV.add("Phường 14");
        arrayGV.add("Phường 15");
        arrayGV.add("Phường 16");
        arrayGV.add("Phường 17");

        arrayPN=new ArrayList<String>();
        arrayPN.add("Phường 1");
        arrayPN.add("Phường 2");
        arrayPN.add("Phường 3");
        arrayPN.add("Phường 4");
        arrayPN.add("Phường 5");
        arrayPN.add("Phường 7");
        arrayPN.add("Phường 8");
        arrayPN.add("Phường 9");
        arrayPN.add("Phường 10");
        arrayPN.add("Phường 11");
        arrayPN.add("Phường 13");
        arrayPN.add("Phường 15");
        arrayPN.add("Phường 17");

        arrayTB=new ArrayList<String>();
        arrayTB.add("Phường 1");
        arrayTB.add("Phường 2");
        arrayTB.add("Phường 3");
        arrayTB.add("Phường 4");
        arrayTB.add("Phường 5");
        arrayTB.add("Phường 6");
        arrayTB.add("Phường 7");
        arrayTB.add("Phường 8");
        arrayTB.add("Phường 9");
        arrayTB.add("Phường 10");
        arrayTB.add("Phường 11");
        arrayTB.add("Phường 12");
        arrayTB.add("Phường 13");
        arrayTB.add("Phường 14");
        arrayTB.add("Phường 15");

        arrayTPhu = new ArrayList<String>();

        arrayTPhu.add("Phường Hiệp Tân");
        arrayTPhu.add("Phường Hòa Thạnh");
        arrayTPhu.add("Phường Phú Thạnh");
        arrayTPhu.add("Phường Phú Thọ Hòa");
        arrayTPhu.add("Phường Phú Trung");
        arrayTPhu.add("Phường Sơn Kỳ");
        arrayTPhu.add("Phường Tân Quý");
        arrayTPhu.add("Phường Tân Sơn Nhì");
        arrayTPhu.add("Phường Tân Thành");
        arrayTPhu.add("Phường Tân Thới Hòa");
        arrayTPhu.add("Phường Tây Thạnh");

        arrayBC = new ArrayList<String>();
        arrayBC.add("Thị trấn Tân Túc");
        arrayBC.add("Xã An Phú Tây");
        arrayBC.add("Xã Bình Chánh");
        arrayBC.add("Xã Bình Hưng");
        arrayBC.add("Xã Bình Lợi");
        arrayBC.add("Xã Đa Phước");
        arrayBC.add("Xã Hưng Long");
        arrayBC.add("Xã Lê Minh Xuân");
        arrayBC.add("Xã Phạm Văn Hai");
        arrayBC.add("Xã Phong Phú");
        arrayBC.add("Xã Qui Đức");
        arrayBC.add("Xã Tân Kiên");
        arrayBC.add("Xã Tân Nhựt");
        arrayBC.add("Xã Tân Quý Tây");
        arrayBC.add("Xã Vĩnh Lộc A");
        arrayBC.add("Xã Vĩnh Lộc B");

        arrayCG = new ArrayList<String>();
        arrayCG.add("Thị trấn Cần Thạnh");
        arrayCG.add("Xã Bình Khánh");
        arrayCG.add("Xã Tam Thôn Hiệp");
        arrayCG.add("Xã An Thới Đông");
        arrayCG.add("Xã Thạnh An");
        arrayCG.add("Xã Long Hòa");

        arrayCC = new ArrayList<String>();
        arrayCC.add("Thị trấn Củ Chi");
        arrayCC.add("Xã Phú Mỹ Hưng");
        arrayCC.add("Xã An Phú");
        arrayCC.add("Xã Trung Lập Thượng");
        arrayCC.add("Xã An Nhơn Tây");
        arrayCC.add("Xã Nhuận Đức");
        arrayCC.add("Xã Phạm Văn Cội");
        arrayCC.add("Xã Phú Hòa Đông");
        arrayCC.add("Xã Trung Lập Hạ");
        arrayCC.add("Xã Trung An");
        arrayCC.add("Xã Phước Thạnh");
        arrayCC.add("Xã Phước Hiệp");
        arrayCC.add("Xã Tân An Hội");
        arrayCC.add("Xã Phước Vĩnh An");
        arrayCC.add("Xã Thái Mỹ");
        arrayCC.add("Xã Tân Thạnh Tây");
        arrayCC.add("Xã Hòa Phú");
        arrayCC.add("Xã Tân Thạnh Đông");
        arrayCC.add("Xã Bình Mỹ");
        arrayCC.add("Xã Tân Phú Trung");
        arrayCC.add("Xã Tân Thông Hội");

        arrayHM = new ArrayList<String>();
        arrayHM.add("Thị trấn Hóc Môn");
        arrayHM.add("Xã Tân Hiệp");
        arrayHM.add("Xã Nhị Bình");
        arrayHM.add("Xã Đông Thạnh");
        arrayHM.add("Xã Tân Thới Nhì");
        arrayHM.add("Xã Thới Tam Thôn");
        arrayHM.add("Xã Xuân Thới Sơn");
        arrayHM.add("Xã Tân Xuân");
        arrayHM.add("Xã Xuân Thới Đông");
        arrayHM.add("Xã Trung Chánh");
        arrayHM.add("Xã Xuân Thới Thượng");
        arrayHM.add("Xã Bà Điểm");

        arrayNB = new ArrayList<String>();
        arrayNB.add("Thị trấn Nhà Bè");
        arrayNB.add("Xã Phước Kiển");
        arrayNB.add("Xã Phước Lộc");
        arrayNB.add("Xã Nhơn Đức");
        arrayNB.add("Xã Phú Xuân");
        arrayNB.add("Xã Long Thới");
        arrayNB.add("Xã Hiệp Phước");

        arrayTD = new ArrayList<String>();
        arrayTD.add("An Khánh");
        arrayTD.add("An Lợi Đông");
        arrayTD.add("An Phú");
        arrayTD.add("Bình Chiểu");
        arrayTD.add("Bình Thọ");
        arrayTD.add("Bình Trưng Đông");
        arrayTD.add("Bình Trưng Tây");
        arrayTD.add("Cát Lái");
        arrayTD.add("Hiệp Bình Chánh");
        arrayTD.add("Hiệp Bình Phước");
        arrayTD.add("Hiệp Phú");
        arrayTD.add("Linh Chiểu");
        arrayTD.add("Linh Đông");
        arrayTD.add("Linh Tây");
        arrayTD.add("Linh Trung");
        arrayTD.add("Linh Xuân");
        arrayTD.add("Long Bình");
        arrayTD.add("Long Phước");
        arrayTD.add("Long Thạnh Mỹ");
        arrayTD.add("Long Trường");
        arrayTD.add("Phú Hữu");
        arrayTD.add("Phước Bình");
        arrayTD.add("Phước Long A");
        arrayTD.add("Phước Long B");
        arrayTD.add("Tam Bình");
        arrayTD.add("Tam Phú");
        arrayTD.add("Tăng Nhơn Phú A");
        arrayTD.add("Tăng Nhơn Phú B");
        arrayTD.add("Tân Phú");
        arrayTD.add("Thảo Điền");
        arrayTD.add("Thạnh Mỹ Lợi");
        arrayTD.add("Thủ Thiêm");
        arrayTD.add("Trường Thạnh");
        arrayTD.add("Trường Thọ");
        spinnerHuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i==1)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ1);
                }
                if (i==2)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ3);

                }
                if (i==3)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ4);
                }
                if (i==4)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ5);

                }
                if (i==5)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ6);
                }
                if (i==6)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ7);

                }
                if (i==7)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ8);
                }
                if (i==8)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ10);

                }
                if (i==9)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ11);
                }
                if (i==10)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayQ12);

                }
                if (i==11)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayBTan);
                }
                if (i==12)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayBThanh);

                }
                if (i==13)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayGV);
                }
                if (i==14)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayPN);

                }
                if (i==15)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayTB);
                }
                if (i==16)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayTPhu);

                }
                if (i==17)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayBC);
                }
                if (i==18)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayCG);

                }
                if (i==19)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayCC);
                }
                if (i==20)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayHM);

                }
                if (i==21)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayNB);
                }
                if (i==22)
                {
                    arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayTD);

                }
                spinnerXa.setAdapter(arrayAdapterXa);
                String selectedHuyen = (String) spinnerHuyen.getItemAtPosition(i);

                if (selectedHuyen.equals("None")) {
                    // Nếu người dùng chọn None, thì tắt Spinner thứ ba
                    spinnerXa.setEnabled(false);
                    spinnerXa.setSelection(0); // Đặt giá trị None làm giá trị mặc định
                } else {
                    // Nếu người dùng chọn một giá trị khác None, thì mở khóa Spinner thứ ba
                    spinnerXa.setEnabled(true);
                    spinnerXa.setSelection(0); // Đặt giá trị None làm giá trị mặc định
                }
            }
            //End Huyen

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                arrayAdapterXa=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayNotHCM);
                spinnerXa.setAdapter(arrayAdapterXa);
            }
        });

        // Set a click listener for the button (example usage)
        button.setOnClickListener(view -> {
            // Perform some action when the button is clicked
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            int last10StartIndex = Math.max(email.length() - 10, 0);
            String last10Characters = email.substring(last10StartIndex);
            if (!last10Characters.equals("@gmail.com")){
                editTextEmail.setError("Invalid Email Id");
            }
            if (password.length()<8)
            {
                editTextPassword.setError("Number character of Password need longer");
            }
            Address signedAdd = new Address(spinnerTP.getSelectedItem().toString(),spinnerHuyen.getSelectedItem().toString(),spinnerXa.getSelectedItem().toString());
            User signedUser = new User(editTextName.getText().toString().trim(),editTextEmail.getText().toString().trim(),signedAdd);
            Intent mainAct = new Intent(this, LoginView.class);
            mainAct.putExtra("cloud",myCloudDB);
            myCloudDB.signUpUser(editTextEmail.getText().toString().trim(),editTextPassword.getText().toString().trim(),signedUser, this, mainAct);
        }
        );
    }

    private void initUi(){
        setContentView(R.layout.activity_sign_up_view);
        this.textView = findViewById(R.id.textView);
        this.editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        this.editTextName = findViewById(R.id.EditText_Name);
        this.editTextPassword = findViewById(R.id.editTextTextPassword);
        this.button = findViewById(R.id.button_confirm_sign_up);
        this.spinnerTP=(Spinner)this.findViewById(R.id.spinner);
        this.spinnerHuyen= (Spinner)this.findViewById(R.id.spinner3);
        this.spinnerXa= (Spinner)this.findViewById(R.id.spinner4);
        this.myCloudDB = (CloudDBModel) getIntent().getSerializableExtra("cloud");
    }


}
