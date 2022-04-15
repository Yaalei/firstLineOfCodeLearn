package com.example.broadcastforceoff;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button off_button = findViewById(R.id.off_button);
        off_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 利用Intent对象发送广播，在Intent构造方法中传入Action
                Intent intent = new Intent("com.example.broadcastforceoff.FORCE_OFF_LOGIN");
                intent.setPackage(getPackageName()); //传入包名，指定相应的程序，将隐式广播定义为显式广播
                // 发送广播 sendOrderedBroadcast用于发送有序广播
                sendBroadcast(intent);
            }
        });
    }
}
