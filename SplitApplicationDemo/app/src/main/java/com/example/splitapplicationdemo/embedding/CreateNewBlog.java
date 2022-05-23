package com.example.splitapplicationdemo.embedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.splitapplicationdemo.R;
import com.example.splitapplicationdemo.admin.sqldata.BlogDatabaseHelper;

public class CreateNewBlog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_blog);
        BlogDatabaseHelper databaseHelper = new BlogDatabaseHelper(CreateNewBlog.this,"BlogStore.db",null,5);

        findViewById(R.id.send_blog_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sdb = databaseHelper.getReadableDatabase();
                for (int i = 1; i < 11; i++) {
                    ContentValues values = new ContentValues();
                    values.put("title","这是第"+i+"条博客");
                    values.put("author","yalei");
                    values.put("summery","摘要：关于人生的第"+i+"个道理，最后一个绝对让你三观尽毁");
                    values.put("mainText","   从前有座山，山上有座庙，庙里有个老和尚和小和尚讲故事，故事讲的什么呢？\n   讲的是从前有座山，山上有作庙，庙里有一个老和尚和小和尚讲故事，讲的什么呢，讲的是......");
                    sdb.insert("Blog",null,values);
                }
            }
        });

    }
}