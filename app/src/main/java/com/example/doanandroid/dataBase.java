package com.example.doanandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuanLiSach.db";
    private static final int DATABASE_VERSION = 1;

    // Table: Sach
    public static final String TABLE_SACH = "Sach";
    public static final String COLUMN_SACHID = "maSach";
    public static final String COLUMN_TENSACH = "ten";
    public static final String COLUMN_SOLUONG = "soLuong";
    public static final String COLUMN_NAMXB = "namXB";
    public static final String COLUMN_TTS = "thongTinSach";

    // Table: Sach
    public static final String TABLE_TACGIA = "TacGia";
    public static final String COLUMN_TACGIAID = "maTacGia";
    public static final String COLUMN_TENTACGIA = "tenTacGia";
    public static final String COLUMN_NAMSINH = "namSinh";
    public static final String COLUMN_INFO = "thongTin";

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
                COLUMN_TACGIAID + " INTEGER NOT NULL," +
                COLUMN_THELOAIID + " INTEGER NOT NULL, " +
                COLUMN_SOLUONG + " INTEGER NOT NULL,"+
                COLUMN_TTS + " TEXT NOT NULL,"+
                "FOREIGN KEY("+COLUMN_TACGIAID+")REFERENCES " +TABLE_TACGIA+"("+COLUMN_TACGIAID+"),"
                +
                "FOREIGN KEY("+COLUMN_THELOAIID+")REFERENCES " +TABLE_THELOAI+"("+COLUMN_THELOAIID+"))";
        db.execSQL(CREATE_SACH_TABLE);

        // Create table tacgia
        String CREATE_TACGIA_TABLE = "CREATE TABLE " + TABLE_TACGIA + " (" +
                COLUMN_TACGIAID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TENTACGIA + " TEXT NOT NULL, " +
                COLUMN_INFO + " TEXT, " +
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
    public boolean insertSach(String name, int soLuong  , int tacgia,int theLoai,String info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENSACH, name);
        values.put(COLUMN_SOLUONG, soLuong);
        values.put(COLUMN_TACGIAID, tacgia);
        values.put(COLUMN_THELOAIID, theLoai);
        values.put(COLUMN_TTS,info);

        long result = db.insert(TABLE_SACH, null, values);
        db.close();
        return result != -1; // Nếu kết quả là -1, việc chèn đã thất bại.
    }
    //update Sách
    public boolean updateSach(int sachId,String name, int soLuong  , int tacgia,int theLoai,String info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENSACH, name);
        values.put(COLUMN_SOLUONG, soLuong);
        values.put(COLUMN_TACGIAID, tacgia);
        values.put(COLUMN_THELOAIID, theLoai);
        values.put(COLUMN_TTS,info);

        // Cập nhật dựa trên maSach
        int result = db.update(TABLE_SACH, values, COLUMN_SACHID + " = ?", new String[]{String.valueOf(sachId)});
        db.close();

        return result > 0; // Nếu result > 0, có ít nhất một bản ghi đã được cập nhật.
    }

    //them TACGIA
    public boolean insertTacGia(String name, String namsinh,String info ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENTACGIA, name);
        values.put(COLUMN_NAMSINH, namsinh);
        values.put(COLUMN_INFO, info);

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
    public int getBooksCountByGenre(String genreName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM Sach WHERE maTheLoai = ?";
        Cursor cursor = db.rawQuery(query, new String[]{genreName});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0); // Lấy số lượng sách
        }
        cursor.close();
        return count;
    }
    public int countSoLuong(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM "+tableName;
        Cursor cursor = db.rawQuery(query,null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public List<String> getAllTacGiaNames() {
        List<String> tacGiaNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT tenTacGia FROM TacGia", null);

        if (cursor.moveToFirst()) {
            do {
                tacGiaNames.add(cursor.getString(0)); // Lấy tên tác giả
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tacGiaNames;
    }

    public List<String> getAllTheLoaiNames() {
        List<String> theLoaiNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT tenTheLoai FROM TheLoai", null);

        if (cursor.moveToFirst()) {
            do {
                theLoaiNames.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return theLoaiNames;
    }

    // Lấy ID từ tên tác giả
    public Integer getTacGiaID(String tenTacGia) {
        SQLiteDatabase db = this.getReadableDatabase();
        Integer tacGiaID = null;
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_TACGIAID + " FROM " + TABLE_TACGIA +
                        " WHERE " + COLUMN_TENTACGIA + " = ?",
                new String[]{tenTacGia});
        if (cursor.moveToFirst()) {
            tacGiaID = cursor.getInt(0); // Lấy giá trị ID từ cột đầu tiên
        }
        cursor.close();
        db.close();
        return tacGiaID;
    }
    public String getTacGia(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String tacGia = "";
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_TENTACGIA + " FROM " + TABLE_TACGIA +
                        " WHERE " + COLUMN_TACGIAID + " = ?",
                new String[]{id});
        if (cursor.moveToFirst()) {
            tacGia = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return tacGia;
    }

    // Lấy ID từ tên thể loại
    public Integer getTheLoaiID(String tenTheLoai) {
        SQLiteDatabase db = this.getReadableDatabase();
        Integer theLoaiID = null;
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_THELOAIID + " FROM " + TABLE_THELOAI +
                        " WHERE " + COLUMN_TENTHELOAI + " = ?",
                new String[]{tenTheLoai});
        if (cursor.moveToFirst()) {
            theLoaiID = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return theLoaiID;
    }
    public String getTheLoai(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String theLoai = "";
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_TENTHELOAI  + " FROM " + TABLE_THELOAI +
                        " WHERE " + COLUMN_THELOAIID + " = ?",
                new String[]{id});
        if (cursor.moveToFirst()) {
            theLoai = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return theLoai;
    }

}
