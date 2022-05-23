package com.example.splitapplicationdemo.embedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.splitapplicationdemo.R;

public class LoginActivity extends AppCompatActivity {
    String TAG = "loginActivity";
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = findViewById(R.id.username);
                password = findViewById(R.id.password);
                if(userName.getText().toString().equals("") && password.getText().toString().equals("")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userName", "xiaomi");
                    intent.putExtra("password", "password");
                    startActivity(intent);
                }else{
                    Log.d(TAG, "onClick: username = "+userName.getText()+",password = "+ password.getText());
                    Toast.makeText(LoginActivity.this, "您输入的用户名或密码有误，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}