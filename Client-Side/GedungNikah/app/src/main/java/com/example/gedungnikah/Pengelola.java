package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Pengelola extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText EditNamaGedung, EditAlamat, EditHarga, EditLuas, EditDayaTampung, EditKontak;
    private Button btnUpdate;
    private int id;
    Button Tombol, Tombol2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengelola);

        id = 4;

        db = new DatabaseHelper(this);

        EditNamaGedung = (EditText) findViewById(R.id.EditNamaGedung);
        EditAlamat = (EditText) findViewById(R.id.EditAlamat);
        EditHarga = (EditText) findViewById(R.id.EditHarga);
        EditLuas = (EditText) findViewById(R.id.EditLuas);
        EditDayaTampung = (EditText) findViewById(R.id.EditDayaTampung);
        EditKontak = (EditText) findViewById(R.id.EditKontak);
        btnUpdate = (Button) findViewById(R.id.btnPerbarui);

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

        loadDetailGedung();
        editAction();
    }

    private void loadDetailGedung(){
        Cursor cursor = db.getDetail(id);
        if (cursor.moveToFirst()){
            EditNamaGedung.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)));
            EditAlamat.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)));
            EditHarga.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_4))));
            EditLuas.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_5))));
            EditDayaTampung.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_6))));
            EditKontak.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_7)));
        }
    }

    public void editAction(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gedung gedung = Gedung.generateUpdateObject(id,
                        EditNamaGedung.getText().toString(),
                        EditAlamat.getText().toString(),
                        Integer.parseInt(EditHarga.getText().toString()),
                        Integer.parseInt(EditLuas.getText().toString()),
                        Integer.parseInt(EditDayaTampung.getText().toString()),
                        EditKontak.getText().toString()
                );
                new ApiConnect(Pengelola.this,gedung).execute(ApiConnect.UPDATE_ACTION+"");
            }
        });
    }

}
