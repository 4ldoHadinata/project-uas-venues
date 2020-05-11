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
    EditText InputNamaGedung, InputAlamat, InputHarga, InputLuas, InputDayaTampung, InputKontak;
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
        InputKontak = (EditText) findViewById(R.id.InputKontak);

        addAction();
    }

    public void addAction() {
        Tombol.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gedung gedung = Gedung.generateInsertObject(InputNamaGedung.getText().toString(),
                                InputAlamat.getText().toString(),
                                Integer.parseInt(InputHarga.getText().toString()),
                                Integer.parseInt(InputLuas.getText().toString()),
                                Integer.parseInt(InputDayaTampung.getText().toString()),
                                InputKontak.getText().toString()
                        );
                        new ApiConnect(AdminTambah.this,gedung).execute(ApiConnect.INSERT_ACTION+"");
                    }
                }
        );
    }

}
