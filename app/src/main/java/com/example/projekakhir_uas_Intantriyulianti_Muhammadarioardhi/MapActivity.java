package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Koordinat UNPAM Viktor (cek di Google Maps: -6.347472, 106.729133)
        double latitude = -6.347472;
        double longitude = 106.729133;

        // Buka lokasi di Google Maps dengan nama tempat
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(UNPAM+Viktor)";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps"); // pastikan pakai Google Maps

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Jika Google Maps tidak tersedia, buka lewat browser
            String browserUri = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(browserUri)));
        }

        finish(); // tutup activity setelah membuka maps
    }
}
