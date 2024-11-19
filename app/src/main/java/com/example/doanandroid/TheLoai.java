package com.example.doanandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TheLoai extends Fragment {
    //Khai baso bieens
    private FloatingActionButton floatingActionButton;
    private ListView listTheLoai;

    public TheLoai() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_the_loai, container, false);
        floatingActionButton = view.findViewById(R.id.fab_home_tl);
        ///
        listTheLoai = view.findViewById(R.id.listTL);
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("Truyện Cười", 5));
        genres.add(new Genre("Truyện Kinh Dị", 3));
        AdapterTheLoai adapterTheLoai = new AdapterTheLoai(requireContext(), genres);
        listTheLoai.setAdapter(adapterTheLoai);
        //
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InsertTheLoai.class);
                startActivity(intent);
            }
        });
        return view;
    }
}