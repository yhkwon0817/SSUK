package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LiarGame extends AppCompatActivity {
    public int people=0;
    public int minute=0;
    public int second=0;
    public int category_number=0;
    public String answer="";
    public int randN_topic;
    public int randN_liar;
    public int i=0;

    public String[][] array={{""},
            {"사자", "호랑이", "곰", "퓨마", "낙타", "기린", "다람쥐", "원숭이", "아르마딜로", "돌고래",
                    "박쥐", "캥거루", "삵", "하이에나", "말", "닭"},
            {"어바웃타임", "it", "밀정", "탐정", "노트북", "인턴", "좋은놈 나쁜놈 이상한놈", "미녀와 야수", "어벤져스", "2012",
                    "트랜스포머", "킹스맨"},
            {"김연아", "아이유", "레오나르도 다빈치", "장영실", "방탄소년단", "유재석", "강동원", "마이클 잭슨", "저스틴 비버", "페이커",
                    "손흥민"},
            {"냉장고", "전자렌지", "전기모기채", "전기장판", "리모컨", "TV"}

    };
    public String[] items_category = {" ", "동물", "영화", "유명인사", "가전제품", "만화", "사자성어"};

    Timer timer;
    TimerTask timerTask;
    CountDownTimer countDownTimer;

    TextView min, sec;
    LinearLayout layout_timer;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liar_game);

        Intent secondIntent = getIntent();
        people=secondIntent.getIntExtra("사람 수", 0);
        minute=secondIntent.getIntExtra("시간제한_분", 0);
        second=secondIntent.getIntExtra("시간제한_초", 0);
        category_number=secondIntent.getIntExtra("카테고리 넘버", 0);

        randN_topic=(int)(Math.random()*16);//*topic 의 수
        randN_liar=(int)(Math.random()*people);

        TextView text_topic=(TextView) findViewById(R.id.text_topic);
        text_topic.setText(items_category[category_number]);
        answer=array[category_number][randN_topic];

        TextView text_isLiar = (TextView) findViewById(R.id.text_isLiar);
        btn_next = findViewById(R.id.btn_next);
        min = findViewById(R.id.min);
        sec = findViewById(R.id.sec);
        layout_timer = findViewById(R.id.layout_timer);

        btn_next.setText("제시어 확인하기");
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i>people*2+1){
                    Intent intent = new Intent(LiarGame.this, LiarGameLast.class);
                    intent.putExtra("정답 번호", randN_topic);
                    intent.putExtra("라이어", randN_liar);
                    intent.putExtra("카테고리 넘버", category_number);
                    startActivity(intent);
                }
                else if(i==people*2+1){
                    text_isLiar.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.GONE);
                    text_isLiar.setText("범인 색출 시작!\n누가 라이어인지\n맞춰 봅시다!");
                    layout_timer.setVisibility(View.VISIBLE);
                    timerTask();
                    countDownTime();

                    timer.schedule(timerTask, 0, (minute + 1) * 60000);
                }
                else if(i==people*2){
                    btn_next.setText("게임 시작");
                    text_isLiar.setVisibility(View.GONE);
                }
                else if(i%2==0){
                    btn_next.setText("제시어 확인하기");
                    text_isLiar.setVisibility(View.GONE);
                }
                else if(i==randN_liar*2+1){
                    text_isLiar.setVisibility(View.VISIBLE);
                    text_isLiar.setText("당신은\n라이어입니다.");
                    btn_next.setText("확인 완료");
                }
                else if(i%2==1){
                    text_isLiar.setVisibility(View.VISIBLE);
                    text_isLiar.setText(answer);
                    btn_next.setText("확인 완료");
                }
            }
        });

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
        countDownTimer = new CountDownTimer(minute * 60000 + second*1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int num = (int) (millisUntilFinished / 1000);

                if(num+1==0){
                    countDownTimer.onFinish();
                }

                int m = (int)((num+1)/60);
                int s = (int)((num+1)%60);
                min.setText(Integer.toString(m));
                if(s>=0&&s<10)sec.setText("0"+Integer.toString(s));
                else sec.setText(Integer.toString(s));
            }

            @Override
            public void onFinish() {
                layout_timer.setVisibility(View.INVISIBLE);
                btn_next.setVisibility(View.VISIBLE);
                btn_next.setText("범인 색출하기");
                timerTask.cancel();
                timerTask=null;
            }
        };
    }
}