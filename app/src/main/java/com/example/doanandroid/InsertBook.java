package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class InsertBook extends AppCompatActivity {
    //Khai báo biến
    EditText addBook, addTacGia, addSoLuong, addInformation;
    Button btnHuy, btnThem;
    Spinner spinnerTacGia,spinnerTheLoai;
    dataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);
        db = new dataBase(this, "QuanLiSach.db", null, 1);
        //Ánh xạ EditText
        addBook = findViewById(R.id.addBook);
        addSoLuong = findViewById(R.id.addSoLuong);
        addInformation = findViewById(R.id.addInformation);
        // select option
        spinnerTacGia = findViewById(R.id.spinnerTacGia);
        List<String> tacGiaNames = db.getAllTacGiaNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Giao diện mặc định
                tacGiaNames
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Giao diện khi xổ xuống
        spinnerTacGia.setAdapter(adapter);

        spinnerTheLoai = findViewById(R.id.spinnerTheLoai);
        List<String> theLoaiNames = db.getAllTheLoaiNames();
        ArrayAdapter<String> adapterTheLoai = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                theLoaiNames
        );
        adapterTheLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Giao diện khi xổ xuống
        spinnerTheLoai.setAdapter(adapterTheLoai);
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
                String tenSach = addBook.getText().toString().trim();
                String soLuongStr = addSoLuong.getText().toString().trim();
                String namXB = "2024"; // Nếu không có input năm, có thể đặt mặc định
                String thongTin = addInformation.getText().toString().trim();
                String tacGia = spinnerTacGia.getSelectedItem().toString();
                String theLoai = spinnerTheLoai.getSelectedItem().toString();

                // Kiểm tra tính hợp lệ
                if (tenSach.isEmpty() || soLuongStr.isEmpty() || tacGia.isEmpty() || theLoai.isEmpty()) {
                    // Hiển thị thông báo nếu dữ liệu bị thiếu
                    Toast.makeText(InsertBook.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int tacGiaID = db.getTacGiaID(tacGia);
                int theLoaiID = db.getTheLoaiID(theLoai);
                Log.d("InsertBook", "tacgiaID: " + tacGiaID + ", theLoaiID: " + theLoaiID);


                // Chuyển số lượng sang Integer
                int soLuong;
                try {
                    soLuong = Integer.parseInt(soLuongStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(InsertBook.this, "Số lượng phải là số nguyên!", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isInserted = db.insertSach(tenSach, soLuong, tacGiaID, theLoaiID, thongTin);
                if (isInserted) {
                    Toast.makeText(InsertBook.this, "Thêm sách thành công!", Toast.LENGTH_SHORT).show();
                    Intent resultIntent = new Intent();
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(InsertBook.this, "Thêm sách thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}