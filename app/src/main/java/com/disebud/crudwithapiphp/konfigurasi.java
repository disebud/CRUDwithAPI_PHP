package com.disebud.crudwithapiphp;


public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://10.0.2.2/penjualan/tambahProduk.php";
    public static final String URL_GET_ALL = "http://10.0.2.2/penjualan/tampilSemuaProduk.php";
//    public static final String URL_GET_ALL = "http://192.168.42.180/penjualan/tampilSemuaProduk.php";
    public static final String URL_GET_BRG = "http://10.0.2.2/penjualan/tampilProduk.php?id=";
    public static final String URL_UPDATE_BRG = "http://10.0.2.2/penjualan/updateProduk.php";
    public static final String URL_DELETE_BRG = "http://10.0.2.2/penjualan/hapusProduk.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_BRG_ID = "idProduk";
    public static final String KEY_BRG_KD = "kdProduk";
    public static final String KEY_BRG_NAMA = "namaProduk";
    public static final String KEY_BRG_HARGA = "harga"; //desg itu variabel untuk posisi

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "idProduk";
    public static final String TAG_KD = "kdProduk";
    public static final String TAG_NAMA = "namaProduk";
    public static final String TAG_HARGA = "harga";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String BRG_ID = "brg_id";
}
