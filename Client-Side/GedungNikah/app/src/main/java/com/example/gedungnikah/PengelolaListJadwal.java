package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PengelolaListJadwal extends AppCompatActivity {

    Button Tombol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengelola_list_jadwal);

        Tombol = (Button) findViewById(R.id.btnTambahJadwal);
        Tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(PengelolaListJadwal.this,Pengelola.class);
                startActivity(pindah);
            }
        });

    }
}
