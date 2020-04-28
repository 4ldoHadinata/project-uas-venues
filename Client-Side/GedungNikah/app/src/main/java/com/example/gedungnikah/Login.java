package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button Tombol, Tombol2, Tombol3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Tombol = (Button) findViewById(R.id.btnFragmentPengelola);
        Tombol2 = (Button) findViewById(R.id.btnFragmentAdmin);
        Tombol3 = (Button) findViewById(R.id.btnMoveUser);

        Tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Login.this,Pengelola.class);
                startActivity(pindah);
            }
        });

        Tombol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah2 = new Intent(Login.this,AdminListGedung.class);
                startActivity(pindah2);
            }
        });

        Tombol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah2 = new Intent(Login.this,UserListGedung.class);
                startActivity(pindah2);
            }
        });

    }
}
