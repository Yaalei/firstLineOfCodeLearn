package com.example.sqliitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 绑定配置文件
        setContentView(R.layout.activity_main);

        // 创建MyDataBaseHelper对象
        dataBaseHelper = new MyDataBaseHelper(this,"BookStore.db",null,8);

        Button createDatabaseTable = findViewById(R.id.create_database_button); // 获取Button对象
        createDatabaseTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建或升级数据库
                dataBaseHelper.getWritableDatabase();
            }
        });
    }
}
