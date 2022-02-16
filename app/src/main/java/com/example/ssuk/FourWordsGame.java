package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class FourWordsGame extends AppCompatActivity {

    boolean isFinish = false;
    ArrayList<String> problem = new ArrayList<String>();

    TextView tv, word1, word2, word3, word4;
    Button solution;
    CountDownTimer countDownTimer;
    LinearLayout screen;
    TimerTask timerTask;
    Timer timer;

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

        //값 받아오기
        Intent setting = getIntent();

        repeat = setting.getIntExtra("setting_repeat", 0);
        time = setting.getIntExtra("setting_time", 0);
        String category = setting.getStringExtra("setting_category");

        problem.add("사필귀정");
        problem.add("정문일침");
        problem.add("어렵네요");
        problem.add("죽마고우");
        problem.add("수어지교");
        problem.add("각골난망");
        problem.add("군계일학");
        problem.add("격세지감");
        problem.add("계란투석");
        problem.add("백면서생");
        problem.add("좌정관천");
        problem.add("고진감래");
        problem.add("괄목상대");
        problem.add("과유불급");

        Collections.shuffle(problem);

        solution.setOnClickListener(onClickListener);
        screen.setOnTouchListener(onTouchListener);
        //Log.e("###", problem.get(index_));

        timerTask();
        countDownTime();

        timer.schedule(timerTask, 0, (time + 1) * 1000);

    }

    public void timerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                change_pb();
                if (index_ == repeat) {
                    timer.cancel();
                }
            }
        };

    }

    public void countDownTime() {
        countDownTimer = new CountDownTimer(time * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int num = (int) (millisUntilFinished / 1000);
                tv.setText(Integer.toString(num + 1));
            }

            @Override
            public void onFinish() {
                tv.setText("끝");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(isFinish) {


                        }
                        change_pb();
                    }
                }, 1000);
            }
        };
    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            countDownTimer.cancel();
            timer.cancel();
            Log.e("###", index_ + "인덱스 터치 호출");

            change_pb();

            return false;
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn:
                    Toast.makeText(getApplicationContext(), problem.get(index_ - 1), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    public void change_pb() {

        countDownTimer.start();

        Log.e("###", Integer.toString(repeat));
        Log.e("###", index_ + "번째 문제바꿈 호출");

        if (index_ == repeat) {
            word1.setText("게");
            word2.setText("임");
            word3.setText("종");
            word4.setText("료!");
            isFinish = true;
        }

        char[] pb = (problem.get(index_)).toCharArray();
        Log.e("###", Integer.toString(index_));
        word1.setText(Character.toString(pb[0]));
        word2.setText(Character.toString(pb[1]));
        //index_ 이용
        // 화면에 index번째 문제 띄우기

        index_++;
    }
}

