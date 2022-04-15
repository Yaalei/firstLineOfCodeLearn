package com.example.broadcastforceoff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * 定义一个登录Activity类
 * 如果能登录成功则跳转到MainActivity，如果密码错误会给出提示
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 绑定登录按钮
        Button button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取输入框中的数据
                EditText accountEdit = findViewById(R.id.accountEdit);
                String account = accountEdit.getText().toString();

                EditText passwordEdit = findViewById(R.id.passwordEdit);
                String password = passwordEdit.getText().toString();

                // 判断是否登录成功
                if(account.equals("wanghaha")&& password.equals("123456")){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
