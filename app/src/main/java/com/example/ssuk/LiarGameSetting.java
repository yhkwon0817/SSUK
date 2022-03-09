package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LiarGameSetting extends AppCompatActivity {
    public int people = 4;
    public int minute = 0;
    public int second = 0;

    public int category_number = 0;
    public int mode = 0;
    public int length = 6;//카테고리 수

    String[] items_numbers = {"4", "5", "6", "7", "8", "9"};//인원 수
    String[] items_numbers_m = {"0", "1", "2", "3", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};//분
    String[] items_numbers_s = {"0", "1", "2", "3", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59"};//초
    String[] items_category = {"동물", "영화", "유명인사", "가전제품", "만화", "사자성어"};
    String[] items_modes = {"일반", "스파이", "바보"};//모드

    ImageView image_mode_normal, image_mode_mafia, image_mode_fool;
    TextView text_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liar_game_setting);

        image_mode_normal = findViewById(R.id.normal_mode);
        image_mode_mafia = findViewById(R.id.mafia_mode);
        image_mode_fool = findViewById(R.id.fool_mode);
        text_mode = findViewById(R.id.mode);
        ImageButton btn_back_mode = (ImageButton) findViewById(R.id.back_mode);
        ImageButton btn_next_mode = (ImageButton) findViewById(R.id.next_mode);
        ImageButton btn_back_category = (ImageButton) findViewById(R.id.back_category);
        ImageButton btn_next_category = (ImageButton) findViewById(R.id.next_category);
        TextView text_category = (TextView) findViewById(R.id.text_category);
        Button btn = (Button) findViewById(R.id.startBtn);

        text_category.setText(items_category[category_number]);
        text_mode.setText(items_modes[mode]);


        Spinner spinner_people = (Spinner) findViewById(R.id.spinner_number_of_people);
        Spinner spinner_minute = (Spinner) findViewById(R.id.spinner_minute);
        Spinner spinner_second = (Spinner) findViewById(R.id.spinner_second);
        ArrayAdapter<String> adapter_people = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items_numbers);
        ArrayAdapter<String> adapter_minute = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items_numbers_m);
        ArrayAdapter<String> adapter_second = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items_numbers_s);
        adapter_people.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_minute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_second.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_people.setAdapter(adapter_people);
        spinner_minute.setAdapter(adapter_minute);
        spinner_second.setAdapter(adapter_second);

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

        spinner_people.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                people = Integer.parseInt(items_numbers[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_minute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                minute = Integer.parseInt(items_numbers_m[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_second.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                second = Integer.parseInt(items_numbers_s[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

        /*

        Spinner spinner_category = (Spinner) findViewById(R.id.spinner_category);
        Spinner spinner_mode = (Spinner) findViewById(R.id.spinner_mode);


        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items_category);
        ArrayAdapter<String> adapter_mode = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, items_modes);


        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_mode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner_category.setAdapter(adapter_category);
        spinner_mode.setAdapter(adapter_mode);



        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category_number = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mode = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        */
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
        }
        else {
            image_mode_fool.setVisibility(View.VISIBLE);
            sp.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        text_mode.setText(sp);
    }
}