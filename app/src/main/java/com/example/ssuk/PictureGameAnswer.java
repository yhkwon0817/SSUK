package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PictureGameAnswer extends AppCompatActivity {

    ArrayList<PictureGameItem> problem = new ArrayList<>();
    ArrayList<PictureGameItem> getProblem;
    int repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game_answer);

        ListView listView = findViewById(R.id.listView);

        Intent get = getIntent();

        getProblem = (ArrayList<PictureGameItem>) get.getSerializableExtra("Ans_list");
        repeat = get.getIntExtra("Ans_repeat", 0);

        /*problem.add(getProblem.get(1));
*/
        for(int i = 0; i < repeat; i++){
            problem.add(new PictureGameItem(getProblem.get(i).getId_(), getProblem.get(i).getName()));
        }

        PictureGameListAdapter mAdapter = new PictureGameListAdapter(getApplicationContext(), problem);
        listView.setAdapter(mAdapter);

    }
}