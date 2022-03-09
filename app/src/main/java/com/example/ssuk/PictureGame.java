package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
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
import java.util.Timer;
import java.util.TimerTask;

public class PictureGame extends AppCompatActivity {

    ArrayList<Integer> problem = new ArrayList<>();

    ImageView pic;
    TextView timeview;
    Button solution;
    LinearLayout screen;
    Timer timer;
    TimerTask timerTask;

    int repeat, time, index_ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_game);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       /* mSoundPool = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes mAudioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            new SoundPool.Builder()
                    .setAudioAttributes(mAudioAttributes)
                    .setMaxStreams(2).build();
        }
        SoundPool.Builder().*/

        timeview = findViewById(R.id.time);
        screen = findViewById(R.id.screen);
        solution = findViewById(R.id.btn);
        pic = findViewById(R.id.image);
        
        //답 버튼은 아직
        //solution.setOnClickListener(onClickListener);
        screen.setOnTouchListener(onTouchListener);
        
        Intent setting = getIntent();

        repeat = setting.getIntExtra("repeat_set", 0);
        time = setting.getIntExtra("time_set", 0);
        String category = setting.getStringExtra("category");

        putProblem();
        startTimerTask();
    }

    private void putProblem() {
        problem.add(R.drawable.pg1);
        problem.add(R.drawable.pg2);
        problem.add(R.drawable.pg3);
        problem.add(R.drawable.pg4);
        problem.add(R.drawable.pg5);
        problem.add(R.drawable.pg6);
    }

    public void startTimerTask(){

        stopTimerTask();
        change_pb();

        if(index_ == repeat + 1){
            // 종료 이미지 넣어야 함
            pic.setImageResource(R.drawable.endpg2);
            timeview.setText("");
            solution.setVisibility(View.GONE);
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
                            if(count == -1) timeview.setText("끝");
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
    /*View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn:

                    //Toast.makeText(getApplicationContext(), )
                
            }
        }
    };*/

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(index_ < repeat + 1){
                startTimerTask();
                return false;
            }
            else return false;
        }
    };
    
    

    private void change_pb() {
        pic.setImageResource(problem.get(index_));
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