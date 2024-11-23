package com.example.doanandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TheLoai extends Fragment {
    //Khai baso bieens
    private FloatingActionButton floatingActionButton;
    private ListView listTheLoai;
    private TextView count;
    public TheLoai() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_the_loai, container, false);
        floatingActionButton = view.findViewById(R.id.fab_home_tl);
        listTheLoai = view.findViewById(R.id.listTL);
        count = view.findViewById(R.id.viewSLTL);

        // Load dữ liệu ban đầu
        loadData();

        // Cài đặt sự kiện cho FloatingActionButton
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InsertTheLoai.class);
                startActivity(intent);
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Tải lại dữ liệu mỗi khi người dùng quay lại fragment
        loadData();
    }
    public void loadData() {
        dataBase dbHelper = new dataBase(requireContext(), "QuanLiSach.db", null, 1);
        Cursor cursor = dbHelper.getAllRecords("TheLoai");
        int sl = 0;
        sl = dbHelper.countSoLuong("TheLoai");
        count.setText(String.valueOf(sl));
        List<Genre> genres = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                int id = cursor.getInt(cursor.getColumnIndex("maTheLoai")); // Lấy ID
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("tenTheLoai"));
//
                int so = dbHelper.getBooksCountByGenre(String.valueOf(id));

                genres.add(new Genre(id, name, so)); // Thêm id vào đối tượng Genre
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show();
        }

        cursor.close();

        // Cập nhật lại adapter với dữ liệu mới
        AdapterTheLoai adapterTheLoai = new AdapterTheLoai(requireContext(), genres,TheLoai.this);
        listTheLoai.setAdapter(adapterTheLoai);
    }

}