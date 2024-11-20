package com.example.doanandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TacGiaa extends Fragment {
    private FloatingActionButton fab;
    private ListView listTacGia;
    public TacGiaa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_tac_giaa, container, false);
        fab = view.findViewById(R.id.fab_home_tacgia);
        ////
        listTacGia = view.findViewById(R.id.listTacGia );
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Tô Hoài", "10/02/2024", "10/02/2024"));
        authors.add(new Author("Truyện Kinh Dị", "10/02/2024","10/6/2024"));
        AdapterTacGia adapterTacGia = new AdapterTacGia(getActivity(), authors);
        listTacGia.setAdapter(adapterTacGia);
        ////
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddTacgia.class);
                startActivity(intent);
        });
        return view;
    }
}