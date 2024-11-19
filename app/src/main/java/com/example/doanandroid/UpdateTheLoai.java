package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateTheLoai extends AppCompatActivity {
    EditText UpdateTheLoai;
    Button btnHuyTL, btnUpdateTL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_the_loai);
        UpdateTheLoai = findViewById(R.id.UpdateTheLoai);
        btnHuyTL = findViewById(R.id.btnHuyTL);
        btnUpdateTL = findViewById(R.id.btnUpdateTL);
        //
        btnHuyTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHuy = new Intent(UpdateTheLoai.this, TheLoai.class);
                startActivity(intentHuy);
            }
        });
        btnUpdateTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}