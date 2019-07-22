package com.gmail.aprizalabyan.nilaisiswa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MySQLHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "nilaisiswa.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String tbsiswa = "siswa";
    public static final String tbnilai = "nilai";


    // Columns siswa
    public static final String id_siswa = "_idsiswa";
    public static final String nama = "nama";
    public static final String password = "password";

    // Columns nilai
    public static final String id_mapel = "_idmapel";
    public static final String mapel = "mapel";
    public static final String nilai = "nilai";

    public MySQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlsiswa = "create table " + tbsiswa + "(_id integer primary key autoincrement,"
                + id_siswa  + " text not null, "
                + nama + " text not null, "
                + password + " text not null);";
        Log.d("tbsiswa", "onCreate: " + sqlsiswa);
        db.execSQL(sqlsiswa);

        String sqlnilai = "create table " + tbnilai + "("+ id_mapel + " integer primary key, "
                + mapel + " text not null, "
                + nilai + " integer not null,"
                + id_siswa + " text,"
                + " foreign key('" + id_siswa + "') references "+ tbsiswa +"('" + id_siswa + "'));";
        Log.d("tbnilai", "onCreate: " + sqlnilai);
        db.execSQL(sqlnilai);

        String sqlMapel = "insert into "+ tbnilai +" (_idmapel, mapel, nilai) values " +
                "(01, '" + "Bahasa Indonesia" + "', 0)," +
                "(02, '" + "Bahasa Inggris" + "', 0)," +
                "(03, '" + "Ilmu Pengetahuan Alam" + "', 0)," +
                "(04, '" + "Ilmu Pengetahuan Sosial" + "', 0)," +
                "(05, '" + "Agama" + "', 0)," +
                "(06, '" + "Matematika" + "', 0)," +
                "(07, '" + "PPKN" + "', 0)," +
                "(08, '" + "Penjaskes" + "', 0);";
        Log.d("mapel", "onCreate: " + sqlMapel);
        db.execSQL(sqlMapel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

    public List<String> getMapel(){
        List<String> Mapels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + tbnilai;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mapels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return Mapels;
    }
}
