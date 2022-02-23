package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_game);

        Button button = (Button) findViewById(R.id.Next_button);

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

        jump_gif.setVisibility((View.INVISIBLE));
        start_gif.setVisibility((View.INVISIBLE));
        stop_gif.setVisibility((View.INVISIBLE));
        turn_left_gif.setVisibility((View.INVISIBLE));
        turn_right_gif.setVisibility((View.VISIBLE));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int i = random.nextInt(5)+1;
                if(i == 1)
                {
                    jump_gif.setVisibility((View.VISIBLE));
                    start_gif.setVisibility((View.INVISIBLE));
                    stop_gif.setVisibility((View.INVISIBLE));
                    turn_left_gif.setVisibility((View.INVISIBLE));
                    turn_right_gif.setVisibility((View.INVISIBLE));
                }
                if(i == 2)
                {
                    jump_gif.setVisibility((View.INVISIBLE));
                    start_gif.setVisibility((View.VISIBLE));
                    stop_gif.setVisibility((View.INVISIBLE));
                    turn_left_gif.setVisibility((View.INVISIBLE));
                    turn_right_gif.setVisibility((View.INVISIBLE));
                }
                if(i == 3)
                {
                    jump_gif.setVisibility((View.INVISIBLE));
                    start_gif.setVisibility((View.INVISIBLE));
                    stop_gif.setVisibility((View.VISIBLE));
                    turn_left_gif.setVisibility((View.INVISIBLE));
                    turn_right_gif.setVisibility((View.INVISIBLE));
                }
                if(i == 4)
                {
                    jump_gif.setVisibility((View.INVISIBLE));
                    start_gif.setVisibility((View.INVISIBLE));
                    stop_gif.setVisibility((View.INVISIBLE));
                    turn_left_gif.setVisibility((View.VISIBLE));
                    turn_right_gif.setVisibility((View.INVISIBLE));
                }
                if(i == 5)
                {
                    jump_gif.setVisibility((View.INVISIBLE));
                    start_gif.setVisibility((View.INVISIBLE));
                    stop_gif.setVisibility((View.INVISIBLE));
                    turn_left_gif.setVisibility((View.INVISIBLE));
                    turn_right_gif.setVisibility((View.VISIBLE));
                }

            }
        });
    }
}