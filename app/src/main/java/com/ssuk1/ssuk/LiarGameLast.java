package com.ssuk1.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LiarGameLast extends AppCompatActivity {
    public int num_answer, people, num_liar, num_spy, category_number, category_number_fool, mode;
    public int answer_place;
    public String answer = "";
    public int[] check = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public String[][] array = {
            {"소", "라마", "순록", "염소", "당나귀", "고양이", "패럿", "개", "족제비", "햄스터",
                    "거북", "자라", "앵무새", "방울새", "금붕어", "송사리", "팬더마우스", "카멜레온", "이구아나", "타란튤라",
                    "장수풍뎅이", "꽃무지", "나비", "전갈", "지네", "노래기", "새우", "갑오징어", "달팽이", "맹꽁이",
                    "벌새", "해파리", "주머니쥐", "고라니", "박쥐", "캥거루", "삵", "하이에나", "말", "닭",
                    "사자", "호랑이", "곰", "퓨마", "낙타", "기린", "다람쥐", "원숭이", "아르마딜로", "돌고래"
            },
            {"어바웃타임", "it", "밀정", "탐정", "노트북", "인턴", "좋은놈 나쁜놈 이상한놈", "미녀와 야수", "어벤져스", "2012",
                    "트랜스포머", "킹스맨", "모가디슈", "타이타닉", "괴물", "메이즈 러너", "승리호", "라이언 일병 구하기", "트루먼 쇼", "유주얼 서스팩트",
                    "이터널 션사인", "그래비티", "마션", "노인을위한나라는없다", "셜록홈즈", "레미제마블", "클로젯", "지금 만나러 갑니다", "쇼생크 탈출", "포드 V 페라리",
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
    Timer timer;
    TimerTask timerTask;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16;
    Button btn_push;
    ImageButton btn_back, btn_home;
    TextView textClock, text_after_choose;
    LinearLayout layout_answers, layout_choose_liar, layout_back_and_home;
    EditText edit_liar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liar_game_last);

        Intent secondIntent = getIntent();
        people = secondIntent.getIntExtra("사람 수", 0);
        num_answer = secondIntent.getIntExtra("정답 번호", 0);
        num_liar = secondIntent.getIntExtra("라이어", 0);
        num_spy = secondIntent.getIntExtra("스파이", 0);
        category_number = secondIntent.getIntExtra("카테고리 넘버", 0);
        category_number_fool = secondIntent.getIntExtra("바보의 제시어", 0);
        mode = secondIntent.getIntExtra("모드", 0);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btn16 = findViewById(R.id.btn16);
        btn_home = findViewById(R.id.btn_home);
        btn_back = findViewById(R.id.btn_back);
        textClock = findViewById(R.id.text_clock);
        text_after_choose = findViewById(R.id.text_after_choose);
        layout_answers = findViewById(R.id.layout_answers);
        layout_choose_liar = findViewById(R.id.layout_choose_liar);
        layout_back_and_home = findViewById(R.id.layout_back_and_home);
        btn_push = findViewById(R.id.btn_push);
        edit_liar = findViewById(R.id.edit_liar);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LiarGameLast.this, LiarGameSetting.class);
                startActivity(intent);
                finish();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LiarGameLast.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_push.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                try {
                    int push = Integer.parseInt(edit_liar.getText().toString());
                    if ((Integer) push >= 1 && (Integer) push <= people) {
                        layout_choose_liar.setVisibility(View.GONE);
                        layout_back_and_home.setVisibility(View.VISIBLE);
                        if (mode == 1 && push == num_spy) {
                            text_after_choose.setText("스파이 검거 완료! 끝");
                        } else if (push == num_liar) {
                            if (mode == 0)
                                text_after_choose.setText("라이어 검거!!\n라이어는 제시어를 맞추시길 바랍니다.\n제한시간 10초");
                            else
                                text_after_choose.setText("바보 검거!!\n바보는 제시어를 맞추시길 바랍니다.\n제한시간 10초");
                            layout_answers.setVisibility(View.VISIBLE);
                            answer = array[category_number][num_answer];
                            answer_place = (int) (Math.random() * 16);

                            int i = 1;
                            int randN = (int) (Math.random() * 16);
                            int chk = 0;

                            while (i <= 16) {
                                if (check[randN] == 0) {
                                    if (array[category_number][randN].equals(answer)) chk++;
                                    setButton(i, array[category_number][randN]);
                                    check[randN] = 1;
                                    i++;
                                }
                                randN = (int) (Math.random() * 16);
                            }
                            if (chk == 0) setButton(answer_place, answer);

                            startTimerTask();

                            btn1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn1.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn2.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn3.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn4.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn5.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn6.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn7.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn7.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn8.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn8.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn9.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn9.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn10.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn10.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn11.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn11.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn12.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn12.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn13.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn13.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn14.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn14.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn15.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn15.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn16.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn16.getText().toString().equals(answer)) {
                                        if (mode == 0) text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        else if (mode == 1)
                                            text_after_choose.setText("정답입니다.\n라이어, 스파이 승리");
                                        else text_after_choose.setText("정답입니다.\n바보 승리");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은 " + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });

                        } else {
                            layout_answers.setVisibility(View.GONE);
                            textClock.setVisibility(View.GONE);
                            if(mode == 0)
                                text_after_choose.setText("틀렸습니다.\n라이어는 바로\n" + num_liar + "번 플레이어 입니다!");
                            else if (mode == 1)
                                text_after_choose.setText("틀렸습니다.\n라이어, 스파이는 각각\n" + num_liar + ", " + num_spy + "번 플레이어 입니다!");
                            else
                                text_after_choose.setText("틀렸습니다.\n바보는 바로\n" + num_liar + "번 플레이어 입니다!");
                        }
                    } else Toast.makeText(getApplicationContext(),
                            "알맞은 숫자를 기입하시길 바랍니다.", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자만 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setButton(int num, String text) {
        if (num == 1) btn1.setText(text);
        else if (num == 2) btn2.setText(text);
        else if (num == 3) btn3.setText(text);
        else if (num == 4) btn4.setText(text);
        else if (num == 5) btn5.setText(text);
        else if (num == 6) btn6.setText(text);
        else if (num == 7) btn7.setText(text);
        else if (num == 8) btn8.setText(text);
        else if (num == 9) btn9.setText(text);
        else if (num == 10) btn10.setText(text);
        else if (num == 11) btn11.setText(text);
        else if (num == 12) btn12.setText(text);
        else if (num == 13) btn13.setText(text);
        else if (num == 14) btn14.setText(text);
        else if (num == 15) btn15.setText(text);
        else if (num == 16) btn16.setText(text);
    }

    public void startTimerTask() {
        stopTimerTask();

        timer = new Timer();
        timerTask = new TimerTask() {
            int count = 10;

            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                textClock.post(new Runnable() {
                    @Override
                    public void run() {
                        if (count == -1) {
                            textClock.setVisibility(View.GONE);
                            if (mode == 1)
                                text_after_choose.setText("실패!\n정답은 "+answer+"이고\n스파이는 " + num_spy + "번 플레이어 입니다!");
                            else
                                text_after_choose.setText("실패!\n정답은 "+answer+"입니다.");
                            layout_answers.setVisibility(View.GONE);
                        } else if (count < -1) {
                            stopTimerTask();
                        } else textClock.setText(Integer.toString(count));
                    }
                });
                count--;

            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    private void stopTimerTask() {
        if (timerTask != null) {
            textClock.setVisibility(View.GONE);
            timerTask.cancel();
            timerTask = null;
        }
    }

    @Override
    public void onBackPressed(){
    }
}