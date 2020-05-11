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

        //default fragment
        PengelolaFragment pengelolaFragment = new PengelolaFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, pengelolaFragment)
                .commit();

        Tombol = (Button) findViewById(R.id.btnFragmentPengelola);
        Tombol2 = (Button) findViewById(R.id.btnFragmentAdmin);
        Tombol3 = (Button) findViewById(R.id.btnMoveUser);

        Tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PengelolaFragment pengelolaFragment = new PengelolaFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, pengelolaFragment)
                        .commit();
            }
        });

        Tombol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminFragment adminFragment = new AdminFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, adminFragment)
                        .commit();
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
