package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminListGedung extends AppCompatActivity {

    Button Tombol, Tombol2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_gedung);

        Tombol = (Button) findViewById(R.id.btnTambahGedung);
        Tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(AdminListGedung.this,AdminTambah.class);
                startActivity(pindah);
            }
        });

        Tombol2 = (Button) findViewById(R.id.btnLogoutAdmin);
        Tombol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(AdminListGedung.this,Login.class);
                startActivity(pindah);
            }
        });
    }
}
