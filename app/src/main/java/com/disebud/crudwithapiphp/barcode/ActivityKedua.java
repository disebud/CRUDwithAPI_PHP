package com.disebud.crudwithapiphp.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.disebud.crudwithapiphp.R;


public class ActivityKedua extends AppCompatActivity {

    public EditText nhasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kedua);
        //TextView hasilnya = (TextView) findViewById(R.id.textHasil);
        final EditText nhasil = (EditText) findViewById(R.id.editText);
        Bundle bundle = getIntent().getExtras();
        String bhasil = bundle.getString("name");
        nhasil.setText(String.valueOf(bhasil));
        //hasilnya.setText(bhasil);
        Button button = (Button) findViewById(R.id.buttonUtama);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityKedua.this, ListScan.class);
                startActivity(i);
            }
        });
        Button button2 = (Button) findViewById(R.id.btn_cek);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityKedua.this, ActivityKetiga.class);
                intent.putExtra("data1", nhasil.getText().toString());
                startActivity(intent);
            }
        });
    }

}
