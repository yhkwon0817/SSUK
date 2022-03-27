package com.ssuk1.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

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
        ImageView imageView = (ImageView)findViewById(R.id.img_bottom);

        Display display = getWindowManager().getDefaultDisplay();  // in Activity
        /* getActivity().getWindowManager().getDefaultDisplay() */ // in Fragment
        Point size = new Point();
        display.getRealSize(size); // or getSize(size)
        int width = size.x;
        int height = size.y;

        if((width==1440&&height==2560)||(width==1080&&height==1920))
            imageView.setVisibility(View.GONE);
        if(height>=2000)
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

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
                Intent intent = new Intent(getApplicationContext(),PictureGameSetting.class);
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

        logo4.setOnClickListener(new View.OnClickListener() {
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