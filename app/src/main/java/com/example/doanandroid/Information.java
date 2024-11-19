package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    // Khai Báo Biến
    TextView ifBook, ifTacGia, ifSoLuong, ifInformation;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Ánh xạ TextView
        ifBook = findViewById(R.id.ifBook);
        ifTacGia = findViewById(R.id.ifTacGia);
        ifSoLuong = findViewById(R.id.ifSoLuong);
        ifInformation = findViewById(R.id.ifInformation);

        // Ánh xạ Button
        btnCancel = findViewById(R.id.btnCancel);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        int quantity = intent.getIntExtra("quantity", 0);
        String description = intent.getStringExtra("description");

        // Hiển thị thông tin sách
        ifBook.setText("Tên sách: " + title);
        ifTacGia.setText("Tác giả: " + author);
        ifSoLuong.setText("Số lượng: " + quantity);
        ifInformation.setText("Mô tả: " + description);

        // Sự kiện nút hủy
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quay lại màn hình trước đó
                finish();
            }
        });
    }
}
