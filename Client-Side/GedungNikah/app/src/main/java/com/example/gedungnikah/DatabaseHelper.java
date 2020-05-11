package com.example.gedungnikah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gedung.db";

    public static final String TABLE_DETAIL = "detail_gedung";
    public static final String TABLE_LIST = "list_gedung";

    public static final String COL_ID = "id_gedung";

    public static final String COL_2 = "nama_gedung";
    public static final String COL_3 = "alamat_gedung";
    public static final String COL_4 = "harga_sewa_gedung";
    public static final String COL_5 = "luas_gedung";
    public static final String COL_6 = "daya_tampung";
    public static final String COL_7 = "kontak";

    public static final String COL_LIST_2 = "mulai_sewa";
    public static final String COL_LIST_3 = "selesai_sewa";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_DETAIL +" (id_gedung INTEGER,nama_gedung TEXT,alamat_gedung TEXT,harga_sewa_gedung INTEGER, luas_gedung INTEGER, daya_tampung INTEGER, kontak TEXT)");
        db.execSQL("create table " + TABLE_LIST + " (id_gedung INTEGER, mulai_sewa DATETIME, selesai_sewa DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LIST);
        onCreate(db);
    }

    public boolean insertDataDetail(int id_gedung,String nama_gedung,String alamat_gedung,int harga_sewa_gedung, int luas_gedung, int daya_tampung, String kontak) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID,id_gedung);
        contentValues.put(COL_2,nama_gedung);
        contentValues.put(COL_3,alamat_gedung);
        contentValues.put(COL_4,harga_sewa_gedung);
        contentValues.put(COL_5,luas_gedung);
        contentValues.put(COL_6,daya_tampung);
        contentValues.put(COL_7,kontak);
        long result = db.insert(TABLE_DETAIL,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataList(int id_gedung,String mulai_sewa,String selesai_sewa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID,id_gedung);
        contentValues.put(COL_2,mulai_sewa);
        contentValues.put(COL_3,selesai_sewa);
        long result = db.insert(TABLE_LIST,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataDetail() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_DETAIL ,null);
        return res;
    }

    public Cursor getDetail(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_DETAIL+ " where id_gedung = " +id ,null);
        return res;
    }

    public Cursor getJadwal(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_LIST+ " where id_gedung = "+id ,null);
        return res;
    }

    public void emptyData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DETAIL);
        db.execSQL("DELETE FROM " + TABLE_LIST);
    }

}
