package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    // Khai Báo Biến
    TextView ifBook, ifTacGia,ifTheLoai, ifSoLuong, ifInformation;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        int sachId = getIntent().getIntExtra("sachId", -1);

        // Ánh xạ TextView
        ifBook = findViewById(R.id.ifBook);
        ifTacGia = findViewById(R.id.ifTacGia);
        ifTheLoai = findViewById(R.id.ifTheLoaiBook);
        ifSoLuong = findViewById(R.id.ifSoLuong);
        ifInformation = findViewById(R.id.ifInformation);

        // Ánh xạ Button
        btnCancel = findViewById(R.id.btnCancel);

        loadBookDetails(sachId);

        // Sự kiện nút hủy
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quay lại màn hình trước đó
                finish();
            }
        });

    }
    private void loadBookDetails(int sachId) {
        // Create database helper instance
        dataBase dbHelper = new dataBase(Information.this, "QuanLiSach.db", null, 1);

        // Fetch book details from the database using getRecordById
        Cursor cursor = dbHelper.getRecordById("Sach", "maSach", sachId);

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve the data from the cursor
            @SuppressLint("Range") String bookName = cursor.getString(cursor.getColumnIndex("ten"));
            @SuppressLint("Range") int tacGia = cursor.getInt(cursor.getColumnIndex("maTacGia"));
            @SuppressLint("Range") int theloai = cursor.getInt(cursor.getColumnIndex("maTheLoai"));
            @SuppressLint("Range") int soLuong = cursor.getInt(cursor.getColumnIndex("soLuong"));
            @SuppressLint("Range") String thongTinSach = cursor.getString(cursor.getColumnIndex("thongTinSach"));

            // Fill the data into the corresponding EditText fields
            ifBook.setText(bookName);
            ifSoLuong.setText(String.valueOf(soLuong));
            ifInformation.setText(thongTinSach);

            // select tên thông qua id
            String nameTG = dbHelper.getTacGia(String.valueOf(tacGia));
            String nameTL = dbHelper.getTheLoai(String.valueOf(theloai));

            ifTacGia.setText(nameTG);
            ifTheLoai.setText(nameTL);

            cursor.close(); // Don't forget to close the cursor
        }
    }
}
