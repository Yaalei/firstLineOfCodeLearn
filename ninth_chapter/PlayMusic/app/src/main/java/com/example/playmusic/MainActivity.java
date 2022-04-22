package com.example.playmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * 实现一个可以播放音乐的demo
 * 有三个按钮，可以实现音乐的播放、暂停和停止
 */
public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建一个AssetManager对象，用于获取文件
        AssetManager assetManager = getAssets();
        try{
            AssetFileDescriptor fd =  assetManager.openFd("music.mp3");  // 设置路径
            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength()); // 绑定路径
            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }


        // 创建一个Button对象，绑定播放按钮
        Button startButton = findViewById(R.id.play);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            }
        });

        // 创建一个Button对象，绑定暂停按钮
        Button pauseButton = findViewById(R.id.pause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });

        // 创建一个Button对象，绑定停止按钮
        Button stopButton = findViewById(R.id.stop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
            }
        });
    }

    // 重写onDestroy()方法，当前Activity被销毁时，释放资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
