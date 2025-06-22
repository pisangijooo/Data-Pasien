package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Welcome extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            startActivity(new Intent(Activity_Welcome.this, ActivityLogin.class));
        });
    }
}
