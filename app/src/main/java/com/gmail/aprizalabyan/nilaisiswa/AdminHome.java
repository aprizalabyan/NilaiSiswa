package com.gmail.aprizalabyan.nilaisiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    public void btnInputSiswaadmin(View view){
        Intent intentInputSiswa = new Intent(getApplicationContext(), InputSiswa.class);
        startActivity(intentInputSiswa);
    }

    public void btnInputNilaiadmin(View view){
        Intent intentInputNilai = new Intent(getApplicationContext(), InputNilai.class);
        startActivity(intentInputNilai);
    }
}
