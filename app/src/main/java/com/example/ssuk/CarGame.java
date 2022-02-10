package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class CarGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_game);

        ImageView jump_gif = (ImageView) findViewById(R.id.jump_gif);
        Glide.with(this).load(R.drawable.jump_remove).into(jump_gif);

        ImageView start_gif = (ImageView) findViewById(R.id.start_gif);
        Glide.with(this).load(R.drawable.start_remove).into(start_gif);

        ImageView stop_gif = (ImageView) findViewById(R.id.stop_gif);
        Glide.with(this).load(R.drawable.stop_remove).into(stop_gif);

        ImageView turn_left_gif = (ImageView) findViewById(R.id.turn_left_gif);
        Glide.with(this).load(R.drawable.turn_left_remove).into(turn_left_gif);

        ImageView turn_right_gif = (ImageView) findViewById(R.id.turn_right_gif);
        Glide.with(this).load(R.drawable.turn_right_remove).into(turn_right_gif);


    }
}