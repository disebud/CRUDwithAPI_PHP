package com.disebud.crudwithapiphp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbpenjualan.db" ;
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE produk(id_produk integer primary key autoincrement, kd_produk text, nama_produk text, harga integer)");
        db.execSQL("INSERT INTO produk (kd_produk, nama_produk, harga) VALUES ('8998127912370', 'Dunhil Fine Cut Mild','23000')");
        db.execSQL("INSERT INTO produk (kd_produk, nama_produk, harga) VALUES ('8998127912371', 'Aqua Botol 300ml','2000')");
        db.execSQL("INSERT INTO produk (kd_produk, nama_produk, harga) VALUES ('8998127912372', 'Kopi Susu ABC','1000')");
        db.execSQL("INSERT INTO produk (kd_produk, nama_produk, harga) VALUES ('8998127912372', 'Fine choco 27g','1500')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE produk");
        onCreate(db);
    }
}
