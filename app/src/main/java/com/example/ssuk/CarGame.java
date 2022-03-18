package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.media.Image;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Random;

public class CarGame extends AppCompatActivity {

    ImageView jump_gif, start_gif, stop_gif, turn_left_gif, turn_right_gif;
    TextView jump_text, start_text, stop_text, turn_left_text, turn_right_text,jumpway;
    ImageView upway, downway, leftway,rightway;
    MediaPlayer mediaPlayer;
    LinearLayout screen;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_game);

        Image_Setting();
        Random_Image();

        screen = findViewById(R.id.screen);
        screen.setOnTouchListener(onTouchListener);

    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Reset_Glide();
            Image_Setting();
            Random_Image();
            return false;
        }
    };

    public void Image_Setting(){
        jump_gif = (ImageView) findViewById(R.id.jump_gif);
        jump_text = (TextView) findViewById(R.id.jump_text);
        jumpway = (TextView) findViewById(R.id.jumpway);
        Glide.with(this).load(R.drawable.jump_remove).into(jump_gif);

        start_gif = (ImageView) findViewById(R.id.start_gif);
        start_text = (TextView) findViewById(R.id.start_text);
        upway = (ImageView) findViewById(R.id.upway);
        Glide.with(this).load(R.drawable.start_remove).into(start_gif);

        stop_gif = (ImageView) findViewById(R.id.stop_gif);
        stop_text = (TextView) findViewById(R.id.stop_text);
        downway = (ImageView) findViewById(R.id.downway);
        Glide.with(this).load(R.drawable.stop_remove).into(stop_gif);

        turn_left_gif = (ImageView) findViewById(R.id.turn_left_gif);
        turn_left_text = (TextView) findViewById(R.id.turn_left_text);
        leftway =(ImageView) findViewById(R.id.leftway);
        Glide.with(this).load(R.drawable.turn_left_remove).into(turn_left_gif);

        turn_right_gif = (ImageView) findViewById(R.id.turn_right_gif);
        turn_right_text = (TextView) findViewById(R.id.turn_right_text);
        rightway = (ImageView) findViewById(R.id.rightway);
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
            jump_text.setVisibility((View.VISIBLE));
            start_text.setVisibility((View.GONE));
            stop_text.setVisibility((View.GONE));
            turn_left_text.setVisibility((View.GONE));
            turn_right_text.setVisibility((View.GONE));
            jumpway.setVisibility((View.VISIBLE));
            upway.setVisibility((View.GONE));
            downway.setVisibility((View.GONE));
            rightway.setVisibility((View.GONE));
            leftway.setVisibility((View.GONE));
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
            jump_text.setVisibility((View.GONE));
            start_text.setVisibility((View.VISIBLE));
            stop_text.setVisibility((View.GONE));
            turn_left_text.setVisibility((View.GONE));
            turn_right_text.setVisibility((View.GONE));
            jumpway.setVisibility((View.GONE));
            upway.setVisibility((View.VISIBLE));
            downway.setVisibility((View.GONE));
            rightway.setVisibility((View.GONE));
            leftway.setVisibility((View.GONE));
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
            jump_text.setVisibility((View.GONE));
            start_text.setVisibility((View.GONE));
            stop_text.setVisibility((View.VISIBLE));
            turn_left_text.setVisibility((View.GONE));
            turn_right_text.setVisibility((View.GONE));
            jumpway.setVisibility((View.GONE));
            upway.setVisibility((View.GONE));
            downway.setVisibility((View.VISIBLE));
            rightway.setVisibility((View.GONE));
            leftway.setVisibility((View.GONE));
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
            jump_text.setVisibility((View.GONE));
            start_text.setVisibility((View.GONE));
            stop_text.setVisibility((View.GONE));
            turn_left_text.setVisibility((View.VISIBLE));
            turn_right_text.setVisibility((View.GONE));
            jumpway.setVisibility((View.GONE));
            upway.setVisibility((View.GONE));
            downway.setVisibility((View.GONE));
            rightway.setVisibility((View.GONE));
            leftway.setVisibility((View.VISIBLE));
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
            jump_text.setVisibility((View.GONE));
            start_text.setVisibility((View.GONE));
            stop_text.setVisibility((View.GONE));
            turn_left_text.setVisibility((View.GONE));
            turn_right_text.setVisibility((View.VISIBLE));
            jumpway.setVisibility((View.GONE));
            upway.setVisibility((View.GONE));
            downway.setVisibility((View.GONE));
            rightway.setVisibility((View.VISIBLE));
            leftway.setVisibility((View.GONE));
        }
    }

    public void Reset_Glide()
    {
        Glide.with(this).load(R.drawable.ic_launcher_foreground).into(jump_gif);
        Glide.with(this).load(R.drawable.ic_launcher_foreground).into(start_gif);
        Glide.with(this).load(R.drawable.ic_launcher_foreground).into(stop_gif);
        Glide.with(this).load(R.drawable.ic_launcher_foreground).into(turn_left_gif);
        Glide.with(this).load(R.drawable.ic_launcher_foreground).into(turn_right_gif);
    }
}

