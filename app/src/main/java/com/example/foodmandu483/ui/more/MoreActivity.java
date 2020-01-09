package com.example.foodmandu483.ui.more;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodmandu483.LoginActivity;
import com.example.foodmandu483.MainActivity;
import com.example.foodmandu483.R;
import com.example.foodmandu483.bll.LoginBLL;
import com.example.foodmandu483.strictmode.StrictMode;

public class MoreActivity extends Fragment {
MoreViewModel moreViewModel;
Button button;

public View onCreateView(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {
    moreViewModel =
            ViewModelProviders.of(this).get(MoreViewModel.class);
    View root = inflater.inflate(R.layout.activity_more, container, false);


    button=root.findViewById(R.id.btnNextLog);
    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor=sharedPreferences.edit();
    String user=sharedPreferences.getString("username","");
    String pass=sharedPreferences.getString("password","");
    LoginBLL loginBLL =new LoginBLL();
    StrictMode.StrictMode();
    if(loginBLL.checkUser(user,pass))
    {
        button.setText("Logout");
    }
    else {
        button.setText("Login");
    }




    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(button.getText().equals("Logout"))
            {


                //SharedPreferences settings= PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences sharedPreferences1=getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences1.edit();
                editor.clear();
                editor.commit();
                Intent intent=new Intent(MoreActivity.this.getActivity(),MainActivity.class);
                startActivity(intent);

            }
            else
            {
                Intent intent=new Intent(MoreActivity.this.getActivity(),LoginActivity.class);
                startActivity(intent);

            }

        }
    });










    return root;
}



}
