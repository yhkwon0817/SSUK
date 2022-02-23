package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    static final String[] LIST_MENU = {"4글자 게임","사진 게임","붕붕붕 게임","라이어 게임","노래 맞추기"};
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,LIST_MENU);

        ListView listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), FourWordsGameSetting.class);
                if(i == 0)
                    intent = new Intent(getApplicationContext(), FourWordsGameSetting.class);

                if(i == 1)
                    intent = new Intent(getApplicationContext(), PictureGame.class);

                if(i == 2)
                    intent = new Intent(getApplicationContext(), CarGameSetting.class);

                if(i == 3)
                    intent = new Intent(getApplicationContext(), LiarGame.class);

                startActivity(intent);
            }
        });
    }
}