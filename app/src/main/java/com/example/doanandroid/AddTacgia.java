package com.example.doanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddTacgia extends AppCompatActivity {

    EditText addTenTG, addYearTG, addInformationTG;
    Button btnHuyTG, btnThemTG;
    EditText name,year,infomation;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_tacgia);
        addTenTG = findViewById(R.id.addTacGia);
        addYearTG = findViewById(R.id.addYearTG);
        addInformationTG = findViewById(R.id.addInformationTG);
        btnHuyTG = findViewById(R.id.btnHuyTG);
        btnThemTG = findViewById(R.id.btnThemTG);
        //
        btnHuyTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHuy = new Intent(AddTacgia.this, TacGiaa.class);
                startActivity(intentHuy);
            }
        });
        btnThemTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        name = findViewById(R.id.addTacGia);
        year = findViewById(R.id.addYearTG);
        infomation = findViewById(R.id.addInformationTG);
        btnSave =  findViewById(R.id.btnThemTG);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tacGiaName = name.getText().toString().trim();
                String namSinh = year.getText().toString().trim();
                String thongTin = infomation.getText().toString().trim();

                // Kiểm tra dữ liệu nhập vào
                if (tacGiaName.isEmpty()) {
                    name.setError("Vui lòng nhập tên tác giả");
                    name.requestFocus();
                    return;
                }

                if (namSinh.isEmpty()) {
                    year.setError("Vui lòng nhập năm sinh");
                    year.requestFocus();
                    return;
                }
                dataBase dbHelper = new dataBase(
                        AddTacgia.this,
                        "QuanLiSach.db",
                        null,
                        1
                );

                boolean isInserted = dbHelper.insertTacGia(tacGiaName, namSinh,thongTin);

                if (isInserted) {
                    Toast.makeText(AddTacgia.this, "Thêm tác giả thành công", Toast.LENGTH_SHORT).show();
                    finish(); // Đóng Activity sau khi thêm thành công
                } else {
                    Toast.makeText(AddTacgia.this, "Thêm tác giả thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}