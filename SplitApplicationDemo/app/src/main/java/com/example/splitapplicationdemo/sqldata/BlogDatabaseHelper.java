package com.example.splitapplicationdemo.sqldata;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BlogDatabaseHelper extends SQLiteOpenHelper {

    private String createBlog = "create table Blog(" +
            "id integer primary key autoincrement," +
            "title text," +
            "author text," +
            "summery text," +
            "mainText text)";

    public BlogDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createBlog);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Blog");
        onCreate(sqLiteDatabase);
    }
}
