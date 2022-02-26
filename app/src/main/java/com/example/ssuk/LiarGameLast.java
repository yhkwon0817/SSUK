package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LiarGameLast extends AppCompatActivity {
    public int num_answer, people, num_liar, num_spy, category_number, category_number_fool, mode;
    public int answer_place;
    public String answer = "";
    public int[] check = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public String[][] array = {{""},
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

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16;
    Button btn_push;
    TextView textClock, text_after_choose;
    LinearLayout layout_answers, layout_choose_liar;
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
        textClock = findViewById(R.id.text_clock);
        text_after_choose = findViewById(R.id.text_after_choose);
        layout_answers = findViewById(R.id.layout_answers);
        layout_choose_liar = findViewById(R.id.layout_choose_liar);
        btn_push = findViewById(R.id.btn_push);
        edit_liar = findViewById(R.id.edit_liar);

        btn_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int push = Integer.parseInt(edit_liar.getText().toString());
                    if ((Integer) push >= 1 && (Integer) push <= people) {
                        layout_choose_liar.setVisibility(View.GONE);
                        if (mode == 1 && push == num_spy) {
                            text_after_choose.setText("스파이 검거 완료! 끝");
                        } else if (push == num_liar) {
                            text_after_choose.setText("라이어 검거!!\n라이어는 제시어를 맞추시길 바랍니다.\n제한시간 10초");
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
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn2.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn3.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn4.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn5.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn6.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn7.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn7.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn8.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn8.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn9.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn9.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn10.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn10.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn11.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn11.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn12.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn12.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn13.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn13.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn14.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn14.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn15.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn15.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });
                            btn16.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    stopTimerTask();
                                    if (btn16.getText().toString().equals(answer)) {
                                        text_after_choose.setText("정답입니다.\n라이어 승리!");
                                        layout_answers.setVisibility(View.GONE);
                                    } else
                                        text_after_choose.setText("땡!\n정답은" + answer + "입니다.");
                                    layout_answers.setVisibility(View.GONE);
                                }
                            });

                        } else {
                            text_after_choose.setText("틀렸습니다.\n라이어는 바로\n" + num_liar + "번 플레이어 입니다!");
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

            @Override
            public void run() {
                textClock.post(new Runnable() {
                    @Override
                    public void run() {
                        if (count == -1) {
                            textClock.setVisibility(View.GONE);
                            text_after_choose.setText("라이어 패배\n라이어는 바로\n" + num_liar + "번 플레이어 입니다!");
                            layout_answers.setVisibility(View.GONE);
                        } else if (count < -1) {
                            stopTimerTask();
                            return;
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
}