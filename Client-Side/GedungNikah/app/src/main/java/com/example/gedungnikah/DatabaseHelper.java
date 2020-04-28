package com.example.gedungnikah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gedung.db";
    public static final String TABLE_NAME = "tabel_gedung";
    public static final String COL_1 = "id_gedung";
    public static final String COL_2 = "nama_gedung";
    public static final String COL_3 = "alamat_gedung";
    public static final String COL_4 = "harga_sewa_gedung";
    public static final String COL_5 = "luas_gedung";
    public static final String COL_6 = "daya_tampung";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (id_gedung INTEGER PRIMARY KEY AUTOINCREMENT,nama_gedung TEXT,alamat_gedung TEXT,harga_sewa_gedung INTEGER, luas_gedung INTEGER, daya_tampung INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nama_gedung,String alamat_gedung,int harga_sewa_gedung, int luas_gedung, int daya_tampung) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nama_gedung);
        contentValues.put(COL_3,alamat_gedung);
        contentValues.put(COL_4,harga_sewa_gedung);
        contentValues.put(COL_5,luas_gedung);
        contentValues.put(COL_6,daya_tampung);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String id,String nama_gedung,String alamat_gedung,int harga_sewa_gedung, int luas_gedung, int daya_tampung) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nama_gedung);
        contentValues.put(COL_3,alamat_gedung);
        contentValues.put(COL_4,harga_sewa_gedung);
        contentValues.put(COL_5,luas_gedung);
        contentValues.put(COL_6,daya_tampung);
        int result = db.update(TABLE_NAME, contentValues, "nim = ?",new String[] { id });
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME,"id_gedung = ?" ,new String[] { id });
        if(result == 0)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


}
