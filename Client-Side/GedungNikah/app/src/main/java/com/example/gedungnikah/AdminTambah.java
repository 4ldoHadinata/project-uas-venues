package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminTambah extends AppCompatActivity {

    DatabaseHelper dbhelper;
    EditText InputNamaGedung, InputAlamat, InputHarga, InputLuas, InputDayaTampung;
    Button Tombol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tambah);

        dbhelper = new DatabaseHelper(this);
        Tombol = (Button) findViewById(R.id.btnSubmit);
        InputNamaGedung = (EditText) findViewById(R.id.InputNamaGedung);
        InputAlamat = (EditText) findViewById(R.id.InputAlamat);
        InputHarga = (EditText) findViewById(R.id.InputHarga);
        InputLuas = (EditText) findViewById(R.id.InputLuas);
        InputDayaTampung = (EditText) findViewById(R.id.InputDayaTampung);

    }

    public void addAction() {
        Tombol.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = dbhelper.insertData(InputNamaGedung.getText().toString(),
                                InputAlamat.getText().toString(),
                                Integer.parseInt(InputHarga.getText().toString()),
                                Integer.parseInt(InputLuas.getText().toString()),
                                Integer.parseInt(InputDayaTampung.getText().toString()) );
                        if(isInserted == true) {
                            Toast.makeText(AdminTambah.this,
                                    "Data Berhasil Ditambahkan",Toast.LENGTH_LONG).show(); }
                        else {
                            Toast.makeText(AdminTambah.this,
                                    "Data Gagal Ditambahkan",Toast.LENGTH_LONG).show(); }
//                        Intent pindah = new Intent(AdminTambah.this,AdminListGedung.class);
//                        startActivity(pindah);
                    }
                }
        );
    }

}
