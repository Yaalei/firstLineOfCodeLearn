package com.example.splitactivitydemo2.embedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.splitactivitydemo2.R;

public class SplitActivityD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_d);

        findViewById(R.id.D_start_E).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplitActivityD.this, SplitActivityE.class));
            }
        });
    }
}