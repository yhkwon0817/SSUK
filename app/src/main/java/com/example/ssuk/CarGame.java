package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Random;

public class CarGame extends AppCompatActivity {

    ImageView jump_gif, start_gif, stop_gif, turn_left_gif, turn_right_gif;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_game);
        Button button = (Button) findViewById(R.id.Next_button);

        Image_Setting();
        Random_Image();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reset_Glide();
                Image_Setting();
                Random_Image();
            }
        });
    }

    public void Image_Setting(){
        jump_gif = (ImageView) findViewById(R.id.jump_gif);
        Glide.with(this).load(R.drawable.jump_remove).into(jump_gif);

        start_gif = (ImageView) findViewById(R.id.start_gif);
        Glide.with(this).load(R.drawable.start_remove).into(start_gif);

        stop_gif = (ImageView) findViewById(R.id.stop_gif);
        Glide.with(this).load(R.drawable.stop_remove).into(stop_gif);

        turn_left_gif = (ImageView) findViewById(R.id.turn_left_gif);
        Glide.with(this).load(R.drawable.turn_left_remove).into(turn_left_gif);

        turn_right_gif = (ImageView) findViewById(R.id.turn_right_gif);
        Glide.with(this).load(R.drawable.turn_right_remove).into(turn_right_gif);
    }

    public void Random_Image(){
        Random random = new Random();
        int i = random.nextInt(5)+1;
        if(i == 1)
        {
            jump_gif.setVisibility((View.VISIBLE));
            start_gif.setVisibility((View.GONE));
            stop_gif.setVisibility((View.GONE));
            turn_left_gif.setVisibility((View.GONE));
            turn_right_gif.setVisibility((View.GONE));
            mediaPlayer = MediaPlayer.create(this,R.raw.jump_sound);
            mediaPlayer.start();
        }
        if(i == 2)
        {
            jump_gif.setVisibility((View.GONE));
            start_gif.setVisibility((View.VISIBLE));
            stop_gif.setVisibility((View.GONE));
            turn_left_gif.setVisibility((View.GONE));
            turn_right_gif.setVisibility((View.GONE));
            mediaPlayer = MediaPlayer.create(this,R.raw.start_sound);
            mediaPlayer.start();
        }
        if(i == 3)
        {
            jump_gif.setVisibility((View.GONE));
            start_gif.setVisibility((View.GONE));
            stop_gif.setVisibility((View.VISIBLE));
            turn_left_gif.setVisibility((View.GONE));
            turn_right_gif.setVisibility((View.GONE));
            mediaPlayer = MediaPlayer.create(this,R.raw.stop_sound);
            mediaPlayer.start();
        }
        if(i == 4)
        {
            jump_gif.setVisibility((View.GONE));
            start_gif.setVisibility((View.GONE));
            stop_gif.setVisibility((View.GONE));
            turn_left_gif.setVisibility((View.VISIBLE));
            turn_right_gif.setVisibility((View.GONE));
            mediaPlayer = MediaPlayer.create(this,R.raw.turn_left_sound);
            mediaPlayer.start();
        }
        if(i == 5)
        {
            jump_gif.setVisibility((View.GONE));
            start_gif.setVisibility((View.GONE));
            stop_gif.setVisibility((View.GONE));
            turn_left_gif.setVisibility((View.GONE));
            turn_right_gif.setVisibility((View.VISIBLE));
            mediaPlayer = MediaPlayer.create(this,R.raw.turn_right_sound);
            mediaPlayer.start();
        }
    }

    public void Reset_Glide()
    {
        Glide.with(this).load(R.drawable.ic_launcher_background).into(jump_gif);
        Glide.with(this).load(R.drawable.ic_launcher_background).into(start_gif);
        Glide.with(this).load(R.drawable.ic_launcher_background).into(stop_gif);
        Glide.with(this).load(R.drawable.ic_launcher_background).into(turn_left_gif);
        Glide.with(this).load(R.drawable.ic_launcher_background).into(turn_right_gif);
    }
}

