package com.example.broadcastforceoff;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 定义一个辅助类BaseActivity
 * 在类中定义*/

public class BaseActivity extends AppCompatActivity {
    ForceOffOnlineRecever receiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    // 当处于栈顶时，才会接收到强制下线的广播
    @Override
    protected void onResume() {
        super.onResume();
        // 定义接收到广播类型
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastforceoff.FORCE_OFF_LOGIN");
        receiver = new ForceOffOnlineRecever();  // 创建自定义广播对象
        // 注册广播
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 失去栈顶位置时，取消广播注册
        if(receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    // 创建一个自定义广播类
    class ForceOffOnlineRecever extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {

            // 创建AlertDialog对象，并给属性赋值
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline.Please try to login again.");
            builder.setCancelable(false);
            // 绑定按钮逻辑
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCollector.removeAll();  // 移除所有的Activity
                    Intent intent =  new Intent(context,LoginActivity.class); // 跳转到登录页面
                    startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
