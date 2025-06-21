package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Welcome extends AppCompatActivity {
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome); // pastikan layout-nya ada

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(view -> {
            startActivity(new Intent(Activity_Welcome.this, ActivityLogin.class));
        });

        btnRegister.setOnClickListener(view -> {
            startActivity(new Intent(Activity_Welcome.this, RegisterActivity.class));
        });
    }
}
