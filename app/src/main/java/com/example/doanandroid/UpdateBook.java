package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateBook extends AppCompatActivity {
    //Khai Báo Biến
    EditText upBook, upTacGia, upSoLuong, upInformation;
    Button btnHuyUpdate, btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        //Ánh xạ EditText
        upBook = findViewById(R.id.upBook);
        upTacGia = findViewById(R.id.upTacGia);
        upSoLuong = findViewById(R.id.upSoLuong);
        upInformation = findViewById(R.id.upInformation);
        //Ánh xạ Button
        btnHuyUpdate = findViewById(R.id.btnHuyUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        //Sự Kiện
        btnHuyUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHuy = new Intent(UpdateBook.this, Sach.class);
                startActivity(intentHuy);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}