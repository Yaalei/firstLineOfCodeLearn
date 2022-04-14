package com.example.sqliitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * 这是一个主Activity
 * 在onCreate方法中创建了进行了表的创建和更新
 * 同时对表中的数据进行增删改查
 * 最后使用了事务对表中数据进行了修改
 */
public class MainActivity extends AppCompatActivity {

    private MyDataBaseHelper dataBaseHelper;
    private SQLiteDatabase sqlLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 绑定配置文件
        setContentView(R.layout.activity_main);

        // 创建MyDataBaseHelper对象
        dataBaseHelper = new MyDataBaseHelper(this,"BookStore.db",null,10);

        // 将创建数据库的方法绑定到按钮上
        Button createDatabaseTable = findViewById(R.id.create_database_button); // 获取Button对象
        createDatabaseTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建或升级数据库
                sqlLite = dataBaseHelper.getWritableDatabase();
            }
        });

        // 将添加数据的方法绑定到按钮上
        Button addDataButton = findViewById(R.id.add_data_button);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlLite = dataBaseHelper.getReadableDatabase();
                sqlLite.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",
                        new String[]{"侠客行","李白","99","9.9"});
                sqlLite.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",
                        new String[]{"潼关吏","杜甫","99","9.9"});
                sqlLite.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",
                        new String[]{"长歌行","白居易","99","9.9"});
            }
        });
        // 将更新数据的方法绑定到按钮上
        Button upDateDataButton = findViewById(R.id.update_data_button);
        upDateDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlLite = dataBaseHelper.getReadableDatabase();
                sqlLite.execSQL("update Book set name = ? where author = ?",
                        new String[]{"石壕吏","杜甫"});
            }
        });

        // 将删除数据的方法绑定到按钮上
        Button deleteDataButton = findViewById(R.id.delete_data_button);
        deleteDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlLite = dataBaseHelper.getReadableDatabase();
                sqlLite.execSQL("delete from Book where name = ?",
                        new String[]{"长歌行"});
            }
        });

        // 将查询数据的方法绑定到按钮上，使用Toast将数据循环显示出来
        Button queryDataButton = findViewById(R.id.query_data_button);
        queryDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlLite = dataBaseHelper.getReadableDatabase();
                Cursor cursor = sqlLite.rawQuery("select * from Book",null);
                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        String pages = cursor.getString(cursor.getColumnIndex("pages"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "onClick: name"+name+" author"+author+" pages"+pages+" price:"+price);
                    }while(cursor.moveToNext());
                }
            }
        });

        // 使用事务进行Book表数据的更改
        Button replaceData = findViewById(R.id.replace_data_button);
        replaceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlLite = dataBaseHelper.getReadableDatabase();
                try{
                    // 开启事务
                    sqlLite.beginTransaction();
                    // 删除数据
                    sqlLite.execSQL("delete from Book where name = ?",new String[] {"长歌行"});
                    // 为了测试事务，手动抛异常
/*                  if(true){
                        throw new SQLException("手动抛异常");
                    }*/
                    // 添加数据
                    sqlLite.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",
                            new String[]{"水调歌头","苏轼","100","10"});
                    sqlLite.setTransactionSuccessful();  // 数据执行成功
                }catch(SQLException e){
                    e.printStackTrace();
                }finally {
                    sqlLite.endTransaction();  // 关闭事务
                }
            }
        });
    }
}
