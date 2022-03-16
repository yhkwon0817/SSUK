package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FourWordsGameSetting extends AppCompatActivity {
    EditText timeset, repeat;
    Button btn;
    ImageButton next, pre;
    TextView category;
    MediaPlayer startsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_words_game_setting);

        timeset = findViewById(R.id.timeset);
        repeat = findViewById(R.id.repeat);
        btn = findViewById(R.id.startbtn);
        category = findViewById(R.id.category);

        next = findViewById(R.id.next_mode);
        pre = findViewById(R.id.back_mode);

        ImageView btn_four_word_game_hint = (ImageView) findViewById(R.id.btn_four_word_game_hint);
        ImageView btn_close_four_word_game_hint = (ImageView) findViewById(R.id.btn_close_four_word_game_hint);
        LinearLayout layout_four_word_game_hint = (LinearLayout)findViewById(R.id.layout_four_word_game_hint);

        btn_four_word_game_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FourWordsGameIntroduction.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(onClickListener);
        next.setOnClickListener(onClickListener);
        pre.setOnClickListener(onClickListener);

        startsound = MediaPlayer.create(this, R.raw.round_start);

        category.setText("일상용어");

    }

    // click listener 한번에 관리해주기
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.startbtn:
                    if(repeat.getText().length() <= 0 || timeset.getText().length() <= 0) {
                        Toast.makeText(FourWordsGameSetting.this, "시간, 반복횟수를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String setting_category = category.getText().toString();
                        int setting_repeat = Integer.parseInt(repeat.getText().toString());
                        int setting_time = Integer.parseInt(timeset.getText().toString());
                        //Log.e("###", Integer.toString(setting_repeat) + " " + Integer.toString(setting_time));

                        if(setting_repeat > 0 && setting_repeat < 20 && setting_time >= 2 && setting_time <= 15){

                            startsound.start();

                            Intent intent = new Intent(FourWordsGameSetting.this, FourWordsGame.class);

                            intent.putExtra("setting_repeat", setting_repeat);
                            intent.putExtra("setting_time", setting_time);
                            intent.putExtra("setting_category", setting_category);

                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(FourWordsGameSetting.this, "시간, 반복횟수를 다시 확인해주세요 !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                case R.id.back_mode:
                case R.id.next_mode:
                    if(category.getText().equals("일상용어")) category.setText("사자성어");
                    else category.setText("일상용어");
                    break;

            }
        }
    };
}
