package com.example.splitapplicationdemo.embedding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.splitapplicationdemo.R;
import com.example.splitapplicationdemo.sqldata.BlogDatabaseHelper;

public class CreateNewBlogActivity extends AppCompatActivity {

    BlogDatabaseHelper databaseHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_blog);
        databaseHelper = new BlogDatabaseHelper(CreateNewBlogActivity.this,"BlogStore.db",null,5);
//        databaseHelper.getWritableDatabase().execSQL("DELETE FROM Blog WHERE title IS NULL OR TRIM(title) = ?",new String[]{""});

        findViewById(R.id.send_blog_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBlog();
            }
        });
    }

    private void createBlog(){
        EditText titleText = findViewById(R.id.title_edittext);
        EditText summeryText = findViewById(R.id.summery_edittext);
        EditText mainText = findViewById(R.id.main_text_edittext);

        if(titleText.getText().toString().equals("")){
            AlertDialog.Builder dialog = new AlertDialog.Builder(CreateNewBlogActivity.this);
            dialog.setTitle("重要提示");
            dialog.setMessage("博客标题不允许为空！请重新编辑标题");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                }
            });
            dialog.show();
        }else{
            SQLiteDatabase sdb = databaseHelper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("title",titleText.getText().toString());
            values.put("author","yalei");
            values.put("summery",summeryText.getText().toString());
            values.put("mainText",mainText.getText().toString());
            sdb.insert("Blog",null,values);
        }

    }
}