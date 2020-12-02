package com.disebud.crudwithapiphp.barcode;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.disebud.crudwithapiphp.DataHelper;
import com.disebud.crudwithapiphp.R;


public class ActivityKetiga extends AppCompatActivity {

    protected Cursor cursor, cursor2, cursor3;
    DataHelper dbHelper;
    Button ton2;
    EditText etext1, etext2, etext3, etext4, etext5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketiga);

        dbHelper = new DataHelper(this);
        etext1 = (EditText) findViewById(R.id.ed1);
        etext2 = (EditText) findViewById(R.id.ed2);
        etext3 = (EditText) findViewById(R.id.ed3);
        etext4 = (EditText) findViewById(R.id.ed4);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM produk WHERE kd_produk = '" + getIntent().getStringExtra("data1") + "'",null);
        // cursor = db.rawQuery("SELECT * FROM produk WHERE kd_produk = '998127912370' ",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            etext1.setText(cursor.getString(0).toString());
            etext2.setText(cursor.getString(1).toString());
            etext3.setText(cursor.getString(2).toString());
            etext4.setText(cursor.getString(3).toString());
        }

    }
}