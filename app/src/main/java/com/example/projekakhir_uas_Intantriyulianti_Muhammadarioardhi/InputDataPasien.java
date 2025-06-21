package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.*;

public class InputDataPasien extends AppCompatActivity {

    DatabaseHelper db;
    EditText etNama, etEmail, etAlamat, etRiwayat, etGejala, etTanggal;
    RadioGroup rgJenisKelamin;
    RadioButton rbSelected;
    Spinner spGolDarah;
    Button btnSave;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_pasien);

        db = new DatabaseHelper(this);

        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etAlamat = findViewById(R.id.etAlamat);
        etRiwayat = findViewById(R.id.etRiwayat);
        etGejala = findViewById(R.id.etGejala);
        etTanggal = findViewById(R.id.etTanggal);
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin);
        spGolDarah = findViewById(R.id.spGolDarah);
        btnSave = findViewById(R.id.btnSave);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.golongan_darah_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGolDarah.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ID")) {
            id = intent.getStringExtra("ID");
            etNama.setText(intent.getStringExtra("NAMA"));
            etEmail.setText(intent.getStringExtra("EMAIL"));
            etAlamat.setText(intent.getStringExtra("ALAMAT"));
            etRiwayat.setText(intent.getStringExtra("RIWAYAT"));
            etGejala.setText(intent.getStringExtra("GEJALA"));
            etTanggal.setText(intent.getStringExtra("TANGGAL"));

            String jk = intent.getStringExtra("JENISKELAMIN");
            if (jk != null) {
                if (jk.equalsIgnoreCase("Laki-laki")) {
                    rgJenisKelamin.check(R.id.rbLaki);
                } else if (jk.equalsIgnoreCase("Perempuan")) {
                    rgJenisKelamin.check(R.id.rbPerempuan);
                }
            }

            String goldar = intent.getStringExtra("GOLDAR");
            if (goldar != null) {
                int pos = adapter.getPosition(goldar);
                spGolDarah.setSelection(pos);
            }

            btnSave.setText("Update");
        }

        btnSave.setOnClickListener(view -> {
            String nama = etNama.getText().toString();
            String email = etEmail.getText().toString();
            String alamat = etAlamat.getText().toString();
            String riwayat = etRiwayat.getText().toString();
            String gejala = etGejala.getText().toString();
            String tanggal = etTanggal.getText().toString();

            int selectedId = rgJenisKelamin.getCheckedRadioButtonId();
            rbSelected = findViewById(selectedId);
            String jeniskelamin = rbSelected != null ? rbSelected.getText().toString() : "";

            String goldar = spGolDarah.getSelectedItem().toString();

            if (id.isEmpty()) {
                boolean inserted = db.insertData(nama, email, alamat, jeniskelamin, goldar, riwayat, gejala, tanggal);
                if (inserted) {
                    Toast.makeText(this, "Data disimpan", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                }
            } else {
                boolean updated = db.updateData(id, nama, email, alamat, jeniskelamin, goldar, riwayat, gejala, tanggal);
                if (updated) {
                    Toast.makeText(this, "Data diperbarui", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
