package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pengelola extends AppCompatActivity {

    Button Tombol, Tombol2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengelola);

        Tombol = (Button) findViewById(R.id.btnLihatSewa);
        Tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Pengelola.this,PengelolaListJadwal.class);
                startActivity(pindah);
            }
        });

        Tombol2 = (Button) findViewById(R.id.btnLogoutPengelola);
        Tombol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Pengelola.this,Login.class);
                startActivity(pindah);
            }
        });
    }
}
