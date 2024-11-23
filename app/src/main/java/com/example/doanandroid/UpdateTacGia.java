package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateTacGia extends AppCompatActivity {
    EditText upTenTG, upYearTG, upInformationTG;
    Button btnHuyTG, btnUpTG;
    dataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tacgia);

        db = new dataBase(this, "QuanLiSach.db", null, 1);
        int authorId = getIntent().getIntExtra("idAuthor", -1);

        upTenTG = findViewById(R.id.upNameTacGia);
        upYearTG = findViewById(R.id.upYearTG);
        upInformationTG = findViewById(R.id.upInformationTG);
        btnHuyTG = findViewById(R.id.btnHuyUpTG);
        btnUpTG = findViewById(R.id.btnUpdateTG);
        btnHuyTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        loadAuthorDetails(authorId);
        btnUpTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = upTenTG.getText().toString();
                String year =upYearTG.getText().toString();
                String info = upInformationTG.getText().toString();
                boolean isUpdated = db.updateTacGia(authorId,name,year,info);
                if (isUpdated) {
                    // Show success message
                    Toast.makeText(UpdateTacGia.this, "Author updated successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, go back to the book list activity
                    Intent resultIntent = new Intent();
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    // Show failure message
                    Toast.makeText(UpdateTacGia.this, "Failed to update Author", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loadAuthorDetails(int id) {
        // Create database helper instance
        dataBase dbHelper = new dataBase(UpdateTacGia.this, "QuanLiSach.db", null, 1);

        // Fetch book details from the database using getRecordById
        Cursor cursor = dbHelper.getRecordById("TacGia", "maTacGia", id);

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve the data from the cursor
            @SuppressLint("Range") String TacGiaName = cursor.getString(cursor.getColumnIndex("tenTacGia"));
            @SuppressLint("Range") String TacGiaYear = cursor.getString(cursor.getColumnIndex("namSinh"));
            @SuppressLint("Range") String TacGiaInfo = cursor.getString(cursor.getColumnIndex("thongTin"));

            // Fill the data into the corresponding EditText fields
            upTenTG.setText(TacGiaName);
            upYearTG.setText(TacGiaYear);
            upInformationTG.setText(TacGiaInfo);


            cursor.close(); // Don't forget to close the cursor
        }
    }
}