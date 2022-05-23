package com.example.splitactivitydemo2.embedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.splitactivitydemo2.R;

public class SplitActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_a);

        findViewById(R.id.A_start_B).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplitActivityA.this, SplitActivityB.class));
            }
        });

        findViewById(R.id.A_start_C).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplitActivityA.this, SplitActivityC.class));
            }
        });
    }
}