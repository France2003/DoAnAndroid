package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
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

        int authorId = getIntent().getIntExtra("idAuthor", -1);
        // Ánh xạ TextView
        IfNameTacGia = findViewById(R.id.IFNameTacGia);
        IFYearTG = findViewById(R.id.IFYearTG);
        IFInformationTG = findViewById(R.id.IFInformationTG);
        // Ánh xạ Button
        btnCancel = findViewById(R.id.btnCancelTG);
        loadAuthorDetails(authorId);
        // Sự kiện nút hủy
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadAuthorDetails(int id) {
        // Create database helper instance
        dataBase dbHelper = new dataBase(InformationTacGia.this, "QuanLiSach.db", null, 1);

        // Fetch book details from the database using getRecordById
        Cursor cursor = dbHelper.getRecordById("TacGia", "maTacGia", id);

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve the data from the cursor
            @SuppressLint("Range") String TacGiaName = cursor.getString(cursor.getColumnIndex("tenTacGia"));
            @SuppressLint("Range") String TacGiaYear = cursor.getString(cursor.getColumnIndex("namSinh"));
            @SuppressLint("Range") String TacGiaInfo = cursor.getString(cursor.getColumnIndex("thongTin"));

            // Fill the data into the corresponding EditText fields
            IfNameTacGia.setText(TacGiaName);
            IFYearTG.setText(TacGiaYear);
            IFInformationTG.setText(TacGiaInfo);


            cursor.close(); // Don't forget to close the cursor
        }
    }
}