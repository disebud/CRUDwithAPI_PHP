package com.disebud.crudwithapiphp.barcode.produk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.disebud.crudwithapiphp.R;
import com.disebud.crudwithapiphp.RequestHandler;
import com.disebud.crudwithapiphp.barcode.ListScan;
import com.disebud.crudwithapiphp.konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ActivityProduk extends AppCompatActivity implements ListView.OnItemClickListener , View.OnClickListener{

    private ListView listView;

    private String JSON_STRING;

    Button btnBuatProduk,kembaliListScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);
        listView = (ListView) findViewById(R.id.listViewProduk);
        btnBuatProduk = findViewById(R.id.buttonProduk);
        kembaliListScan = findViewById(R.id.button_kembali_listproduk);
        listView.setOnItemClickListener(this);
        btnBuatProduk.setOnClickListener(this);
        kembaliListScan.setOnClickListener(this);
        getJSON();
    }


    private void showProduk(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_ID);
                Log.d("OKEY",id);
                String kd = jo.getString(konfigurasi.TAG_KD);
                Log.d("OKEY2",kd);

                HashMap<String,String> produks = new HashMap<>();
                produks.put(konfigurasi.TAG_ID,id);
                produks.put(konfigurasi.TAG_KD,kd);
                list.add(produks);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ActivityProduk.this, list, R.layout.list_item,
                new String[]{konfigurasi.TAG_ID,konfigurasi.TAG_KD},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ActivityProduk.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showProduk();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, LihatProduk.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String brgId = map.get(konfigurasi.TAG_ID).toString();
        intent.putExtra(konfigurasi.BRG_ID,brgId);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        if(v == btnBuatProduk){
            startActivity(new Intent(this, BuatProduk.class));
        }

        if(v == kembaliListScan){
            startActivity(new Intent(this, ListScan.class));
        }


    }



}

//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.disebud.crudwithapiphp.DataHelper;
//import com.disebud.crudwithapiphp.R;
//
//
//public class ActivityProduk extends AppCompatActivity {
//
//    public static ActivityProduk ap;
//    protected Cursor cursor;
//    String[] daftar;
//    ListView ListView01;
//    Button btn;
//    Menu menu;
//    DataHelper dbcenter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_produk);
//        btn=(Button)findViewById(R.id.buttonProduk);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                Intent inte = new Intent(ActivityProduk.this, BuatProduk.class);
//                startActivity(inte);
//            }
//        } );
//        ap = this;
//        dbcenter = new DataHelper(this);
//        RefreshList();
//    }
//
//    public void RefreshList() {
//        SQLiteDatabase db = dbcenter.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM produk" ,null);
//        daftar = new String[cursor.getCount()];
//        cursor.moveToFirst();
//        for (int cc=0; cc < cursor.getCount(); cc++) {
//            cursor.moveToPosition(cc);
//            daftar[cc] = cursor.getString(1).toString();
//        }
//        ListView01 = (ListView)findViewById(R.id.listViewProduk);
//        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
//        ListView01.setSelected(true);
//        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
//                final String selection = daftar[arg2];
//                final CharSequence[] dialogitem = { "Lihat Data" , "Update Data" , "Hapus Data"} ;
//                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityProduk.this);
//                builder.setTitle("Pilihan");
//                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int item) {
//                        switch(item) {
//                            case 0 :
//                                Intent i = new Intent(getApplicationContext(), LihatProduk.class);
//                                i.putExtra("kdProduk" , selection);
//                                startActivity(i);
//                                break;
//
//
//                            case 1 :
//                                Intent in = new Intent(getApplicationContext(), UpdateProduk.class);
//                                in.putExtra("kdProduk" , selection);
//                                startActivity(in);
//                                break;
//                            case 2 :
//                                SQLiteDatabase db = dbcenter.getWritableDatabase();
//                                db.execSQL("delete from produk where kd_produk = " +selection+ "");
//                                Toast.makeText(getApplicationContext(), "Data "+selection+" Berhasil Dihapus" ,
//                                        Toast.LENGTH_LONG).show();
//                                RefreshList();
//                                break;
//                        }
//                    }
//                } );
//                builder.create().show();
//            }
//        } );
//        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
//    }
//}
//
//
//
