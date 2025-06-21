package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pasien.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "data_pasien";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabel pasien
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nama TEXT, email TEXT, alamat TEXT, jeniskelamin TEXT, " +
                        "goldarah TEXT, riwayatpenyakit TEXT, gejala TEXT, tanggal TEXT)"
        );

        // Tabel user login
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "username TEXT UNIQUE, " +
                        "password TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // ------------------ FUNGSI PASIEN ------------------ //

    public boolean insertData(String nama, String email, String alamat, String jeniskelamin,
                              String goldarah, String riwayatpenyakit, String gejala, String tanggal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", nama);
        values.put("email", email);
        values.put("alamat", alamat);
        values.put("jeniskelamin", jeniskelamin);
        values.put("goldarah", goldarah);
        values.put("riwayatpenyakit", riwayatpenyakit);
        values.put("gejala", gejala);
        values.put("tanggal", tanggal);

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean updateData(String id, String nama, String email, String alamat, String jeniskelamin,
                              String goldarah, String riwayatpenyakit, String gejala, String tanggal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", nama);
        values.put("email", email);
        values.put("alamat", alamat);
        values.put("jeniskelamin", jeniskelamin);
        values.put("goldarah", goldarah);
        values.put("riwayatpenyakit", riwayatpenyakit);
        values.put("gejala", gejala);
        values.put("tanggal", tanggal);

        int result = db.update(TABLE_NAME, values, "id=?", new String[]{id});
        return result > 0;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{id});
    }

    // ------------------ FUNGSI LOGIN & REGISTER ------------------ //

    // Fungsi untuk insert user baru saat register
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("users", null, values);
        return result != -1;
    }

    // Fungsi untuk cek login
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE username = ? AND password = ?",
                new String[]{username, password}
        );
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }
}
