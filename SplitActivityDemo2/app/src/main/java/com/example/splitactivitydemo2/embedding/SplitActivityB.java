package com.example.splitactivitydemo2.embedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.splitactivitydemo2.R;

public class SplitActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_b);

        findViewById(R.id.B_start_C).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplitActivityB.this, SplitActivityC.class));
            }
        });

        findViewById(R.id.B_start_D).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplitActivityB.this, SplitActivityD.class));
            }
        });
    }
}