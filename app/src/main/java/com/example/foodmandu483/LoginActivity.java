package com.example.foodmandu483;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodmandu483.bll.LoginBLL;
import com.example.foodmandu483.model.User;
import com.example.foodmandu483.strictmode.StrictMode;

public class LoginActivity extends AppCompatActivity {
TextView textView;
EditText username,password;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Toolbar toolbar = findViewById(R.id.toolbar3);
        toolbar.setTitle("LOGIN");

        username=findViewById(R.id.etUser);
        password=findViewById(R.id.etPassword);
        btn=findViewById(R.id.btnLogin);
        textView=findViewById(R.id.txtCreate);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });
    }

    public void login()
    {
        String user=username.getText().toString();
        String pass=password.getText().toString();
        LoginBLL loginBLL =new LoginBLL();
        StrictMode.StrictMode();
        if(loginBLL.checkUser(user,pass))
        {
            saveData();

            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this,"Either Username or Passowrd is incorrect",Toast.LENGTH_LONG).show();
            username.requestFocus();
        }
    }


    public void saveData()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",username.getText().toString());
        editor.putString("password",password.getText().toString());
        editor.commit();
    }

    }


