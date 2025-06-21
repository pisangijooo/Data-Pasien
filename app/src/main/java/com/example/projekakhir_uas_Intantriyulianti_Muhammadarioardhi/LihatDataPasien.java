package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class LihatDataPasien extends AppCompatActivity {

    DatabaseHelper db;
    ListView listView;
    ArrayList<pasien> pasienList = new ArrayList<>();
    PasienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data_pasien);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);

        adapter = new PasienAdapter(this, pasienList);
        listView.setAdapter(adapter);

        loadData(); // Panggil pertama kali
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // Panggil ulang saat kembali dari InputDataPasien
    }

    private void loadData() {
        pasienList.clear();
        Cursor cursor = db.getAllData();
        if (cursor.moveToFirst()) {
            do {
                pasien p = new pasien(
                        cursor.getString(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("nama")),
                        cursor.getString(cursor.getColumnIndexOrThrow("email")),
                        cursor.getString(cursor.getColumnIndexOrThrow("alamat")),
                        cursor.getString(cursor.getColumnIndexOrThrow("jeniskelamin")),
                        cursor.getString(cursor.getColumnIndexOrThrow("goldarah")),
                        cursor.getString(cursor.getColumnIndexOrThrow("riwayatpenyakit")),
                        cursor.getString(cursor.getColumnIndexOrThrow("gejala")),
                        cursor.getString(cursor.getColumnIndexOrThrow("tanggal"))
                );
                pasienList.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged(); // Refresh tampilan list
    }
}
