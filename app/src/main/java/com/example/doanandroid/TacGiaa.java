package com.example.doanandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TacGiaa extends Fragment {
    private FloatingActionButton fab;
    public TacGiaa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_tac_giaa, container, false);


        fab = view.findViewById(R.id.fab_home);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddTacgia.class);
//            intent.putExtra("user_id", userId);
//            ((Activity) getContext()).startActivityForResult(intent, 1);
                startActivity(intent);
        });


        return view;
    }
}