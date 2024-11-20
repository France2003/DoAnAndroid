package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateTacGia extends AppCompatActivity {
    EditText upTenTG, upYearTG, upInformationTG;
    Button btnHuyTG, btnUpTG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tacgia);
        upTenTG = findViewById(R.id.upNameTacGia);
        upYearTG = findViewById(R.id.upYearTG);
        upInformationTG = findViewById(R.id.upInformationTG);
        btnHuyTG = findViewById(R.id.btnHuyUpTG);
        btnUpTG = findViewById(R.id.btnUpdateTG);
        btnHuyTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHuy = new Intent(UpdateTacGia.this, TacGiaa.class);
                startActivity(intentHuy);
            }
        });
        btnUpTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}