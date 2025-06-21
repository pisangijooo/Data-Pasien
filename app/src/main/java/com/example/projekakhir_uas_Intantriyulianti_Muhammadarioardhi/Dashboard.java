package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private View sensorCard, mapCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Inisialisasi view
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);

        sensorCard = findViewById(R.id.sensor);
        mapCard = findViewById(R.id.map1);

        // Klik tombol menu (navigasi)
        topAppBar.setNavigationOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        // Handle menu navigation drawer
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_dashboard) {
                    // Sudah di dashboard
                } else if (itemId == R.id.nav_tambah_data) {
                    startActivity(new Intent(Dashboard.this, InputDataPasien.class));
                } else if (itemId == R.id.nav_lihat_data) {
                    startActivity(new Intent(Dashboard.this, LihatDataPasien.class));
                } else if (itemId == R.id.nav_pengaturan) {
                    startActivity(new Intent(Dashboard.this, MainActivity.class));
                } else if (itemId == R.id.nav_keluar) {
                    finishAffinity();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Klik card Sensor
        sensorCard.setOnClickListener(v -> {
            startActivity(new Intent(this, SensorActivity.class));
        });

        // Klik card Map
        mapCard.setOnClickListener(v -> {
            startActivity(new Intent(this, MapActivity.class));
        });

        // Klik tombol tambah
        fabAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, InputDataPasien.class));
        });
    }
}
