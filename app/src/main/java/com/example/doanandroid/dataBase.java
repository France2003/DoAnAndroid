package com.example.doanandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuanLiSach.db";
    private static final int DATABASE_VERSION = 1;

    // Table: Sach
    public static final String TABLE_SACH = "Sach";
    public static final String COLUMN_SACHID = "maSach";
    public static final String COLUMN_TENSACH = "ten";
    public static final String COLUMN_SOLUONG = "soLuong";
    public static final String COLUMN_NAMXB = "namXB";

    // Table: Sach
    public static final String TABLE_TACGIA = "TacGia";
    public static final String COLUMN_TACGIAID = "maTacGia";
    public static final String COLUMN_TENTACGIA = "tenTacGia";
    public static final String COLUMN_NAMSINH = "namSinh";

    // Table: Sach
    public static final String TABLE_THELOAI = "TheLoai";
    public static final String COLUMN_THELOAIID = "maTheLoai";
    public static final String COLUMN_TENTHELOAI = "tenTheLoai";


    public dataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table sach
        String CREATE_SACH_TABLE = "CREATE TABLE " + TABLE_SACH + " (" +
                COLUMN_SACHID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TENSACH + " TEXT NOT NULL, " +
                COLUMN_TACGIAID + " TEXT NOT NULL," +   COLUMN_THELOAIID + " TEXT NOT NULL, " +
                COLUMN_SOLUONG + " INT NOT NULL,"+   COLUMN_NAMXB + " TEXT NOT NULL,"+
                "FOREIGN KEY("+COLUMN_TACGIAID+")REFERENCES " +TABLE_TACGIA+"("+COLUMN_TACGIAID+"),"
                +
                "FOREIGN KEY("+COLUMN_THELOAIID+")REFERENCES " +TABLE_THELOAI+"("+COLUMN_THELOAIID+"))";
        db.execSQL(CREATE_SACH_TABLE);
        // Create table tacgia
        String CREATE_TACGIA_TABLE = "CREATE TABLE " + TABLE_TACGIA + " (" +
                COLUMN_TACGIAID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TENTACGIA + " TEXT NOT NULL, " +
                COLUMN_NAMSINH + " TEXT NOT NULL)";
        db.execSQL(CREATE_TACGIA_TABLE);
        // Create table theloai
        String CREATE_THELOAI_TABLE = "CREATE TABLE " + TABLE_THELOAI + " (" +
                COLUMN_THELOAIID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TENTHELOAI + " TEXT NOT NULL)";
        db.execSQL(CREATE_THELOAI_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //them sach
    public boolean insertSach(String name, Integer soLuong , String namXB , String tacgia,String theLoai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENSACH, name);
        values.put(COLUMN_SOLUONG, soLuong);
        values.put(COLUMN_NAMXB, namXB);
        values.put(COLUMN_TACGIAID, tacgia);
        values.put(COLUMN_THELOAIID, theLoai);

        long result = db.insert(TABLE_SACH, null, values);
        db.close();
        return result != -1; // Nếu kết quả là -1, việc chèn đã thất bại.
    }
    //update Sách
    public boolean updateSach(int sachId, String name, Integer soLuong, String namXB, String tacgia, String theLoai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENSACH, name);
        values.put(COLUMN_SOLUONG, soLuong);
        values.put(COLUMN_NAMXB, namXB);
        values.put(COLUMN_TACGIAID, tacgia);
        values.put(COLUMN_THELOAIID, theLoai);

        // Cập nhật dựa trên maSach
        int result = db.update(TABLE_SACH, values, COLUMN_SACHID + " = ?", new String[]{String.valueOf(sachId)});
        db.close();

        return result > 0; // Nếu result > 0, có ít nhất một bản ghi đã được cập nhật.
    }

    //them TACGIA
    public boolean insertTacGia(String name, String namsinh ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENTACGIA, name);
        values.put(COLUMN_NAMSINH, namsinh);

        long result = db.insert(TABLE_TACGIA, null, values);
        db.close();
        return result != -1; // Nếu kết quả là -1, việc chèn đã thất bại.
    }
    //update tacgia
    public boolean updateTacGia(int tacGiaId, String name, String namSinh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENTACGIA, name);
        values.put(COLUMN_NAMSINH, namSinh);

        // Cập nhật dựa trên maTacGia
        int result = db.update(TABLE_TACGIA, values, COLUMN_TACGIAID + " = ?", new String[]{String.valueOf(tacGiaId)});
        db.close();

        return result > 0; // Nếu result > 0, có ít nhất một bản ghi đã được cập nhật.
    }

    //them Theloai
    public boolean insertTheLoai(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENTHELOAI, name);

        long result = db.insert(TABLE_THELOAI, null, values);
        db.close();
        return result != -1;
    }
    //update theloai
    public boolean updateTheLoai(int theLoaiId, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENTHELOAI, name);

        // Cập nhật dựa trên maTheLoai
        int result = db.update(TABLE_THELOAI, values, COLUMN_THELOAIID + " = ?", new String[]{String.valueOf(theLoaiId)});
        db.close();

        return result > 0;
    }
    //delete
    public boolean deleteRecord(String tableName, String idColumn, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(tableName, idColumn + " = ?", new String[]{String.valueOf(id)});
        db.close();

        return result > 0;
    }
    //getALl
    public Cursor getAllRecords(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + tableName, null);
    }
    public Cursor getRecordById(String tableName, String idColumn, int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + tableName + " WHERE " + idColumn + " = ?", new String[]{String.valueOf(id)});
    }

}
