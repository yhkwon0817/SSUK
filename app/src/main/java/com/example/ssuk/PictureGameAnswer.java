package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class PictureGameAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game_answer);

        Intent get = getIntent();

        String category = get.getStringExtra("Ans_category");
        int [] problem = get.getIntArrayExtra("Ans_list");




    }
}