package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminDetailGedung extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText EditNamaGedung, EditAlamat, EditHarga, EditLuas, EditDayaTampung;
    private Button btnDelete;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_gedung);

        Intent fromList = getIntent();
        id = fromList.getIntExtra("id",0);

        db = new DatabaseHelper(this);

        EditNamaGedung = (EditText) findViewById(R.id.EditNamaGedung);
        EditAlamat = (EditText) findViewById(R.id.EditAlamat);
        EditHarga = (EditText) findViewById(R.id.EditHarga);
        EditLuas = (EditText) findViewById(R.id.EditLuas);
        EditDayaTampung = (EditText) findViewById(R.id.EditDayaTampung);
        btnDelete = (Button) findViewById(R.id.btnHapus);

        loadDetailGedung();
        deleteAction();
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

    public void deleteAction(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gedung gedung = Gedung.generateDeleteObject(id);
                new ApiConnect(AdminDetailGedung.this, gedung).execute(ApiConnect.DELETE_ACTION+"");
            }
        });
    }
}
