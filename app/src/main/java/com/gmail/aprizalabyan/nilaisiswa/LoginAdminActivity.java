package com.gmail.aprizalabyan.nilaisiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        getSupportActionBar().setTitle("Login Admin");
    }
    public void Login(View view){
        Intent intentInputNilai = new Intent(getApplicationContext(), AdminHome.class);

        String username = "admin";
        String password = "admin";

        EditText user = findViewById(R.id.inputUser);
        EditText pass = findViewById(R.id.inputPass);

        String inputUser = user.getText().toString();
        String inputPass = pass.getText().toString();

        if(inputUser.equals(username) && inputPass.equals(password)){
            startActivity(intentInputNilai);
        }
        else{
            Toast.makeText(getApplicationContext(),"Username dan Password salah",Toast.LENGTH_SHORT).show();
        }
    }
}
