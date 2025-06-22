package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText regEmail, regUsername, regPassword;
    Button regButton;
    DatabaseHelper dbHelper;
    ImageView btnBack; // <- Tambahan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi view
        regEmail = findViewById(R.id.reg_email);
        regUsername = findViewById(R.id.reg_username);
        regPassword = findViewById(R.id.reg_password);
        regButton = findViewById(R.id.reg_button);
        btnBack = findViewById(R.id.btn_back); // <- Inisialisasi tombol back

        dbHelper = new DatabaseHelper(this);

        // Fungsi tombol "Register"
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regEmail.getText().toString().trim(); // optional
                String username = regUsername.getText().toString().trim();
                String password = regPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Username dan password wajib diisi", Toast.LENGTH_SHORT).show();
                } else {
                    boolean success = dbHelper.insertUser(username, password);
                    if (success) {
                        Toast.makeText(RegisterActivity.this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                        finish(); // kembali ke halaman sebelumnya
                    } else {
                        Toast.makeText(RegisterActivity.this, "Username sudah digunakan!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Fungsi tombol "Back"
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // kembali ke halaman sebelumnya
            }
        });
    }
}
