package com.example.splitapplicationdemo.embedding;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splitapplicationdemo.R;
import com.example.splitapplicationdemo.BaseActivity;
import com.example.splitapplicationdemo.sqldata.BlogDatabaseHelper;

public class DetailActivity extends BaseActivity {

    Button authorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String title = getIntent().getStringExtra("title");
        searchBlog(title);
    }

    @SuppressLint("Range")
    private void searchBlog(String title){
        TextView titleView = findViewById(R.id.blog_title);
        TextView summeryView = findViewById(R.id.blog_summery);
        TextView mainTextView = findViewById(R.id.blog_main_text);
        authorBtn = findViewById(R.id.author_button);

        SQLiteOpenHelper dbHelper = new BlogDatabaseHelper(this,"BlogStore.db",null,5);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Blog where title = ?", new String[]{title});
        if(cursor.moveToFirst()){
            titleView.setText(cursor.getString(cursor.getColumnIndex("title")));
            summeryView.setText(cursor.getString(cursor.getColumnIndex("summery")));
            mainTextView.setText(cursor.getString(cursor.getColumnIndex("mainText")));
            authorBtn.setText(cursor.getString(cursor.getColumnIndex("author")));
        }
        cursor.close();
    }
}