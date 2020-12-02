package com.disebud.crudwithapiphp.barcode.produk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.disebud.crudwithapiphp.DataHelper;
import com.disebud.crudwithapiphp.R;


public class LihatProduk extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnKembali;
    EditText ned1, ned2, ned3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_produk);
        dbHelper = new DataHelper(this);

        ned1 = (EditText) findViewById(R.id.et_kode_lihatProduk);
        ned2 = (EditText) findViewById(R.id.et_nama_lihatProduk);
        ned3 = (EditText) findViewById(R.id.et_harga_lihatProduk);

        btnKembali = (Button) findViewById(R.id.button_kembali_lihatProduk);


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM produk WHERE kd_produk = '" +
                getIntent().getStringExtra("kdProduk") + "'" , null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            ned1.setText(cursor.getString(1).toString());
            ned2.setText(cursor.getString(2).toString());
            ned3.setText(cursor.getString(3).toString());
        }

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        } );
    }
}