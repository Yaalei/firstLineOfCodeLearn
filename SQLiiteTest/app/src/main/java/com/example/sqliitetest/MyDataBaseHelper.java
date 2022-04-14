package com.example.sqliitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    // 定义建表语句
    private String createCategory = "create table Category(\n" +
            "    id integer primary key autoincrement,\n" +
            "    category_name text,\n" +
            "    category_code integer)";

    private Context context;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createCategory);
        // 可以从构造方法中获取context
        Toast.makeText(context, "Cteate createCategory Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists Book"); // 删除表
        sqLiteDatabase.execSQL("drop table if exists Category");

        onCreate(sqLiteDatabase);  // 创建新表
    }

    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }
}
