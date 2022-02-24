package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class LiarGameSetting extends AppCompatActivity {
    public int people=0;
    public int minute=0;
    public int second=0;
    public int category_number=0;

    String[] items_numbers = {"4", "5", "6", "7", "8", "9"};//인원 수
    String[] items_numbers_m = {"0", "1", "2", "3", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};//분
    String[] items_numbers_s = {"0", "1", "2", "3", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59"};//초
    String[] items_category = {"카테고리 선택", "동물", "영화", "유명인사", "가전제품", "만화", "사자성어"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liar_game_setting);

        Button btn = (Button)findViewById(R.id.startBtn);
        Spinner spinner_people = (Spinner) findViewById(R.id.spinner_number_of_people);
        Spinner spinner_minute = (Spinner) findViewById(R.id.spinner_minute);
        Spinner spinner_second = (Spinner) findViewById(R.id.spinner_second);
        Spinner spinner_category = (Spinner) findViewById(R.id.spinner_category);

        ArrayAdapter<String> adapter_people = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items_numbers);
        ArrayAdapter<String> adapter_minute = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items_numbers_m);
        ArrayAdapter<String> adapter_second = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items_numbers_s);
        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items_category);

        adapter_people.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_minute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_second.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_people.setAdapter(adapter_people);
        spinner_minute.setAdapter(adapter_minute);
        spinner_second.setAdapter(adapter_second);
        spinner_category.setAdapter(adapter_category);

        spinner_people.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                people= Integer.parseInt(items_numbers[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_minute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                minute= Integer.parseInt(items_numbers_m[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_second.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                second=Integer.parseInt(items_numbers_s[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category_number=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (people ==0 || minute*60+second == 0 || category_number ==0 ) {
                    Toast.makeText(getApplicationContext(), "항목을 선택하여 주시길 바랍니다.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(LiarGameSetting.this, LiarGame.class);
                intent.putExtra("사람 수", people);
                intent.putExtra("시간제한_분", minute);
                intent.putExtra("시간제한_초", second);
                intent.putExtra("카테고리 넘버", category_number);
                startActivity(intent);
            }
        });
    }
}