package com.example.doanandroid;

import android.annotation.SuppressLint;
import android.content.DialogInterface;

import android.content.Intent;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.EditText;

import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Sach extends Fragment {
    private FloatingActionButton floatingActionButton;

    private ImageButton imageMenu;
    private ListView listBook;
    private TextView count;

    private FloatingActionButton toolBar;
    SQLiteDatabase ThuVien;
    EditText name;



    public Sach() {
        // Required empty public constructor

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        View viewMenu = inflater.inflate(R.layout.card_book, container, false);
        floatingActionButton = view.findViewById(R.id.fab_home);

        //
        listBook = view.findViewById(R.id.listBook);
        List<Book> books = new ArrayList<>();
        Adapter adapter = new Adapter(requireContext(), books, Sach.this);
        listBook.setAdapter(adapter);
        count = view.findViewById(R.id.viewSL);
        //

        //MenuDots
        imageMenu = viewMenu.findViewById(R.id.imageMenu);
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(requireContext(), "Bạn đã click vào", Toast.LENGTH_LONG);
                toast.show();
            }
        });
//        toolBar = view.findViewById(R.id.imageButton1);

        name = view.findViewById(R.id.idBook);
        // Load dữ liệu ban đầu
        loadData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InsertBook.class);
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
        Cursor cursor = dbHelper.getAllRecords("Sach");
        int sl = 0;
        sl = dbHelper.countSoLuong("Sach");
        count.setText(String.valueOf(sl));
        List<Book> book = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                int id = cursor.getInt(cursor.getColumnIndex("maSach")); // Lấy ID
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("ten"));
                @SuppressLint("Range")
                String tacgia =cursor.getString(cursor.getColumnIndex("maTacGia"));
                String NameTG = dbHelper.getTacGia(tacgia);
//
//                int so = dbHelper.getBooksCountByGenre(name);

                book.add(new Book(id,name, NameTG));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show();
        }

        cursor.close();

        // Cập nhật lại adapter với dữ liệu mới
        Adapter adapterSach= new Adapter(requireContext(), book,Sach.this);
        listBook.setAdapter(adapterSach);
    }
}



