package com.example.gedungnikah;

import org.json.JSONException;
import org.json.JSONObject;

public class Gedung {
    private int id_gedung, harga_sewa_gedung,luas_gedung, daya_tampung, type;
    private String nama_gedung, alamat_gedung, kontak, mulai_sewa, selesai_sewa;
    public static final int INSERT_TYPE=1;
    public static final int UPDATE_TYPE=2;
    public static final int DELETE_TYPE=3;

    public static Gedung generateInsertObject(String nama_gedung, String alamat_gedung, int harga_sewa_gedung, int luas_gedung, int daya_tampung, String kontak){
        return new Gedung(-1,nama_gedung,alamat_gedung,harga_sewa_gedung,luas_gedung,daya_tampung,kontak,Gedung.INSERT_TYPE);
    }

    public static Gedung generateUpdateObject(int id,String nama_gedung, String alamat_gedung, int harga_sewa_gedung, int luas_gedung, int daya_tampung, String kontak){
        return new Gedung(id,nama_gedung,alamat_gedung,harga_sewa_gedung,luas_gedung,daya_tampung,kontak,Gedung.UPDATE_TYPE);
    }

    public static Gedung generateDeleteObject(int id){
        return new Gedung(id,null,null,-1,-1,-1,null,Gedung.DELETE_TYPE);
    }

    public JSONObject getJsonGedung(){
        JSONObject obj = new JSONObject();
        try {
            switch (type) {
                case Gedung.INSERT_TYPE:
                    obj.put("nama_gedung", this.nama_gedung);
                    obj.put("alamat_gedung", this.alamat_gedung);
                    obj.put("harga_sewa_gedung",this.harga_sewa_gedung);
                    obj.put("luas_gedung",this.luas_gedung);
                    obj.put("daya_tampung",this.daya_tampung);
                    obj.put("kontak",this.kontak);
                    break;
                case Gedung.UPDATE_TYPE:
                    obj.put("id_gedung",id_gedung);
                    obj.put("nama_gedung", this.nama_gedung);
                    obj.put("alamat_gedung", this.alamat_gedung);
                    obj.put("harga_sewa_gedung",this.harga_sewa_gedung);
                    obj.put("luas_gedung",this.luas_gedung);
                    obj.put("daya_tampung",this.daya_tampung);
                    obj.put("kontak",this.kontak);
                    break;
                case Gedung.DELETE_TYPE:
                    obj.put("id_gedung",id_gedung);
                    break;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }

    public String getJsonString(){return getJsonGedung().toString();}

    public Gedung(int id_gedung, String nama_gedung, String alamat_gedung, int harga_sewa_gedung, int luas_gedung, int daya_tampung, String kontak) {
        this.id_gedung = id_gedung;
        this.harga_sewa_gedung = harga_sewa_gedung;
        this.luas_gedung = luas_gedung;
        this.daya_tampung = daya_tampung;
        this.nama_gedung = nama_gedung;
        this.alamat_gedung = alamat_gedung;
        this.kontak = kontak;
    }

    public Gedung(int id_gedung, String nama_gedung, String alamat_gedung, int harga_sewa_gedung, int luas_gedung, int daya_tampung, String kontak, int type) {
        this.id_gedung = id_gedung;
        this.harga_sewa_gedung = harga_sewa_gedung;
        this.luas_gedung = luas_gedung;
        this.daya_tampung = daya_tampung;
        this.nama_gedung = nama_gedung;
        this.alamat_gedung = alamat_gedung;
        this.kontak = kontak;
        this.type = type;
    }

    public Gedung(int id_gedung, String mulai_sewa, String selesai_sewa) {
        this.id_gedung = id_gedung;
        this.mulai_sewa = mulai_sewa;
        this.selesai_sewa = selesai_sewa;
    }

    public int getId_gedung() {
        return id_gedung;
    }

    public int getHarga_sewa_gedung() {
        return harga_sewa_gedung;
    }

    public int getLuas_gedung() {
        return luas_gedung;
    }

    public int getDaya_tampung() {
        return daya_tampung;
    }

    public String getNama_gedung() {
        return nama_gedung;
    }

    public String getAlamat_gedung() {
        return alamat_gedung;
    }

    public String getKontak() {
        return kontak;
    }

    public String getMulai_sewa() {
        return mulai_sewa;
    }

    public String getSelesai_sewa() {
        return selesai_sewa;
    }
}
