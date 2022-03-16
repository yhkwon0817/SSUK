package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


public class PictureGame extends AppCompatActivity {

    ArrayList<Integer> problem = new ArrayList<>();

    String category;

    ImageView pic;
    TextView timeview;
    Button solution;

    MediaPlayer endsound, midsound;
    LinearLayout screen;
    Timer timer;
    TimerTask timerTask;

    int repeat, time, index_ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       /* mSoundPool = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes mAudioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            new SoundPool.Builder()
                    .setAudioAttributes(mAudioAttributes)
                    .setMaxStreams(2).build();
        }
        SoundPool.Builder().*/

        timeview = findViewById(R.id.time);
        screen = findViewById(R.id.screen);
        solution = findViewById(R.id.btn);
        pic = findViewById(R.id.image);

        midsound = MediaPlayer.create(this, R.raw.onetwothree_sound);
        endsound = MediaPlayer.create(this, R.raw.ddang_sound);

        //답 버튼은 아직
        //solution.setOnClickListener(onClickListener);
        screen.setOnTouchListener(onTouchListener);
        
        Intent setting = getIntent();

        repeat = setting.getIntExtra("setting_repeat", 0);
        time = setting.getIntExtra("setting_time", 0);
        category = setting.getStringExtra("setting_category");

        /*Log.e("###", "반복 : " + repeat + " 카테고리 :  " + category + "시간 :  " + time);*/

        putProblem();
        startTimerTask();
    }

    private void putProblem() {

        if(category.equals("여자 배우")){
            problem.add(R.drawable.pgw1);
            problem.add(R.drawable.pgw2);
            problem.add(R.drawable.pgw3);
            problem.add(R.drawable.pgw4);
            problem.add(R.drawable.pgw5);
            problem.add(R.drawable.pgw6);
            problem.add(R.drawable.pgw7);
            problem.add(R.drawable.pgw8);
            problem.add(R.drawable.pgw9);
            problem.add(R.drawable.pgw10);
            problem.add(R.drawable.pgw11);
            problem.add(R.drawable.pgw12);
            problem.add(R.drawable.pgw13);
            problem.add(R.drawable.pgw14);
            problem.add(R.drawable.pgw15);
            problem.add(R.drawable.pgw16);
            problem.add(R.drawable.pgw17);
            problem.add(R.drawable.pgw18);
            problem.add(R.drawable.pgw19);
            problem.add(R.drawable.pgw20);
        }
        else if(category.equals("남자 배우")){
            problem.add(R.drawable.pgm1);
            problem.add(R.drawable.pgm2);
            problem.add(R.drawable.pgm3);
            problem.add(R.drawable.pgm4);
            problem.add(R.drawable.pgm5);
            problem.add(R.drawable.pgm6);
            problem.add(R.drawable.pgm7);
            problem.add(R.drawable.pgm8);
            problem.add(R.drawable.pgm9);
            problem.add(R.drawable.pgm10);
            problem.add(R.drawable.pgm11);
            problem.add(R.drawable.pgm12);
            problem.add(R.drawable.pgm13);
            problem.add(R.drawable.pgm14);
            problem.add(R.drawable.pgm15);
            problem.add(R.drawable.pgm16);
            problem.add(R.drawable.pgm17);
            problem.add(R.drawable.pgm18);
            problem.add(R.drawable.pgm19);
            problem.add(R.drawable.pgm20);
        }
        else{
            problem.add(R.drawable.pgh1);
            problem.add(R.drawable.pgh2);
            problem.add(R.drawable.pgh3);
            problem.add(R.drawable.pgh4);
            problem.add(R.drawable.pgh5);
            problem.add(R.drawable.pgh6);
            problem.add(R.drawable.pgh7);
            problem.add(R.drawable.pgh9);
            problem.add(R.drawable.pgh10);
            problem.add(R.drawable.pgh11);
            problem.add(R.drawable.pgh12);
            problem.add(R.drawable.pgh13);
            problem.add(R.drawable.pgh14);
            problem.add(R.drawable.pgh15);
            problem.add(R.drawable.pgh16);
            problem.add(R.drawable.pgh17);
        }

        Collections.shuffle(Arrays.asList(problem));
    }

    public void startTimerTask(){

        stopTimerTask();
        change_pb();

        if(index_ == repeat + 1){
            // 종료 이미지 넣어야 함
            pic.setImageResource(R.drawable.endpg2);
            timeview.setText("");
           /* solution.setVisibility(View.GONE);*/
            return;
        }
        else{
            timer = new Timer();
            timerTask = new TimerTask() {

                int count = time;

                @Override
                public void run() {
                    timeview.post(new Runnable() {
                        @Override
                        public void run() {
                            if(count == 0) midsound.start();
                            if(count == -1) {
                                endsound.start();
                                timeview.setText("끝");
                            }
                            else if(count < -1){
                                startTimerTask();
                                return;
                            }
                            else timeview.setText(Integer.toString(count));
                        }
                    });
                    count--;
                }
            };
            timer.schedule(timerTask, 0, 1000);
        }
    }

    //답 어떻게 가져올지 생각
/*    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn:
                    Intent intent = new Intent(PictureGame.this, PictureGameAnswer.class);
                    intent.putExtra("Ans_category", category);
                    intent.putExtra("Ans_list", problem);
                    startActivity(intent);
            }
        }
    };*/

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            /*Log.e("###", "" + index_);*/
            if(index_ < repeat + 1){
                startTimerTask();
                return false;
            }
            else return false;
        }
    };
    
    

    private void change_pb() {
        pic.setImageResource(problem.get(index_));
        index_++;
    }

    private void stopTimerTask() {
        if(timerTask != null)
        {
            timerTask.cancel();
            timerTask = null;
        }
    }


}