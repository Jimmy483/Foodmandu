package com.example.foodmandu483.ui.home;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodmandu483.R;
import com.example.foodmandu483.ui.more.MoreActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
//    private ImageView imageView;
    Animation _translateAnimation;
    LinearLayout _linearLoading = null;
    private CarouselView carouselView;
    private int[] pics=new int[]
            {
                    R.drawable.drinks,R.drawable.food,R.drawable.cooked
            };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

//        final TextView textView = root.findViewById(R.id.text_home);
//
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }

//        });
//        _translateAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f,
//                TranslateAnimation.ABSOLUTE, -1800f, TranslateAnimation.ABSOLUTE, 0f,
//                    TranslateAnimation.ABSOLUTE, 0f);
//        _translateAnimation.setDuration(11000);
//        _translateAnimation.setRepeatCount(-1);
//        _translateAnimation.setRepeatMode(Animation.ABSOLUTE); // REVERSE
//        _translateAnimation.setInterpolator(new LinearInterpolator());
//        _linearLoading = (LinearLayout) root.findViewById(R.id.linear);
//        _linearLoading.startAnimation(_translateAnimation);



        carouselView=root.findViewById(R.id.carousel);
        carouselView.setPageCount(pics.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(pics[position]);
            }
        });







        return root;
    }



}