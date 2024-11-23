package com.example.doanandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TacGiaa extends Fragment {
    private FloatingActionButton fab;
    private ListView listTacGia;
    TextView SLTacGia;
    public TacGiaa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_the_loai, container, false);
        fab = view.findViewById(R.id.fab_home_tl);
        listTacGia = view.findViewById(R.id.listTL);
        SLTacGia = view.findViewById(R.id.viewSLTL);
        ////
        loadData();
        ////
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddTacgia.class);
                startActivity(intent);
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
        Cursor cursor = dbHelper.getAllRecords("TacGia");
        int sl = 0;
        sl = dbHelper.countSoLuong("TacGia");
        SLTacGia.setText(String.valueOf(sl));
        List<Author> author = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                int id = cursor.getInt(cursor.getColumnIndex("maTacGia")); // Lấy ID
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("tenTacGia"));
                @SuppressLint("Range")
                String year = cursor.getString(cursor.getColumnIndex("namSinh"));
//
                author.add(new Author( name,year,id)); // Thêm id vào đối tượng Genre
            } while (cursor.moveToNext());
        }

        cursor.close();

        // Cập nhật lại adapter với dữ liệu mới
        AdapterTacGia adapterTacGia = new AdapterTacGia(requireContext(), author,TacGiaa.this);
        listTacGia.setAdapter(adapterTacGia);
    }
}