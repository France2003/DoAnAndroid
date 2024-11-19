package com.example.doanandroid;

import android.content.DialogInterface;
import android.content.Intent;
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

    public Sach() {
        // Required empty public constructor
    }

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
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InsertBook.class);
                startActivity(intent);
            }
        });
        return view;
    }
}