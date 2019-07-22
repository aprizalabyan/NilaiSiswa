package com.gmail.aprizalabyan.nilaisiswa;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class InputNilai extends AppCompatActivity {

    Spinner spinnerMapel;
    MySQLHelper dbHelper;
    private int id = -1;
    EditText nilai;
    EditText idSiswa;
    private String mapel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nilai);
        getSupportActionBar().setTitle("Input Nilai");

        spinnerMapel = findViewById(R.id.spinnerMapel);
        dbHelper = new MySQLHelper(this);
        idSiswa = findViewById(R.id.inputIDSiswa);
        nilai = findViewById(R.id.inputNilai);

        loadSpinnerData();

        spinnerMapel.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mapel = spinnerMapel.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


    }

    private void loadSpinnerData() {
        // Spinner Drop down elements
        List<String> mapels = dbHelper.getMapel();
        //String[] mapels = db.getMapel().toArray(new String[10]);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mapels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerMapel.setAdapter(dataAdapter);
    }

    public void btnInputNilai(View view){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            if(id!=-1){
                db.execSQL("update "+ MySQLHelper.tbnilai
                        +" set nilai='"+ nilai +"' where mapel="+ mapel +" and id_siswa="+idSiswa);
                Toast.makeText(InputNilai.this,"Berhasil input nilai", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            idSiswa.setText(e.toString());
        }

        /*TextView txt = findViewById(R.id.textView4);
        txt.setText(mapel);*/
    }

}
