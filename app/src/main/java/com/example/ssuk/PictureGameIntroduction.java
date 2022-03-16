package com.example.ssuk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class PictureGameIntroduction extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.activity_picture_game_introduction);

        VideoView videoView = (VideoView) findViewById(R.id.vv);
        MediaController mediaController = new MediaController(this);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName()+ "/"+R.raw.picture_game_introduction);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.start();
    }
}