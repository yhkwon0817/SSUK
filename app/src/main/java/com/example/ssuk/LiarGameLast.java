package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LiarGameLast extends AppCompatActivity {
    public int num_answer=0;
    public int num_liar=0;
    public int category_number=0;
    public int answer_place;
    public String answer="";
    public int []check = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public String[][] array={{""},
            {"사자", "호랑이", "곰", "퓨마", "낙타", "기린", "다람쥐", "원숭이", "아르마딜로", "돌고래",
                    "박쥐", "캥거루", "삵", "하이에나", "말", "닭"},
            {"어바웃타임", "it", "밀정", "탐정", "노트북", "인턴", "좋은놈 나쁜놈 이상한놈", "미녀와 야수", "어벤져스", "2012",
                    "트랜스포머", "킹스맨"},
            {"김연아", "아이유", "레오나르도 다빈치", "장영실", "방탄소년단", "유재석", "강동원", "마이클 잭슨", "저스틴 비버", "페이커",
                    "손흥민"},
            {"냉장고", "전자렌지", "전기모기채", "전기장판", "리모컨", "TV"}

    };
    Timer timer;
    TimerTask timerTask;
    CountDownTimer countDownTimer;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16;
    TextView textClock, text_after_choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liar_game_last);

        Intent secondIntent = getIntent();
        num_answer=secondIntent.getIntExtra("정답 번호", 0);
        num_liar=secondIntent.getIntExtra("라이어", 0);
        category_number=secondIntent.getIntExtra("카테고리 넘버", 0);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
        btn10=findViewById(R.id.btn10);
        btn11=findViewById(R.id.btn11);
        btn12=findViewById(R.id.btn12);
        btn13=findViewById(R.id.btn13);
        btn14=findViewById(R.id.btn14);
        btn15=findViewById(R.id.btn15);
        btn16=findViewById(R.id.btn16);
        textClock=findViewById(R.id.text_clock);
        text_after_choose=findViewById(R.id.text_after_choose);

        answer=array[category_number][num_answer];
        answer_place=(int)(Math.random()*16);

        int i=1;
        int randN=(int)(Math.random()*16);
        int chk=0;

        while(i<=16){
            if(check[randN]==0){
                if(array[category_number][randN].equals(answer))chk++;
                setButton(i, array[category_number][randN]);
                check[randN] = 1;
                i++;
            }
            randN=(int)(Math.random()*16);
        }
        if(chk==0)setButton(answer_place, answer);

        timerTask();
        countDownTime();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.getText().toString().equals(answer)) {
                    countDownTimer.onFinish();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void setButton(int num, String text){
        if(num==1)btn1.setText(text);
        else if(num==2)btn2.setText(text);
        else if(num==3)btn3.setText(text);
        else if(num==4)btn4.setText(text);
        else if(num==5)btn5.setText(text);
        else if(num==6)btn6.setText(text);
        else if(num==7)btn7.setText(text);
        else if(num==8)btn8.setText(text);
        else if(num==9)btn9.setText(text);
        else if(num==10)btn10.setText(text);
        else if(num==11)btn11.setText(text);
        else if(num==12)btn12.setText(text);
        else if(num==13)btn13.setText(text);
        else if(num==14)btn14.setText(text);
        else if(num==15)btn15.setText(text);
        else if(num==16)btn16.setText(text);
    }

    public void timerTask() {

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        };
    }
    public void countDownTime() {
        countDownTimer = new CountDownTimer(10*1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int num = (int) (millisUntilFinished / 1000);

                int s = (int)(num+1);
                textClock.setText(Integer.toString(s));
                if(num+1==0){
                    countDownTimer.onFinish();
                }
            }

            @Override
            public void onFinish() {
                timerTask.cancel();
                timerTask=null;
            }
        };
    }
}