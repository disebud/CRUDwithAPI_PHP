package com.disebud.crudwithapiphp.barcode.produk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.disebud.crudwithapiphp.DataHelper;
import com.disebud.crudwithapiphp.R;


public class BuatProduk extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn1, btn2;
    EditText text1, text2, text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_produk);
        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.et_kode_buatProduk);
        text2 = (EditText) findViewById(R.id.et_nama_buatProduk);
        text3 = (EditText) findViewById(R.id.et_harga_buatProduk);

        btn1 = (Button) findViewById(R.id.button_save_buatProduk);
        btn2 = (Button) findViewById(R.id.button_kembali_buatProduk);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into barang (kd_barang, nama_barang, satuan, harga) values('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "'," +
                        Integer.parseInt(text3.getText().toString()) + ")");
                Toast.makeText(getApplicationContext(), "Berhasil" ,
                        Toast.LENGTH_LONG).show();
                ActivityProduk.ap.RefreshList();
                finish();
            }
        } );
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                finish();
            }
        } );
    }
}


