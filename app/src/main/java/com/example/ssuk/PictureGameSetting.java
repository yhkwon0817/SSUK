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

public class PictureGameSetting extends AppCompatActivity {

    EditText timeset, repeat;
    Spinner category;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game_setting);

        category = findViewById(R.id.category);
        timeset = findViewById(R.id.timeset);
        repeat = findViewById(R.id.repeat);
        btn = findViewById(R.id.startbtn);

        btn.setOnClickListener(onClickListener);

        ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(this, R.array.picturecategory, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(categoryAdapter);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.startbtn:
                    if(repeat.getText().length() <= 0 || timeset.getText().length() <= 0) {
                        Toast.makeText(PictureGameSetting.this, "시간, 반복횟수를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        int time_set = Integer.parseInt(timeset.getText().toString());
                        int repeat_set = Integer.parseInt(repeat.getText().toString());
                        String category_set = category.getSelectedItem().toString();

                        if(time_set > 0 && time_set < 12 && repeat_set > 4 && repeat_set < 20){
                            Intent intent = new Intent(PictureGameSetting.this, PictureGame.class);
                            intent.putExtra("time_set", time_set);
                            intent.putExtra("repeat_set", repeat_set);
                            intent.putExtra("category", category_set);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(PictureGameSetting.this, "설정을 제대로 입력해주세요 !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
            }
        }
    };
}