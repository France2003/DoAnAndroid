package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class UpdateBook extends AppCompatActivity {
    // Declare variables
    EditText upBook, upSoLuong, upInformation;
    Button btnHuyUpdate, btnUpdate;
    Spinner spinnerTacGia, spinnerTheLoai;
    dataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        db = new dataBase(this, "QuanLiSach.db", null, 1);
        // Get book ID from Intent
        int sachId = getIntent().getIntExtra("sachId", -1);

        // Map EditText and Spinner views
        upBook = findViewById(R.id.upBook);
        spinnerTacGia = findViewById(R.id.spinnerTacGia);
        List<String> tacGiaNames = db.getAllTacGiaNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Giao diện mặc định
                tacGiaNames
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Giao diện khi xổ xuống
        spinnerTacGia.setAdapter(adapter);
        upSoLuong = findViewById(R.id.upSoLuong);
        upInformation = findViewById(R.id.upInformation);
        spinnerTheLoai = findViewById(R.id.spinnerTheLoai);
        List<String> theLoaiNames = db.getAllTheLoaiNames();
        ArrayAdapter<String> adapterTheLoai = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                theLoaiNames
        );
        adapterTheLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Giao diện khi xổ xuống
        spinnerTheLoai.setAdapter(adapterTheLoai);
        btnHuyUpdate = findViewById(R.id.btnHuyUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);

        // Load data based on sachId
        loadBookDetails(sachId);

        // Event listener for cancel button
        btnHuyUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Event listener for update button (you can add update logic here)
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = upBook.getText().toString();
                int soLuong = Integer.parseInt(upSoLuong.getText().toString());
                String info = upInformation.getText().toString();

                // Get selected author and genre IDs from spinners
                int tacGiaId = spinnerTacGia.getSelectedItemPosition() + 1; // Assuming ID starts from 1
                int theLoaiId = spinnerTheLoai.getSelectedItemPosition() + 1; // Assuming ID starts from 1

                // Call update method
                boolean isUpdated = db.updateSach(sachId, name, soLuong, tacGiaId, theLoaiId, info);

                if (isUpdated) {
                    // Show success message
                    Toast.makeText(UpdateBook.this, "Book updated successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, go back to the book list activity
                    Intent resultIntent = new Intent();
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    // Show failure message
                    Toast.makeText(UpdateBook.this, "Failed to update book", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to load book details by its ID
    private void loadBookDetails(int sachId) {
        // Create database helper instance
        dataBase dbHelper = new dataBase(UpdateBook.this, "QuanLiSach.db", null, 1);

        // Fetch book details from the database using getRecordById
        Cursor cursor = dbHelper.getRecordById("Sach", "maSach", sachId);

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve the data from the cursor
            @SuppressLint("Range") String bookName = cursor.getString(cursor.getColumnIndex("ten"));
            @SuppressLint("Range") int tacGia = cursor.getInt(cursor.getColumnIndex("maTacGia"));
            @SuppressLint("Range") int theloai = cursor.getInt(cursor.getColumnIndex("maTheLoai"));
            @SuppressLint("Range") int soLuong = cursor.getInt(cursor.getColumnIndex("soLuong"));
            @SuppressLint("Range") String thongTinSach = cursor.getString(cursor.getColumnIndex("thongTinSach"));

            // Fill the data into the corresponding EditText fields
            upBook.setText(bookName);
            upSoLuong.setText(String.valueOf(soLuong));
            upInformation.setText(thongTinSach);

            // select tên thông qua id
            String nameTG = dbHelper.getTacGia(String.valueOf(tacGia));
            String nameTL = dbHelper.getTheLoai(String.valueOf(theloai));
            selectSpinnerItem(spinnerTacGia, nameTG);
            selectSpinnerItem(spinnerTheLoai, nameTL); // Replace with actual genre if you have it

            cursor.close(); // Don't forget to close the cursor
        }
    }

    // Method to select an item in the spinner based on the value
    private void selectSpinnerItem(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}
