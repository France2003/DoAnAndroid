package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateTheLoai extends AppCompatActivity {
    EditText UpdateTheLoai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_the_loai);

        // Lấy ID và tên thể loại từ Intent
        int genreId = getIntent().getIntExtra("idGenre", -1);
        String genreTitle = getIntent().getStringExtra("TitleGenre");

        EditText editText = findViewById(R.id.UpdateTheLoai);
        editText.setText(genreTitle);

        Button btnUpdate = findViewById(R.id.btnUpdateTL);
        Button btnBack = findViewById(R.id.btnHuyTL);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTitle = editText.getText().toString();
                dataBase dbHelper = new dataBase(UpdateTheLoai.this, "QuanLiSach.db", null, 1);
                boolean isUpdated = dbHelper.updateTheLoai(genreId, updatedTitle);
                if (isUpdated) {
                    Toast.makeText(UpdateTheLoai.this, "Cập nhật thể loại thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateTheLoai.this, "Cập nhật thể loại thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
