package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FourWordsGameSetting extends AppCompatActivity {
    EditText timeset, repeat;
    Spinner spinner;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_words_game_setting);

        spinner = findViewById(R.id.category);
        timeset = findViewById(R.id.timeset);
        repeat = findViewById(R.id.repeat);
        btn = findViewById(R.id.startbtn);

        btn.setOnClickListener(onClickListener);

        //카테고리 스피너 설정
        ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(this, R.array.fourwordcategory, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(categoryAdapter);
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
                        String setting_category = spinner.getSelectedItem().toString();
                        int setting_repeat = Integer.parseInt(repeat.getText().toString());
                        int setting_time = Integer.parseInt(timeset.getText().toString());
                        //Log.e("###", Integer.toString(setting_repeat) + " " + Integer.toString(setting_time));

                        if(setting_repeat > 0 && setting_repeat < 20 && setting_time >= 2 && setting_time <= 15){

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
            }
        }
    };
}
