package com.example.sqliitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
* 这是一个SQLiteOpenHelper的实现类
* 在方法中定义了建表语句和更新表语句
*/
public class MyDataBaseHelper extends SQLiteOpenHelper {

    // 创建Book语句
    private String createBook = "create table Book(\n" +
            "    id integer primary key autoincrement,\n" +
            "    author text,\n" +
            "    price real,\n" +
            "    pages integer,\n" +
            "    name text,\n"+
            "    category_id integer)";

    private String createCategory = "create table Category (\n" +
            "id integer primary key autoincrement,\n" +
            "category_name text,\n" +
            "category_code integer)";

    // 重写创建表方法
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createBook);     // 执行SQL语句
        sqLiteDatabase.execSQL(createCategory);
    }

    // 重写更新表方法
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // 如果老用户的数据库版本低于1.0，使用执行这条语句进行更新
        if(oldVersion <= 1){
            sqLiteDatabase.execSQL(createCategory);
        }
        // 如果老用户的数据库版本低于2.0，使用执行这条语句进行更新
        if(oldVersion <= 2){
            sqLiteDatabase.execSQL("alter table Book add column category_id integer");
        }
    }

    // 构造方法
    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
