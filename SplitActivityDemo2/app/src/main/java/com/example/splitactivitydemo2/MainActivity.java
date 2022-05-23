package com.example.splitactivitydemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.splitactivitydemo2.embedding.SplitActivityA;
import com.example.splitactivitydemo2.embedding.SplitActivityB;
import com.example.splitactivitydemo2.embedding.SplitActivityC;
import com.example.splitactivitydemo2.embedding.SplitListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_start_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SplitListActivity.class));
            }
        });

        findViewById(R.id.main_start_A).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SplitActivityA.class));
            }
        });

        findViewById(R.id.main_start_C).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SplitActivityC.class));
            }
        });
    }
}