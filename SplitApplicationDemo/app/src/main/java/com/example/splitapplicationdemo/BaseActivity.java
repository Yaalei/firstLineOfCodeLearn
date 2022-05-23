package com.example.splitapplicationdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.window.embedding.SplitController;
import androidx.window.embedding.SplitInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.splitapplicationdemo.R;
import com.example.splitapplicationdemo.embedding.CreateNewBlogActivity;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        SplitController.getInstance().addSplitListener(this,getMainExecutor(),new SplitInfoChangeCallBack());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.create_blog:
                startActivity(new Intent(this, CreateNewBlogActivity.class));
                break;
            case R.id.exit:
                onDestroy();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
//
    class SplitInfoChangeCallBack implements Consumer<List<SplitInfo>> {
        @Override
        public void accept(List<SplitInfo> splitInfoList) {
            if(splitInfoList.isEmpty()){
                findViewById(R.id.create_blog).setVisibility(View.GONE);
            }
            findViewById(R.id.create_blog).setVisibility(View.VISIBLE);
        }
    }
}