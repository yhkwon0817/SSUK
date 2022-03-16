package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LiarGame extends AppCompatActivity {
    public int people = 0;
    public int minute = 0;
    public int second = 0;
    public int category_number = 0;
    public String answer = "";
    public int randN_topic, randN_topic_fool, randN_liar, randN_spy;
    public int i = 0;
    public int mode = 0;

    public String[][] array = {
            {"소", "라마", "순록", "염소", "당나귀", "고양이", "패럿", "개", "족제비", "햄스터",
                    "거북", "자라", "앵무새", "방울새", "금붕어", "송사리", "팬더마우스", "카멜레온", "이구아나", "타란튤라",
                    "장수풍뎅이", "꽃무지", "나비", "전갈", "지네", "노래기", "새우", "갑오징어", "달팽이", "맹꽁이",
                    "벌새", "해파리", "주머니쥐", "고라니", "박쥐", "캥거루", "삵", "하이에나", "말", "닭",
                    "사자", "호랑이", "곰", "퓨마", "낙타", "기린", "다람쥐", "원숭이", "아르마딜로", "돌고래"
            },
            {"어바웃타임", "it", "밀정", "탐정", "노트북", "인턴", "좋은놈 나쁜놈 이상한놈", "미녀와 야수", "어벤져스", "2012",
                    "트랜스포머", "킹스맨", "모가디슈", "타이타닉", "괴물", "메이즈 러너", "승리호", "라이언 일병 구하기", "트루먼 쇼", "유주얼 서스팩트",
                    "이터널 션사인", "그래비티", "마션", "노인을 위한 나라는 없다", "셜록홈즈", "레미제마블", "클로젯", "지금 만나러 갑니다", "쇼생크 탈출", "포드 V 페라리",
                    "아바타", "장난스런 키스", "내 머릿속의 지우개", "미녀와 야수", "미녀는 괴로워", "인셉션", "her", "캐치 미 이프유 캔", "위대한 개츠비", "블러드 다이아몬드",
                    "킹메이커", "모럴센스", "나홀로 집에", "보헤미안 랩소디", "빽 투터 퓨쳐", "포레스트 검포", "위대한 쇼맨", "인생은 아름다워", "살인의 추억", "반지의 제왕"
            },
            {"김연아", "아이유", "레오나르도 다빈치", "장영실", "방탄소년단", "유재석", "강동원", "마이클 잭슨", "저스틴 비버", "페이커",
                    "손흥민", "간디", "스티브 잡스", "스티븐 호킹", "스티븐 스필버그", "메시", "호날두", "성룡", "짐 캐리", "이세돌",
                    "박근혜", "이재명", "윤석열", "타이거 우즈", "닐 암스트롱", "판빙빙", "류현진", "아리아나 그란데", "아델", "마이클 조던",
                    "크리스 에반스", "모나리자", "유관순", "엘론 머스크", "워렌 버핏", "빌 게이츠", "버락 오바마", "안중근", "링컨", "이순신",
                    "에디슨", "아인슈타인", "율곡 이이", "김구", "김광석", "처칠", "베토벤", "모차르트", "강호동", "공유"
            },
            {"냉장고", "전자렌지", "전기모기채", "전기장판", "리모컨", "TV","전자수첩", "안마기", "닌텐도", "노트북",
                    "재봉틀", "전기다리미", "전기세탁기", "빨래건조기", "전기면도기", "헤어드라이이", "전자비데", "전동칫솔", "가스레인지", "전기밥솥",
                    "믹서", "에어프라이어", "에스프레소머신", "반죽기", "제면기", "제빙기", "정수기", "식기세척기", "보조배터리", "시계",
                    "손전등", "스탠드", "멀티탭", "전기장판", "온풍기", "에어컨", "선풍기", "공기청정기", "진공청소기", "라디오",
                    "스피커", "MP3플레이어", "녹음기", "마이크", "이어폰", "캠코더", "키보드", "마우스", "스캐너", "복사기"
            }

    };
    public String[] items_category = {"동물", "영화", "유명인사", "가전제품", "만화", "사자성어"};
    public String[] items_modes = {"일반", "스파이", "바보"};//모드

    Timer timer;
    TimerTask timerTask;
    CountDownTimer countDownTimer;

    TextView min, sec;
    LinearLayout layout_timer;
    Button btn_next;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liar_game);

        Intent secondIntent = getIntent();
        people = secondIntent.getIntExtra("사람 수", 0);
        minute = secondIntent.getIntExtra("시간제한_분", 0);
        second = secondIntent.getIntExtra("시간제한_초", 0);
        category_number = secondIntent.getIntExtra("카테고리 넘버", 0);
        mode = secondIntent.getIntExtra("모드", 0);

        //randNum 구현
        //rand people
        randN_liar = (int) (Math.random() * people);
        randN_spy = (int) (Math.random() * people);
        while (randN_liar == randN_spy)
            randN_spy = (int) (Math.random() * people);
        //rand topic
        randN_topic = (int) (Math.random() * 50);//*topic 의 수
        randN_topic_fool = (int) (Math.random() * 50);
        while (randN_topic_fool == randN_topic)
            randN_topic_fool = (int) (Math.random() * 50);

        TextView text_topic = (TextView) findViewById(R.id.text_topic);
        TextView text_isLiar = (TextView) findViewById(R.id.text_isLiar);
        TextView text_mode = (TextView) findViewById(R.id.text_mode);
        btn_next = findViewById(R.id.btn_next);
        min = findViewById(R.id.min);
        sec = findViewById(R.id.sec);
        layout_timer = findViewById(R.id.layout_timer);

        text_topic.setText(items_category[category_number]);
        text_mode.setText(items_modes[mode]);
        answer = array[category_number][randN_topic];

        btn_next.setText("제시어 확인하기");
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i > people * 2 + 1) {
                    Intent intent = new Intent(LiarGame.this, LiarGameLast.class);
                    intent.putExtra("사람 수", people);
                    intent.putExtra("정답 번호", randN_topic);
                    intent.putExtra("라이어", randN_liar + 1);
                    intent.putExtra("바보의 제시어", randN_topic_fool);
                    intent.putExtra("스파이", randN_spy + 1);
                    intent.putExtra("카테고리 넘버", category_number);
                    intent.putExtra("모드", mode);
                    startActivity(intent);
                    finish();
                } else if (i == people * 2 + 1) {
                    text_isLiar.setVisibility(View.VISIBLE);
                    layout_timer.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.VISIBLE);
                    btn_next.setText("범인 색출하기");
                    //모드
                    if (mode == 0) {
                        SpannableStringBuilder sp = new SpannableStringBuilder("범인 색출 시작!\n누가 라이어인지\n맞춰 봅시다!");
                        sp.setSpan(new ForegroundColorSpan(Color.RED), 13, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        text_isLiar.setText(sp);
                    } else if (mode == 1) {
                        SpannableStringBuilder sp = new SpannableStringBuilder("범인 색출 시작!\n누가 라이어 혹은\n스파이인지\n맞춰 봅시다!");
                        sp.setSpan(new ForegroundColorSpan(Color.RED), 13, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        sp.setSpan(new ForegroundColorSpan(Color.RED), 20, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        text_isLiar.setText(sp);
                    } else {
                        SpannableStringBuilder sp = new SpannableStringBuilder("범인 색출 시작!\n누가 바보인지\n맞춰 봅시다!");
                        sp.setSpan(new ForegroundColorSpan(Color.RED), 13, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        text_isLiar.setText(sp);
                    }
                    //타이머
                    timerTask();
                    countDownTime();
                    timer.schedule(timerTask, 0, (minute + 1) * 60000);
                } else if (i == people * 2) {
                    btn_next.setText("게임 시작");
                    text_isLiar.setVisibility(View.GONE);
                } else if (i % 2 == 0) {
                    btn_next.setText("제시어 확인하기");
                    text_isLiar.setVisibility(View.GONE);
                } else if (i == randN_liar * 2 + 1) {
                    text_isLiar.setVisibility(View.VISIBLE);
                    if (mode == 2) text_isLiar.setText(array[category_number][randN_topic_fool]);
                    else {
                        SpannableStringBuilder sp = new SpannableStringBuilder("당신은\n라이어입니다.");
                        sp.setSpan(new ForegroundColorSpan(Color.RED), 4, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        text_isLiar.setText(sp);
                    }
                    btn_next.setText("확인 완료");
                } else if (mode == 1 && i == randN_spy * 2 + 1) {
                    text_isLiar.setVisibility(View.VISIBLE);
                    SpannableStringBuilder sp = new SpannableStringBuilder("당신은\n스파이입니다.");
                    sp.setSpan(new ForegroundColorSpan(Color.RED), 4, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_isLiar.setText(sp);
                    btn_next.setText("확인 완료");
                } else if (i % 2 == 1) {
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

    public void stopTimerTask() {
        timerTask.cancel();
        timerTask = null;
    }

    public void countDownTime() {
        countDownTimer = new CountDownTimer(minute * 60000 + second * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int num = (int) (millisUntilFinished / 1000);

                if (num + 1 == 0) {
                    countDownTimer.onFinish();

                }

                int m = (int) ((num + 1) / 60);
                int s = (int) ((num + 1) % 60);
                min.setText(Integer.toString(m));
                if (s >= 0 && s < 10) sec.setText("0" + Integer.toString(s));
                else sec.setText(Integer.toString(s));
            }

            @Override
            public void onFinish() {
                vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                } else {
                    vibrator.vibrate(1000);
                }*/
                layout_timer.setVisibility(View.INVISIBLE);
                timerTask.cancel();
                timerTask = null;
            }

        };
    }

}