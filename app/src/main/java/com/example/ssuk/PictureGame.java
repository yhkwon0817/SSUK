package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class PictureGame extends AppCompatActivity {

    ArrayList<PictureGameItem> problem = new ArrayList<>();

    String category;

    ImageView pic;
    TextView timeview;
    Button solution;

    MediaPlayer endsound, midsound;
    LinearLayout screen;
    Timer timer;
    TimerTask timerTask;

    int repeat, time, index_ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        timeview = findViewById(R.id.time);
        screen = findViewById(R.id.screen);
        solution = findViewById(R.id.btn);
        pic = findViewById(R.id.image);

        midsound = MediaPlayer.create(this, R.raw.onetwothree_sound);
        endsound = MediaPlayer.create(this, R.raw.ddang_sound);

        //답 버튼은 아직
        solution.setVisibility(View.GONE);
        solution.setOnClickListener(onClickListener);
        screen.setOnTouchListener(onTouchListener);
        
        Intent setting = getIntent();

        repeat = setting.getIntExtra("setting_repeat", 0);
        time = setting.getIntExtra("setting_time", 0);
        category = setting.getStringExtra("setting_category");

        /*Log.e("###", "반복 : " + repeat + " 카테고리 :  " + category + "시간 :  " + time);*/

        putProblem();
        startTimerTask();
    }

    private void putProblem() {

        if(category.equals("여자 배우")){
            problem.add(new PictureGameItem(R.drawable.pgw1, "손담비"));
            problem.add(new PictureGameItem(R.drawable.pgw2, "송혜교"));
            problem.add(new PictureGameItem(R.drawable.pgw3, "정은채"));
            problem.add(new PictureGameItem(R.drawable.pgw4, "김아중"));
            problem.add(new PictureGameItem(R.drawable.pgw5, "김태리"));
            problem.add(new PictureGameItem(R.drawable.pgw6, "김다미"));
            problem.add(new PictureGameItem(R.drawable.pgw7, "박소담"));
            problem.add(new PictureGameItem(R.drawable.pgw8, "강예원"));
            problem.add(new PictureGameItem(R.drawable.pgw9, "조이현"));
            problem.add(new PictureGameItem(R.drawable.pgw10, "손예진"));
            problem.add(new PictureGameItem(R.drawable.pgw11, "진지희"));
            problem.add(new PictureGameItem(R.drawable.pgw12, "장나라"));
            problem.add(new PictureGameItem(R.drawable.pgw13, "박수진"));
            problem.add(new PictureGameItem(R.drawable.pgw14, "전소민"));
            problem.add(new PictureGameItem(R.drawable.pgw15, "김지원"));
            problem.add(new PictureGameItem(R.drawable.pgw16, "박규영"));
            problem.add(new PictureGameItem(R.drawable.pgw17, "나문휘"));
            problem.add(new PictureGameItem(R.drawable.pgw18, "한소휘"));
            problem.add(new PictureGameItem(R.drawable.pgw19, "신세경"));
            problem.add(new PictureGameItem(R.drawable.pgw20, "신민아"));

        }
        else if(category.equals("남자 배우")){
            problem.add(new PictureGameItem(R.drawable.pgm1, "김수현"));
            problem.add(new PictureGameItem(R.drawable.pgm2, "이병헌"));
            problem.add(new PictureGameItem(R.drawable.pgm3, "황정민"));
            problem.add(new PictureGameItem(R.drawable.pgm4, "주지훈"));
            problem.add(new PictureGameItem(R.drawable.pgm5, "유해진"));
            problem.add(new PictureGameItem(R.drawable.pgm6, "강하늘"));
            problem.add(new PictureGameItem(R.drawable.pgm7, "마동석"));
            problem.add(new PictureGameItem(R.drawable.pgm8, "하정우"));
            problem.add(new PictureGameItem(R.drawable.pgm9, "이경영"));
            problem.add(new PictureGameItem(R.drawable.pgm10, "조진웅"));
            problem.add(new PictureGameItem(R.drawable.pgm11, "이준기"));
            problem.add(new PictureGameItem(R.drawable.pgm12, "손현주"));
            problem.add(new PictureGameItem(R.drawable.pgm13, "남궁민"));
            problem.add(new PictureGameItem(R.drawable.pgm14, "연정훈"));
            problem.add(new PictureGameItem(R.drawable.pgm15, "고수"));
            problem.add(new PictureGameItem(R.drawable.pgm16, "성지루"));
            problem.add(new PictureGameItem(R.drawable.pgm17, "최우식"));
            problem.add(new PictureGameItem(R.drawable.pgm18, "유아인"));
            problem.add(new PictureGameItem(R.drawable.pgm19, "박보검"));
            problem.add(new PictureGameItem(R.drawable.pgm20, "이선균"));

        }
        else{
            problem.add(new PictureGameItem(R.drawable.pgh1, "이순신"));
            problem.add(new PictureGameItem(R.drawable.pgh2, "김구"));
            problem.add(new PictureGameItem(R.drawable.pgh3, "세종대왕"));
            problem.add(new PictureGameItem(R.drawable.pgh4, "윤봉길"));
            problem.add(new PictureGameItem(R.drawable.pgh5, "링컨"));
            problem.add(new PictureGameItem(R.drawable.pgh6, "맥아더"));
            problem.add(new PictureGameItem(R.drawable.pgh7, "마하트마 간디"));
            problem.add(new PictureGameItem(R.drawable.pgh9, "다윈"));
            problem.add(new PictureGameItem(R.drawable.pgh10, "징기츠 칸"));
            problem.add(new PictureGameItem(R.drawable.pgh11, "나폴레옹"));
            problem.add(new PictureGameItem(R.drawable.pgh12, "히틀러"));
            problem.add(new PictureGameItem(R.drawable.pgh13, "베토벤"));
            problem.add(new PictureGameItem(R.drawable.pgh14, "체 게바라"));
            problem.add(new PictureGameItem(R.drawable.pgh15, "뉴턴"));
            problem.add(new PictureGameItem(R.drawable.pgh16, "스티브 잡스"));
            problem.add(new PictureGameItem(R.drawable.pgh17, "셰익스피어"));
        }
        Collections.shuffle(problem);
    }

    public void startTimerTask(){

        stopTimerTask();
        change_pb();

        if(index_ == repeat + 1){
            // 종료 이미지 넣어야 함
            pic.setImageResource(R.drawable.endpg2);
            timeview.setText("");
            solution.setVisibility(View.VISIBLE);
            return;
        }
        else{
            timer = new Timer();
            timerTask = new TimerTask() {

                int count = time;

                @Override
                public void run() {
                    timeview.post(new Runnable() {
                        @Override
                        public void run() {
                            if(count == 0) midsound.start();
                            if(count == -1) {
                                endsound.start();
                                timeview.setText("끝");
                            }
                            else if(count < -1){
                                startTimerTask();
                                return;
                            }
                            else timeview.setText(Integer.toString(count));
                        }
                    });
                    count--;
                }
            };
            timer.schedule(timerTask, 0, 1000);
        }
    }

    //답 어떻게 가져올지 생각
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn:
                    Intent intent = new Intent(PictureGame.this, PictureGameAnswer.class);
                    intent.putExtra("Ans_list", problem);
                    intent.putExtra("Ans_repeat", repeat);
                    startActivity(intent);
            }
        }
    };

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            /*Log.e("###", "" + index_);*/
            if(index_ < repeat + 1){
                startTimerTask();
                return false;
            }
            else return false;
        }
    };
    
    

    private void change_pb() {
        pic.setImageResource(problem.get(index_).getId_());
        index_++;
    }

    private void stopTimerTask() {
        if(timerTask != null)
        {
            timerTask.cancel();
            timerTask = null;
        }
    }


}