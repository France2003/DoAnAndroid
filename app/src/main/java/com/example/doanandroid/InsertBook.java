package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertBook extends AppCompatActivity {
    //Khai báo biến
    EditText addBook, addTacGia, addSoLuong, addInformation;
    Button btnHuy, btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);
        //Ánh xạ EditText
        addBook = findViewById(R.id.addBook);
        addTacGia = findViewById(R.id.addTacGia);
        addSoLuong = findViewById(R.id.addSoLuong);
        addInformation = findViewById(R.id.addInformation);
        //Ánh xạ Button
        btnHuy = findViewById(R.id.btnHuy);
        btnThem = findViewById(R.id.btnThem);
        //Sự kiện
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHuy = new Intent(InsertBook.this, Sach.class);
                startActivity(intentHuy);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}