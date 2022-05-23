package com.example.splitactivitydemo2.embedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.splitactivitydemo2.R;

public class SplitActivityPlaceholder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_placeholder);

        findViewById(R.id.start_activityA_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplitActivityPlaceholder.this, SplitActivityA.class));
            }
        });
    }
}