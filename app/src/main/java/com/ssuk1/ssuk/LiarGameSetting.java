package com.ssuk1.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LiarGameSetting extends AppCompatActivity {
    public int people = 6;
    public int minute = 0;
    public int second = 0;
    public int time_gap = 30;

    public int category_number = 0;
    public int mode = 0;
    public int length = 6;//카테고리 수

    String[] items_category = {"동물", "영화", "유명인사", "가전제품", "만화", "사자성어"};
    String[] items_modes = {"일반", "스파이", "바보"};//모드

    ImageView image_mode_normal, image_mode_mafia, image_mode_fool;
    TextView text_mode, text_clock;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liar_game_setting);

        image_mode_normal = findViewById(R.id.normal_mode);
        image_mode_mafia = findViewById(R.id.mafia_mode);
        image_mode_fool = findViewById(R.id.fool_mode);
        text_mode = findViewById(R.id.mode);
        text_clock = findViewById(R.id.text_clock);
        ImageButton btn_back_mode = (ImageButton) findViewById(R.id.back_mode);
        ImageButton btn_next_mode = (ImageButton) findViewById(R.id.next_mode);
        ImageButton btn_back_category = (ImageButton) findViewById(R.id.back_category);
        ImageButton btn_next_category = (ImageButton) findViewById(R.id.next_category);
        ImageButton btn_back_people = (ImageButton) findViewById(R.id.back_people);
        ImageButton btn_next_people = (ImageButton) findViewById(R.id.next_people);
        ImageButton btn_back_clock = (ImageButton) findViewById(R.id.back_clock);
        ImageButton btn_next_clock = (ImageButton) findViewById(R.id.next_clock);
        TextView text_category = (TextView) findViewById(R.id.text_category);
        TextView text_people = (TextView) findViewById(R.id.text_people);
        Button btn = (Button) findViewById(R.id.startBtn);
        ImageView btn_liar_game_hint = (ImageView) findViewById(R.id.btn_liar_game_hint);
        ImageView btn_close_liar_game_hint = (ImageView) findViewById(R.id.btn_close_liar_game_hint);
        LinearLayout layout_liar_game_hint = (LinearLayout)findViewById(R.id.layout_liar_game_hint);

        text_category.setText(items_category[category_number]);
        text_mode.setText(items_modes[mode]);
        text_people.setText(Integer.toString(people));
        setClockText();

        btn_liar_game_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LiarGameIntroduction.class);
                startActivity(intent);
            }
        });

        btn_back_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInvisiblePicture();
                mode = (mode + 2) % 3;
                setVisiblePicture();
            }
        });

        btn_next_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInvisiblePicture();
                mode = (mode + 1) % 3;
                setVisiblePicture();
            }
        });

        btn_back_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_number = (category_number + length - 1) % length;
                text_category.setText(items_category[category_number]);
            }
        });

        btn_next_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_number = (category_number + 1) % length;
                text_category.setText(items_category[category_number]);
            }
        });

        btn_back_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (people == 4) people = 20;
                else people--;
                text_people.setText(Integer.toString(people));
            }
        });

        btn_next_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (people == 20) people = 4;
                else people++;
                text_people.setText(Integer.toString(people));
            }
        });

        btn_back_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (minute != 0 || second != 0) {
                    if (second == 0) {
                        minute--;
                        second += 60;
                    }
                    second -= time_gap;
                    setClockText();
                }
            }
        });

        btn_next_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                second += time_gap;
                if (second >= 60) {
                    second -= 60;
                    minute++;
                }
                setClockText();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (people == 0 || minute * 60 + second == 0) {
                    Toast.makeText(getApplicationContext(), "항목을 선택하여 주시길 바랍니다.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(LiarGameSetting.this, LiarGame.class);
                intent.putExtra("사람 수", people);
                intent.putExtra("시간제한_분", minute);
                intent.putExtra("시간제한_초", second);
                intent.putExtra("카테고리 넘버", category_number);
                intent.putExtra("모드", mode);
                startActivity(intent);
            }
        });


    }

    public void setInvisiblePicture() {
        if (mode == 0)
            image_mode_normal.setVisibility(View.INVISIBLE);
        else if (mode == 1)
            image_mode_mafia.setVisibility(View.INVISIBLE);
        else
            image_mode_fool.setVisibility(View.INVISIBLE);
    }

    public void setVisiblePicture() {
        SpannableStringBuilder sp = new SpannableStringBuilder(items_modes[mode]);
        if (mode == 0)
            image_mode_normal.setVisibility(View.VISIBLE);
        else if (mode == 1) {
            image_mode_mafia.setVisibility(View.VISIBLE);
            sp.setSpan(new ForegroundColorSpan(Color.RED), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            image_mode_fool.setVisibility(View.VISIBLE);
            sp.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        text_mode.setText(sp);
    }

    @SuppressLint("SetTextI18n")
    public void setClockText() {
        if (minute < 10 && second < 10)
            text_clock.setText("0" + Integer.toString(minute) + "분 0" + Integer.toString(second) + "초");
        else if (second < 10)
            text_clock.setText("" + Integer.toString(minute) + "분 0" + Integer.toString(second) + "초");
        else if (minute < 10)
            text_clock.setText("0" + Integer.toString(minute) + "분 " + Integer.toString(second) + "초");
        else
            text_clock.setText("" + Integer.toString(minute) + "분 " + Integer.toString(second) + "초");
    }
}