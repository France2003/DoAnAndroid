package com.example.doanandroid;

import android.content.DialogInterface;
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
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Sach extends Fragment {
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton toolBar;

    public Sach() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        floatingActionButton = view.findViewById(R.id.fab_home);
        toolBar = view.findViewById(R.id.imageButton1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hộp thoại thêm mới khi nhấn vào FAB
                showAddDialog();
            }
        });
        //Toolbar
        toolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(requireContext(), view); // Sử dụng requireContext()
                popupMenu.getMenuInflater().inflate(R.menu.book_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();

                        if (id == R.id.update) {
                            // Logic cho "Update"
                            Toast.makeText(getActivity().getApplicationContext(), "Update Clicked!", Toast.LENGTH_SHORT).show();
                        } else if (id == R.id.delete) {
                            // Logic cho "Delete"
                            Toast.makeText(getActivity().getApplicationContext(), "Delete Clicked!", Toast.LENGTH_SHORT).show();
                        } else if (id == R.id.information) {
                            // Logic cho "Information"
                            Toast.makeText(getActivity().getApplicationContext(), "Information Clicked!", Toast.LENGTH_SHORT).show();
                        }
                        return true; // Đã xử lý
                    }
                });
                popupMenu.show();
            }
        });

        return view;
    }

    ////
    private void showAddDialog() {
        // Use the activity context for the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();

        // Inflate the custom dialog layout
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);

        // Set dialog button listeners
        builder.setPositiveButton("THÊM MỚI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get data from dialog fields and handle the logic for adding new data
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Show the AlertDialog
        builder.create().show();
    }
}