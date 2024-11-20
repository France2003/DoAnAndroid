package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformationTacGia extends AppCompatActivity {
    TextView IfNameTacGia,IFYearTG , IFInformationTG;
    Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_tac_gia);
        // Ánh xạ TextView
        IfNameTacGia = findViewById(R.id.IFNameTacGia);
        IFYearTG = findViewById(R.id.IFYearTG);
        IFInformationTG = findViewById(R.id.IFInformationTG);
        // Ánh xạ Button
        btnCancel = findViewById(R.id.btnCancelTG);
        Intent intent = getIntent();
        String nameAuthor = intent.getStringExtra("Tên Tác Giả");
        String year = intent.getStringExtra("Năm Sinh");
        String information = intent.getStringExtra("Thông Tin Tác Giả");
        // Hiển thị thông tin sách
        IfNameTacGia.setText("Tên tác giả: " + nameAuthor);
        IFYearTG.setText("Tác giả: " + year);
        IFInformationTG.setText("Thông tin tác giả: " + information);
        // Sự kiện nút hủy
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}