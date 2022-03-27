package com.ssuk1.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class FourWordsGame extends AppCompatActivity {

    String[] problem = new ArrayList<String>().toArray(new String[0]);

    TextView tv, word1, word2, word3, word4;
    Button solution;
    LinearLayout screen;
    TimerTask timerTask;
    Timer timer;
    String category;
    MediaPlayer endsound, midsound;

    int repeat;
    int time;
    int index_ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_words_game);

        // 상태창 제거
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tv = findViewById(R.id.time);
        solution = findViewById(R.id.btn);
        word1 = findViewById(R.id.word1);
        word2 = findViewById(R.id.word2);
        word3 = findViewById(R.id.word3);
        word4 = findViewById(R.id.word4);
        screen = findViewById(R.id.screen);

        endsound = MediaPlayer.create(this,R.raw.ddang_sound);
        midsound = MediaPlayer.create(this,R.raw.onetwothree_sound);

        solution.setOnClickListener(onClickListener);
        screen.setOnTouchListener(onTouchListener);


        //값 받아오기
        Intent setting = getIntent();

        repeat = setting.getIntExtra("setting_repeat", 0);
        time = setting.getIntExtra("setting_time", 0);
        category = setting.getStringExtra("setting_category");

        //문제 넣고 섞기
        putProblem();

        //Log.e("###", problem.get(index_));

        startTimerTask();
    }

    private void putProblem() {

        if(category.equals("사자성어")) problem = getResources().getStringArray(R.array.sagha);
        else problem = getResources().getStringArray(R.array.anyelse);
        Collections.shuffle(Arrays.asList(problem));
    }

    public void startTimerTask() {

        stopTimerTask();
        change_pb();

        if(index_ == repeat + 1){
            word1.setText("게");
            word2.setText("임");
            word3.setText("종");
            word4.setText("료!");
            tv.setText("");
            solution.setVisibility(View.GONE);
            return;
        }
        else{
            timer = new Timer();
            timerTask = new TimerTask() {

                int count = time;

                @Override
                public void run() {
                    tv.post(new Runnable() {
                        @Override
                        public void run() {
                            if(count == 0){
                                midsound.start();
                            }
                            if(count == -1) {
                                tv.setText("끝");
                                endsound.start();
                            }
                            else if (count < -1) {
                                startTimerTask();
                                return;
                            }
                            else tv.setText(Integer.toString(count));
                        }
                    });
                    count--;
                }
            };
            timer.schedule(timerTask,0 ,1000);
        }
    }

    private void stopTimerTask()
    {
        if(timerTask != null)
        {
            timerTask.cancel();
            timerTask = null;
        }
    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(index_ < repeat + 1){
                startTimerTask();
                return false;
            }
            else return false;
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn:
                    Toast.makeText(getApplicationContext(), problem[index_ - 1], Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void change_pb() {

        char[] pb = (problem[index_]).toCharArray();
        Log.e("###", Integer.toString(index_));
        word1.setText(Character.toString(pb[0]));
        word2.setText(Character.toString(pb[1]));
        //index_ 이용
        // 화면에 index번째 문제 띄우기

        index_++;
    }
}

