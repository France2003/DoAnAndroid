package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class TacGia extends AppCompatActivity {


    SQLiteDatabase ThuVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThuVien = openOrCreateDatabase("qlThuVien.db",MODE_PRIVATE,null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tac_gia);
    }
}