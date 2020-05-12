package com.example.gedungnikah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserListGedung extends AppCompatActivity implements GedungAdapter.customButtonListener {
    public static DatabaseHelper db;
    private ListView listViewGedung;
    private List<Gedung> gedungs;
    private GedungAdapter gedungAdapter;
    private SwipeRefreshLayout swipeRefresh;
    private int status;
    Button Tombol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_gedung);

        SharedPreferences sharedPreferences = getSharedPreferences("cekLogin", Context.MODE_PRIVATE);
        status = sharedPreferences.getInt("status",0);
        if (status != 0) {
            Intent pindah = new Intent(UserListGedung.this, AdminListGedung.class);
            startActivity(pindah);
        }

        db = new DatabaseHelper(this);
        gedungs = new ArrayList<>();
        listViewGedung = (ListView) findViewById(R.id.activity_list_gedung);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        loadGedung();
        sync();

        Tombol = (Button) findViewById(R.id.btnMoveLogin);
        Tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(UserListGedung.this,Login.class);
                startActivity(pindah);
            }
        });
    }

    @Override
    public void onButtonClickListener(int position, int id) {
        Intent toDetail = new Intent(UserListGedung.this, UserDetailGedung.class);
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
        gedungAdapter.setCustomButtonListener(UserListGedung.this);
        listViewGedung.setAdapter(gedungAdapter);
    }

    public void sync(){
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ApiConnect(UserListGedung.this).execute(ApiConnect.SYNC_ACTION+"");
                loadGedung();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

}
