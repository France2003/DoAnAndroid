package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertTheLoai extends AppCompatActivity {
    //Khai báo biến
    EditText addTheLoai;
    Button btnHuy, btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_the_loai);
        addTheLoai = findViewById(R.id.addTheLoai);
        btnHuy = findViewById(R.id.btnHuy);
        btnThem = findViewById(R.id.btnThem);
        //
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHuy = new Intent(InsertTheLoai.this, TheLoai.class);
                startActivity(intentHuy);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String theLoaiName = addTheLoai.getText().toString().trim();
                if (theLoaiName.isEmpty()) {
                    addTheLoai.setError("Vui lòng nhập tên thể loại");
                    addTheLoai.requestFocus();
                    return;
                }
                dataBase dbHelper = new dataBase(
                        InsertTheLoai.this,
                        "QuanLiSach.db",
                        null,
                        1
                );
                boolean isInserted = dbHelper.insertTheLoai(theLoaiName);
                if (isInserted) {
                    Toast.makeText(InsertTheLoai.this, "Thêm thể loại thành công", Toast.LENGTH_SHORT).show();
                    Intent resultIntent = new Intent();
                    setResult(RESULT_OK, resultIntent);
                    finish(); // Đóng Activity sau khi thêm thành công
                } else {
                    Toast.makeText(InsertTheLoai.this, "Thêm thể loại thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}