package com.example.foodmandu483;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodmandu483.R;

public class ProfileActivity extends AppCompatActivity {
TextView username,phone;
EditText fname,lname,phonetxt;
Button save,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        username=findViewById(R.id.uname);
        phone=findViewById(R.id.phone);
        fname=findViewById(R.id.etFirstName);
        lname=findViewById(R.id.etLastName);
        phonetxt=findViewById(R.id.etNumber);
        save=findViewById(R.id.btnSave);
        logout=findViewById(R.id.btnLogOut);

        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        String user=sharedPreferences.getString("username","");
        String phon=sharedPreferences.getString("phone","");
        String first=sharedPreferences.getString("fname","");
        String second=sharedPreferences.getString("lname","");
        username.setText(user);
        phone.setText(phon);
        fname.setText(first);
        lname.setText(second);
        phonetxt.setText(phon);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent=new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }







}
