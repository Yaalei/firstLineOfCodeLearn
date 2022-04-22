package com.example.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 实现一个自定义通知功能
 */
public class MainActivity extends AppCompatActivity {

    NotificationManager manager;
    // 设置通道属性
    private String channalId = "mychannel";
    private String channelName = "Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建一个NotificationManage对象对通知进行管理
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // 在android8以后的版本需要创建一个通知的channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channalId,channelName, NotificationManager.IMPORTANCE_DEFAULT);
            // 将channel传入NotificationManager对象中
            manager.createNotificationChannel(channel);
        }

        // 给发送通知按钮绑定发送通知逻辑
        Button sendNotification = findViewById(R.id.send_notice);
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                // 利用Builder构造器创建一个Notification对象，并设置Notification的相关属性
                Notification notification = new NotificationCompat.Builder(MainActivity.this, channalId)
                        .setContentTitle("重要通知")  // 通知标题
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("接上级要求，现在立马起立背一首李白的《静夜思》" +
                                "窗前明月光，疑是地上霜，举头望明月，低头思故乡")) // 通知内容
                        .setSmallIcon(R.drawable.small_icon) // 设置通知小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.large_icon)) // 设置通知大图标
                        .setContentIntent(pi)  // 设置点击事件
                        .setAutoCancel(true)  // 设置点击自动取消
                        .build();
                // 调用这个方法将通知显式出来
                manager.notify(1,notification);
            }
        });

    }

}
