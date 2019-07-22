package com.gmail.aprizalabyan.nilaisiswa;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TampilNilai extends AppCompatActivity {

    MySQLHelper dbHelper;
    private ListView listNilai;
    private ArrayList<String> ListData;

    ArrayList<String> mapel;
    ArrayList<String> nilai;

    private String id_siswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_nilai);

        getSupportActionBar().setTitle("Daftar Nilai");
        listNilai = findViewById(R.id.listNilai);
        //ListData = new ArrayList<>();
        mapel = new ArrayList<>();
        nilai = new ArrayList<>();
        id_siswa = getIntent().getStringExtra("id_siswa");

        dbHelper = new MySQLHelper(getBaseContext());
        getData();

        ListNilaiAdapter adapter = new ListNilaiAdapter(this, mapel, nilai);
        listNilai.setAdapter(adapter);
    }

    private void getData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        /*
        id_siswa = db.execSQL("SELECT id_siswa FROM "+ MySQLHelper.tbsiswa
                +" where username='" +username+ "' and password='"+password+"'", null);


        Cursor cursor2 = db.rawQuery("SELECT * FROM "+ MySQLHelper.tbnilai
                +" where id_siswa='"+ id_siswa +"'",null);
        */
        Cursor cursor2 = db.rawQuery("SELECT * FROM "+ MySQLHelper.tbnilai,null);

        cursor2.moveToFirst();

        if (cursor2.moveToFirst()) {
            do {
                mapel.add(cursor2.getString(cursor2.getColumnIndex(MySQLHelper.mapel)));
                nilai.add(cursor2.getString(cursor2.getColumnIndex(MySQLHelper.nilai)));
            } while (cursor2.moveToNext());
        }
        /*
        for(int count=0; count < cursor.getCount(); count++){
            cursor.moveToPosition(count);

            mapel.add(cursor.getString(cursor.getColumnIndex(MySQLHelper.mapel)));
            nilai.add(cursor.getString(cursor.getColumnIndex(MySQLHelper.nilai)));
        }*/

        cursor2.close();
    }


}
