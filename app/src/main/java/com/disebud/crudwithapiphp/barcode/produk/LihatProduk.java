package com.disebud.crudwithapiphp.barcode.produk;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.disebud.crudwithapiphp.R;
import com.disebud.crudwithapiphp.RequestHandler;
import com.disebud.crudwithapiphp.barcode.ListScan;
import com.disebud.crudwithapiphp.konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LihatProduk extends AppCompatActivity implements View.OnClickListener{



    Button btnKembali,btnUpdate,btnDelete;
    EditText ned1, ned2, ned3;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_produk);

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.BRG_ID);

        ned1 = (EditText) findViewById(R.id.et_kode_lihatProduk);
        ned2 = (EditText) findViewById(R.id.et_nama_lihatProduk);
        ned3 = (EditText) findViewById(R.id.et_harga_lihatProduk);

        btnKembali = (Button) findViewById(R.id.button_kembali_lihatProduk);
        btnUpdate = (Button) findViewById(R.id.button_update_lihatProduk);
        btnDelete = (Button) findViewById(R.id.button_hapus_lihatProduk);
        btnKembali.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


        getProduk();
    }

    private void getProduk(){
        class GetProduk extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatProduk.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showBarang(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_BRG,id);
                return s;
            }
        }
        GetProduk gp = new GetProduk();
        gp.execute();
    }

    private void showBarang(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String kdProduk = c.getString(konfigurasi.TAG_KD);
            String namaProduk = c.getString(konfigurasi.TAG_NAMA);
            String harga = c.getString(konfigurasi.TAG_HARGA);

            ned1.setText(kdProduk);
            ned2.setText(namaProduk);
            ned3.setText(harga);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateProduk(){

        final String kdProduk = ned1.getText().toString().trim();
        final String namaProduk = ned2.getText().toString().trim();
        final String harga = ned3.getText().toString().trim();
//        final Integer harga = Integer.parseInt(ned3.getText().toString().trim());

        class updateProduk extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatProduk.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatProduk.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_BRG_ID,id);
                hashMap.put(konfigurasi.KEY_BRG_KD,kdProduk);
                hashMap.put(konfigurasi.KEY_BRG_NAMA,namaProduk);
                hashMap.put(konfigurasi.KEY_BRG_HARGA,harga);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_BRG,hashMap);

                return s;
            }
        }

        updateProduk up = new updateProduk();
        up.execute();
    }

    private void deleteProduk(){
        class DeleteProduk extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatProduk.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatProduk.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                Intent intent = getIntent();
                id = intent.getStringExtra(konfigurasi.BRG_ID);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_BRG, id);
                return s;
            }
        }

        DeleteProduk dp = new DeleteProduk();
        dp.execute();
    }

    private void confirmDeleteProduk(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Pegawai ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteProduk();
                        startActivity(new Intent(LihatProduk.this,ActivityProduk.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View v) {
        if(v == btnKembali){
            startActivity(new Intent(this, ActivityProduk.class));
        }

        if(v == btnUpdate){
            updateProduk();
            startActivity(new Intent(this, ActivityProduk.class));
        }

        if(v == btnDelete){
            confirmDeleteProduk();

        }
    }
}

//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.disebud.crudwithapiphp.DataHelper;
//import com.disebud.crudwithapiphp.R;
//
//
//public class LihatProduk extends AppCompatActivity {
//
//    protected Cursor cursor;
//    DataHelper dbHelper;
//    Button btnKembali;
//    EditText ned1, ned2, ned3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lihat_produk);
//        dbHelper = new DataHelper(this);
//
//        ned1 = (EditText) findViewById(R.id.et_kode_lihatProduk);
//        ned2 = (EditText) findViewById(R.id.et_nama_lihatProduk);
//        ned3 = (EditText) findViewById(R.id.et_harga_lihatProduk);
//
//        btnKembali = (Button) findViewById(R.id.button_kembali_lihatProduk);
//
//
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM produk WHERE kd_produk = '" +
//                getIntent().getStringExtra("kdProduk") + "'" , null);
//        cursor.moveToFirst();
//        if (cursor.getCount() > 0) {
//            cursor.moveToPosition(0);
//            ned1.setText(cursor.getString(1).toString());
//            ned2.setText(cursor.getString(2).toString());
//            ned3.setText(cursor.getString(3).toString());
//        }
//
//        btnKembali.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                finish();
//            }
//        } );
//    }
//}