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

        solution.setOnClickListener(onClickListener);
        screen.setOnTouchListener(onTouchListener);

        //값 받아오기
        Intent setting = getIntent();

        repeat = setting.getIntExtra("setting_repeat", 0);
        time = setting.getIntExtra("setting_time", 0);
        String category = setting.getStringExtra("setting_category");

        //문제 넣고 섞기
        putProblem();

        //Log.e("###", problem.get(index_));

        startTimerTask();
    }

    private void putProblem() {
        problem.add("사필귀정");
        problem.add("정문일침");
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
    }

    public void clickHandler(View view)
    {
        switch(view.getId())
        {
            case R.id.screen:

                break;
/*            case R.id.btn :
                stopTimerTask();
                break;*/
        }
    }

    public void startTimerTask() {

        stopTimerTask();
        change_pb();

        if(index_ == repeat){
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
                            if(count == -1) tv.setText("끝");
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
            if(index_ < repeat){
                startTimerTask();
                Log.e("###", index_ + "인덱스 터치 호출");
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
                    Toast.makeText(getApplicationContext(), problem.get(index_ - 1), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void change_pb() {

        char[] pb = (problem.get(index_)).toCharArray();
        Log.e("###", Integer.toString(index_));
        word1.setText(Character.toString(pb[0]));
        word2.setText(Character.toString(pb[1]));
        //index_ 이용
        // 화면에 index번째 문제 띄우기

        index_++;
    }
}

