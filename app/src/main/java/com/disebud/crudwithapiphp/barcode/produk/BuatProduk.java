package com.disebud.crudwithapiphp.barcode.produk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.disebud.crudwithapiphp.DataHelper;
import com.disebud.crudwithapiphp.R;
import com.disebud.crudwithapiphp.RequestHandler;
import com.disebud.crudwithapiphp.barcode.ListScan;
import com.disebud.crudwithapiphp.konfigurasi;

import java.util.HashMap;


public class BuatProduk extends AppCompatActivity implements View.OnClickListener {


    Button btn1, btn2;
    EditText text1, text2, text3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_produk);

        text1 = (EditText) findViewById(R.id.et_kode_buatProduk);
        text2 = (EditText) findViewById(R.id.et_nama_buatProduk);
        text3 = (EditText) findViewById(R.id.et_harga_buatProduk);

        btn1 = (Button) findViewById(R.id.button_save_buatProduk);
        btn2 = (Button) findViewById(R.id.button_kembali_buatProduk);

        //Setting listeners to button
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }


    private void addProduk(){

        final String kdProduk = text1.getText().toString().trim();
        final String namaProduk = text2.getText().toString().trim();
        final String harga = text3.getText().toString().trim();

        class AddProduk extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(BuatProduk.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(BuatProduk.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_BRG_KD,kdProduk);
                params.put(konfigurasi.KEY_BRG_NAMA,namaProduk);
                params.put(konfigurasi.KEY_BRG_HARGA,harga);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddProduk ap = new AddProduk();
        ap.execute();
    }


    @Override
    public void onClick(View v) {
        if(v == btn1){
            addProduk();
            startActivity(new Intent(this,ActivityProduk.class));
        }

        if(v == btn2){
            startActivity(new Intent(this, ActivityProduk.class));
        }
    }
}

