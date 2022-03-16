package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PictureGameSetting extends AppCompatActivity {

    EditText timeset, repeat;
    Button btn;
    ImageButton next, pre;
    TextView category;

    String[] mode = new ArrayList<String>().toArray(new String[0]);
    int idx_ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game_setting);

        timeset = findViewById(R.id.timeset);
        repeat = findViewById(R.id.repeat);
        btn = findViewById(R.id.startbtn);
        category = findViewById(R.id.category);

        next = findViewById(R.id.next_mode);
        pre = findViewById(R.id.back_mode);

        btn.setOnClickListener(onClickListener);
        next.setOnClickListener(onClickListener);
        pre.setOnClickListener(onClickListener);

        mode = getResources().getStringArray(R.array.picturecategory);
        category.setText(mode[idx_]);

    }

    // click listener 한번에 관리해주기
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.startbtn:
                    if(repeat.getText().length() <= 0 || timeset.getText().length() <= 0) {
                        Toast.makeText(PictureGameSetting.this, "시간, 반복횟수를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String setting_category = category.getText().toString();
                        int setting_repeat = Integer.parseInt(repeat.getText().toString());
                        int setting_time = Integer.parseInt(timeset.getText().toString());

                        Log.e("###", "값 받는중");

                        if(setting_repeat > 0 && setting_repeat < 20 && setting_time >= 2 && setting_time <= 15){

                            Intent intent = new Intent(PictureGameSetting.this, PictureGame.class);

                            intent.putExtra("setting_repeat", setting_repeat);
                            intent.putExtra("setting_time", setting_time);
                            intent.putExtra("setting_category", setting_category);

                            Log.e("###", "넘어갑니다");
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(PictureGameSetting.this, "시간, 반복횟수를 다시 확인해주세요 !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                case R.id.back_mode:
                    idx_ = (idx_ + 1) % 3;
                    category.setText(mode[idx_]);
                    break;
                case R.id.next_mode:
                    if(idx_ == 0) idx_ = 2;
                    else idx_--;
                    category.setText(mode[idx_]);
                    break;

            }
        }
    };
}