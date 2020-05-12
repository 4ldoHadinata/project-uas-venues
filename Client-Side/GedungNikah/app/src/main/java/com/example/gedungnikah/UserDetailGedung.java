package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetailGedung extends AppCompatActivity {
    private DatabaseHelper db;
    private TextView EditNamaGedung, EditAlamat, EditHarga, EditLuas, EditDayaTampung;
    private int id;
    private Button btnHub;
    private String kontak;

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

        btnHub = findViewById(R.id.btnHubungi);
        btnHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone="+kontak;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                Toast.makeText(getApplicationContext(), kontak, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDetailGedung(){
        Cursor cursor = db.getDetail(id);
        if (cursor.moveToFirst()){
            EditNamaGedung.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)));
            EditAlamat.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)));
            EditHarga.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_4))));
            EditLuas.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_5))));
            EditDayaTampung.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_6))));
            kontak = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_7));
        }
    }
}
