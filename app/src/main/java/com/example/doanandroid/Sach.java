package com.example.doanandroid;

import android.annotation.SuppressLint;
import android.content.DialogInterface;

import android.content.Intent;


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
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Sach extends Fragment {
    private FloatingActionButton floatingActionButton;

    private ImageButton imageMenu;
    private ListView listBook;

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
        books.add(new Book("Title 1", "Author 1", '5', "Lên bcc"));
        books.add(new Book("Title 2", "Author 2", '2', "m mmaans"));
        Adapter adapter = new Adapter(requireContext(), books);
        listBook.setAdapter(adapter);
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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InsertBook.class);
                startActivity(intent);
            }
        });

        return view;
    }





//        //Toolbar
//        toolBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu popupMenu = new PopupMenu(requireContext(), view); // Sử dụng requireContext()
//                popupMenu.getMenuInflater().inflate(R.menu.book_menu, popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        int id = menuItem.getItemId();
//
//                        if (id == R.id.update) {
//                            // Logic cho "Update"
//                            Toast.makeText(getActivity().getApplicationContext(), "Update Clicked!", Toast.LENGTH_SHORT).show();
//                        } else if (id == R.id.delete) {
//                            // Logic cho "Delete"
//                            Toast.makeText(getActivity().getApplicationContext(), "Delete Clicked!", Toast.LENGTH_SHORT).show();
//                        } else if (id == R.id.information) {
//                            // Logic cho "Information"
//                            Toast.makeText(getActivity().getApplicationContext(), "Information Clicked!", Toast.LENGTH_SHORT).show();
//                        }
//                        return true; // Đã xử lý
//                    }
//                });
//                popupMenu.show();
//            }
//        });
//
//        return view;
    }



