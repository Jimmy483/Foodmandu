package com.example.foodmandu483.bll;

import android.net.Uri;

import com.example.foodmandu483.api.UserAPI;
import com.example.foodmandu483.serverresponse.SignUpResponse;
import com.example.foodmandu483.url.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean success=false;

    public boolean checkUser(String username,String password){
        UserAPI userAPI= URL.getInstance().create(UserAPI.class);
        Call<SignUpResponse> usersCall=userAPI.checkUser(username,password);
        try{
            Response<SignUpResponse> loginResponse=usersCall.execute();
            //if(loginResponse.isSuccessful() && loginResponse.body().getStatus().equals("Login Success"))
            if(loginResponse.isSuccessful()&& loginResponse.body().getStatus().equals("Login success!"))
            {
                URL.token += loginResponse.body().getToken();
                success=true;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return success;
    }
}
