package com.gmail.aprizalabyan.nilaisiswa;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class InputSiswa extends AppCompatActivity  {

    MySQLHelper dbHelper;

    private EditText idSiswa;
    private EditText namaSiswa;
    private EditText password;
    ListView listSiswa;

    protected Cursor cursor;
    private int id = -1;
    protected ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_siswa);
        getSupportActionBar().setTitle("Input Siswa");

        this.idSiswa = (EditText) this.findViewById(R.id.inputIDSiswa);
        this.namaSiswa = (EditText) this.findViewById(R.id.inputNamaSiswa);
        this.password = (EditText) this.findViewById(R.id.inputPassSiswa);
        this.listSiswa = this.findViewById(R.id.ListViewSiswa);

        dbHelper = new MySQLHelper(this);
        listSiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM "+MySQLHelper.tbsiswa, null);
                cursor.moveToPosition(arg2);
                idSiswa.setText(cursor.getString(1));
                namaSiswa.setText(cursor.getString(2));
                password.setText(cursor.getString(3));
                id = cursor.getInt(0);
            }
        });
        view();
    }

    public void btnInputSiswa(View v) {
        String a = String.valueOf(idSiswa);
        String b = String.valueOf(namaSiswa);
        String c = String.valueOf(password);
        idSiswa.setText(idSiswa.getText().toString());
        namaSiswa.setText(namaSiswa.getText().toString());
        password.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("insert into " + MySQLHelper.tbsiswa + " values" +
                    "(null,'" + idSiswa + "','" + namaSiswa + "','" + password + "')");

            Toast.makeText(InputSiswa.this,"Berhasil input siswa", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            idSiswa.setText(e.toString());
        }
        view();
    }

    private void view() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            cursor = db.rawQuery("SELECT * FROM " + MySQLHelper.tbsiswa, null);
            adapter = new SimpleCursorAdapter(
                    this, R.layout.view2,
                    cursor, new String[]{"_idsiswa", "nama"},
                    new int[]{R.id.id_siswa, R.id.nama_siswa});


            listSiswa.setAdapter(adapter);
        } catch (Exception e) {
            idSiswa.setText(e.toString());
        }
    }
}
