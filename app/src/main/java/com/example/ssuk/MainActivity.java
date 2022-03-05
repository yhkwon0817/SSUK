package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    static final String[] LIST_MENU = {"4글자 게임","사진 게임","붕붕붕 게임","라이어 게임","노래 맞추기"};
    ArrayAdapter adapter;
    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this,R.raw.ssuk_bgm);
        mp.setLooping(true);
        mp.start();

        ImageView logo1 = (ImageView) findViewById(R.id.fourwordsgame_title);
        ImageView logo2 = (ImageView) findViewById(R.id.picturegame_title);
        ImageView logo3 = (ImageView) findViewById(R.id.cargame_title);
        ImageView logo4 = (ImageView) findViewById(R.id.liargame_title);

        logo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FourWordsGameSetting.class);
                startActivity(intent);
            }
        });

        logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PictureGame.class);
                startActivity(intent);
            }
        });

        logo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CarGameSetting.class);
                startActivity(intent);
            }
        });

        logo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LiarGameSetting.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected  void onUserLeaveHint(){
        mp.pause();;
        super.onUserLeaveHint();
    }

    @Override
    public void onResume(){
        mp.start();
        super.onResume();
    }

    @Override
    public void onDestroy(){
        mp.stop();
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        mp.stop();
        super.onBackPressed();
    }
}