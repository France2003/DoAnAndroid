package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase ThuVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThuVien = openOrCreateDatabase("qlThuVien.db",MODE_PRIVATE,null);
        try {
            String sql = "CREATE TABLE tbSach(maSach TEXT primary key,tenSach TEXT, tacGia TEXT ,theLoai TEXT,namXB TEXT,soLuong INTEGER )";

            String sql1 = "CREATE TABLE tbTacGia(maTG TEXT primary key,tenTG TEXT ,namSinh TEXT)";

            String sql2 = "CREATE TABLE tbTheLoai(maTL TEXT primary key,tenTL TEXT)";

            ThuVien.execSQL(sql);
            ThuVien.execSQL(sql1);
            ThuVien.execSQL(sql2);
        }
        catch (Exception e)
        {
            Log.e("Error","Table sách đã tồn tại");
        }

    }
}