package com.example.splitapplicationdemo.embedding;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.window.core.ExperimentalWindowApi;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.splitapplicationdemo.R;
import com.example.splitapplicationdemo.BaseActivity;
import com.example.splitapplicationdemo.sqldata.BlogDatabaseHelper;
import com.example.splitapplicationdemo.recyclerview.Blog;
import com.example.splitapplicationdemo.recyclerview.BlogAdapter;

import java.util.ArrayList;
import java.util.List;

@ExperimentalWindowApi
public class MainActivity extends BaseActivity {

    private List<Blog> blogList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();
        RecyclerView recyclerView = findViewById(R.id.blog_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new BlogAdapter(blogList);
        recyclerView.setAdapter(adapter);

    }

    @SuppressLint("Range")
    private void initList() {
        SQLiteOpenHelper dbHelper = new BlogDatabaseHelper(this,"BlogStore.db",null,5);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Blog",null);

        if(cursor.moveToFirst()){
            do {
                Blog blog = new Blog();
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String summery = cursor.getString(cursor.getColumnIndex("summery"));
                blog.setBlogTitle(title);
                blog.setBlogSummary(summery);
                blogList.add(blog);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
}