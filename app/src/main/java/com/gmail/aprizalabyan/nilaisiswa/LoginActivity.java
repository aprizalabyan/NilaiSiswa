package com.gmail.aprizalabyan.nilaisiswa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    MySQLHelper dbHelper;
    private ArrayList<String> username;
    private ArrayList<String> password;

    EditText id;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login Siswa");

        username = new ArrayList<>();
        password = new ArrayList<>();

        id = findViewById(R.id.inputUser);
        pass = findViewById(R.id.inputPass);

        dbHelper = new MySQLHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ MySQLHelper.tbsiswa,null);

        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
                username.add(cursor.getString(0));
                password.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void Login(View view){
        String inputUser = id.getText().toString();
        String inputPass = pass.getText().toString();

        if(inputUser.equals("") && inputPass.equals("")){
            Intent intentList = new Intent(getApplicationContext(), TampilNilai.class);
            intentList.putExtra("id_siswa", id.getText().toString());
            startActivity(intentList);
        }
        else{
            Toast.makeText(getApplicationContext(),"Username dan Password salah",Toast.LENGTH_SHORT).show();
        }
    }
}
