package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminListGedung extends AppCompatActivity implements GedungAdapter.customButtonListener {
    public static DatabaseHelper db;
    private ListView listViewGedung;
    private List<Gedung> gedungs;
    private GedungAdapter gedungAdapter;
    private Button Tombol, Tombol2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_gedung);

        db = new DatabaseHelper(this);
        gedungs = new ArrayList<>();
        listViewGedung = (ListView) findViewById(R.id.activity_list_gedung);

        loadGedung();

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

    @Override
    public void onButtonClickListener(int position, int id) {
        Intent toDetail = new Intent(AdminListGedung.this, AdminDetailGedung.class);
        toDetail.putExtra("id",id);
        startActivity(toDetail);
    }

    private void loadGedung(){
        gedungs.clear();
        Cursor cursor = db.getAllDataDetail();
        if (cursor.moveToFirst()){
            do{
                Gedung gedung = new Gedung(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_4)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_5)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_6)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_7))
                );
                gedungs.add(gedung);
            } while (cursor.moveToNext());
        }

        gedungAdapter = new GedungAdapter(this, R.layout.activity_list_gedung, gedungs);
        gedungAdapter.setCustomButtonListener(AdminListGedung.this);
        listViewGedung.setAdapter(gedungAdapter);
    }

}
