package com.example.foodmandu483;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.foodmandu483.bll.LoginBLL;
import com.example.foodmandu483.strictmode.StrictMode;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
   // private CircleImageView img;
    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    Boolean logged=false;


   /* @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);
        img=(CircleImageView)findViewById(R.id.imgDP);
        img.setImageResource(R.drawable.dog);
    }*/

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

//        img.requestLayout();
//        img.getLayoutParams().height=200;
//        img.getLayoutParams().width=300;



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools,R.id.nav_more)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);





    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setTitle(Html.fromHtml("<font color='000000'>FOODMANDU</font>"));
//        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
//        actionBar.show();
        getMenuInflater().inflate(R.menu.main, menu);
        drawer=findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.colorMaroon));
        toggle.syncState();


        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        String user=sharedPreferences.getString("username","");
        String pass=sharedPreferences.getString("password","");
        LoginBLL loginBLL =new LoginBLL();
        StrictMode.StrictMode();
        CircleImageView img=findViewById(R.id.imgDP);

        TextView text=findViewById(R.id.name);
        if(loginBLL.checkUser(user,pass))
        {
//            File imgfile=new File("/home/jimmy/Desktop/TaskManagerAPI-master/public/uploads" + imag);
//            if(imgfile.exists())
//            {
//                Bitmap bitmap= BitmapFactory.decodeFile(imgfile.getAbsolutePath());
//                img.setImageBitmap(bitmap);
//
//            }
//            Toast.makeText(this,imag,Toast.LENGTH_LONG).show();
//            Bitmap bitmap=BitmapFactory.decodeFile("/home/jimmy/Desktop/TaskManagerAPI-master/public/uploads" + imag);
//            img.setImageBitmap(bitmap);
            text.setText(user);
            img.setImageResource(R.drawable.img);
            logged=true;





        }
        else {
            logged=false;
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logged.equals(true))
                {
                    Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        });


        //View view=navigationView.inflateHeaderView(R.layout.nav_header_main);


        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
