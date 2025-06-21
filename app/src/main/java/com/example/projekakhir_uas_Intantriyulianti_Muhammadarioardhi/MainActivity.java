package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi.Dashboard;
import com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi.DatabaseHelper;
import com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi.R;
import com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        dbHelper = new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Lengkapi semua field", Toast.LENGTH_SHORT).show();
                } else if (dbHelper.checkUser(user, pass)) {
                    Toast.makeText(getApplicationContext(), "Login berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Dashboard.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin mendaftar akun baru?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                            }
                        })
                        .setNegativeButton("Batal", null)
                        .show();
            }
        });
    }
}
