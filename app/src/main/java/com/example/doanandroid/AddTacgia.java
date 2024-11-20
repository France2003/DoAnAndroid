package com.example.doanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddTacgia extends AppCompatActivity {
    EditText addTenTG, addYearTG, addInformationTG;
    Button btnHuyTG, btnThemTG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_tacgia);
        addTenTG = findViewById(R.id.addTacGia);
        addYearTG = findViewById(R.id.addYearTG);
        addInformationTG = findViewById(R.id.addInformationTG);
        btnHuyTG = findViewById(R.id.btnHuyTG);
        btnThemTG = findViewById(R.id.btnThemTG);
        //
        btnHuyTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHuy = new Intent(AddTacgia.this, TacGiaa.class);
                startActivity(intentHuy);
            }
        });
        btnThemTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}