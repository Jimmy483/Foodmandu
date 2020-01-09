package com.example.foodmandu483;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodmandu483.api.UserAPI;
import com.example.foodmandu483.model.User;
import com.example.foodmandu483.serverresponse.ImageResponse;
import com.example.foodmandu483.serverresponse.SignUpResponse;
import com.example.foodmandu483.strictmode.StrictMode;
import com.example.foodmandu483.url.URL;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {
private EditText fname,lname,phoneno,username,password,cpassword;
private Button button;
String path;
private String imagename="";
private CircleImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        Toolbar toolbar=findViewById(R.id.toolbar5);
        toolbar.setTitle("SIGN UP");
        fname=findViewById(R.id.etFname);
        lname=findViewById(R.id.etLname);
        phoneno=findViewById(R.id.etPhone);
        username=findViewById(R.id.etUserName);
        password=findViewById(R.id.etPass);
        cpassword=findViewById(R.id.etCpass);
        img=findViewById(R.id.imgProfile);
        button=findViewById(R.id.btnSignUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkValidation();
                checkPassword();
                saveImage();
                signup();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission();
                BrowseImage();
            }
        });
    }

    public void saveImage()
    {
        File file=new File(path);
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("imageFile",file.getName(),requestBody);
        UserAPI userAPI=URL.getInstance().create(UserAPI.class);
        Call<ImageResponse> responseCall=userAPI.uploadImage(body);
        StrictMode.StrictMode();
        try {
            Response<ImageResponse> imageResponseResponse=responseCall.execute();
            imagename=imageResponseResponse.body().getFilename();
            Toast.makeText(this,"image uploaded" + imagename,Toast.LENGTH_LONG).show();
        }catch (IOException e)
        {
            Toast.makeText(this,"error " + e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();;
        }
    }
    public void CheckPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
    }

    private  void BrowseImage()
    {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            if(data==null)
            {
                Toast.makeText(this,"Please select an image",Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri=data.getData();
        img.setImageURI(uri);
        path=getPathFromUri(uri);

    }

    private String getPathFromUri(Uri uri)
    {
        String[] project={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getApplicationContext(),
                uri,project,null,null,null);
        Cursor cursor=loader.loadInBackground();
        int colIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(colIndex);
        cursor.close();
        return result;
    }
    public void checkValidation()
    {
        if(TextUtils.isEmpty(fname.getText().toString()))
        {
            Toast.makeText(this,"Please fill in the empty field",Toast.LENGTH_LONG).show();
            fname.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(lname.getText().toString()))
        {
            Toast.makeText(this,"Please fill in the empty field",Toast.LENGTH_LONG).show();
            lname.requestFocus();
            return;
        }

        try{
            Integer.parseInt(phoneno.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this,"Please fill in the empty field",Toast.LENGTH_LONG).show();
            phoneno.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(username.getText().toString()))
        {
            Toast.makeText(this,"Please fill in the empty field",Toast.LENGTH_LONG).show();
            username.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password.getText().toString()))
        {
            Toast.makeText(this,"Please fill in the empty field",Toast.LENGTH_LONG).show();
            password.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(cpassword.getText().toString()))
        {
            Toast.makeText(this,"Please fill in the empty field",Toast.LENGTH_LONG).show();
            cpassword.requestFocus();
            return;
        }
    }

    public void checkPassword()
    {
        if(!password.getText().toString().equals(cpassword.getText().toString()))
        {
            Toast.makeText(this,"Passwords does not match",Toast.LENGTH_LONG).show();
            password.setText("");
            cpassword.setText("");
            password.requestFocus();
            return;
        }
    }
    public void signup()
    {



        String fn=fname.getText().toString();
        String ln=lname.getText().toString();
        String phone=phoneno.getText().toString();
        String usern=username.getText().toString();
        String pass=password.getText().toString();



        User user=new User(fn,ln,phone,usern,pass);
        UserAPI userAPI= URL.getInstance().create(UserAPI.class);
        Call<SignUpResponse> signUpResponseCall=userAPI.createUser(user);
        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(SignUpActivity.this, "Code " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                saveData();
                Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this,"Error Creating" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }
    public void saveData()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",username.getText().toString());
        editor.putString("password",password.getText().toString());
        editor.putString("fname",fname.getText().toString());
        editor.putString("lname",lname.getText().toString());
        editor.putString("phone",phoneno.getText().toString());

        editor.commit();
    }


}
