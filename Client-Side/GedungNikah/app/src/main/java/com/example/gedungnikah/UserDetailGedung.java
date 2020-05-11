package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserDetailGedung extends AppCompatActivity {
    private DatabaseHelper db;
    private TextView EditNamaGedung, EditAlamat, EditHarga, EditLuas, EditDayaTampung;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_gedung);

        Intent fromList = getIntent();
        id = fromList.getIntExtra("id",0);

        db = new DatabaseHelper(this);
        EditNamaGedung = (TextView) findViewById(R.id.EditNamaGedung);
        EditAlamat = (TextView) findViewById(R.id.EditAlamat);
        EditHarga = (TextView) findViewById(R.id.EditHarga);
        EditLuas = (TextView) findViewById(R.id.EditLuas);
        EditDayaTampung = (TextView) findViewById(R.id.EditDayaTampung);

        loadDetailGedung();
    }

    private void loadDetailGedung(){
        Cursor cursor = db.getDetail(id);
        if (cursor.moveToFirst()){
            EditNamaGedung.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)));
            EditAlamat.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)));
            EditHarga.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_4))));
            EditLuas.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_5))));
            EditDayaTampung.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_6))));
        }
    }
}
