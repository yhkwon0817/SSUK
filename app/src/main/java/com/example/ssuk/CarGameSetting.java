package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CarGameSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_game_setting);

        Button button = (Button) findViewById(R.id.CarGameStart);
        ImageView btn_car_game_hint = (ImageView) findViewById(R.id.btn_car_game_hint);
        ImageView btn_close_car_game_hint = (ImageView)findViewById(R.id.btn_close_car_game_hint);
        LinearLayout layout_car_game_hint = (LinearLayout)findViewById(R.id.layout_car_game_hint);

        btn_car_game_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CarGameIntroduction.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CarGame.class);
                startActivity(intent);
            }
        });
    }
}