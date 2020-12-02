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


public class UpdateProduk extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn1, btn2;
    EditText text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_produk);
        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.et_kode_updateProduk);
        text2 = (EditText) findViewById(R.id.et_nama_updateProduk);
        text3 = (EditText) findViewById(R.id.et_harga_updateProduk);

        btn1 = (Button) findViewById(R.id.button_save_updateProduk);
        btn2 = (Button) findViewById(R.id.button_kembali_updateProduk);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM produk WHERE kd_produk  = '" +
                getIntent().getStringExtra("kdProduk")+"'" , null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(3));
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL(" update produk set nama_produk ='" + text2.getText().toString() + "', " +
                        "harga = " + Integer.parseInt(text3.getText().toString()) +
                        " where kd_produk = " + Integer.parseInt(text1.getText().toString()) + "");
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
