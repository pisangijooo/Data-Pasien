package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    DatabaseHelper db;
    TextView txtToRegister;
    ImageView btnBackLogin; // ← Tambahan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        db = new DatabaseHelper(this);
        txtToRegister = findViewById(R.id.txtToRegister);
        btnBackLogin = findViewById(R.id.btn_back); // ← Inisialisasi tombol back

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (db.checkUser(username, password)) {
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Dashboard.class));
                finish();
            } else {
                Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
            }
        });

        txtToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityLogin.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Fungsi tombol back
        btnBackLogin.setOnClickListener(v -> {
            finish(); // kembali ke halaman sebelumnya
        });
    }
}
