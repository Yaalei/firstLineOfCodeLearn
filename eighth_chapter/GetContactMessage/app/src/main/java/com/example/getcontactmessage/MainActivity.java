package com.example.getcontactmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 功能: 通过获取运行时权限，访问手机联系人信息
 * 并将信息用ListView的方式进行展示
 */

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> list = new ArrayList<>();
    
    ArrayAdapter adapter;

    // 定义请求码
    private static final int  CONTACT_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建一个ListView对象，并绑定一个ArrayAdapter对象
        ListView contactList = findViewById(R.id.contact_list);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        contactList.setAdapter(adapter);

        // 检查是否有访问手机联系人的权限
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            // 进行权限请求
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},CONTACT_REQUEST_CODE);
        }else{
            // 有权限的话，直接调用展示方法
            showContacts();
        }
    }

    // 在请求运行时权限以后，会调用这个方法，并将请求结果封装到参数中
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 校验请求码
        switch (requestCode){
            case CONTACT_REQUEST_CODE:
                // 判断是否得到了权限
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    showContacts();
                }else{
                    Toast.makeText(this, "This permission is denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    // 创建一个展示数据的方法
    private void showContacts() {
        Cursor cursor = null;
        try{
            // 利用ContentResolver对象的query方法获取数据
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            // 将数据存入到list对象中
            if(cursor != null){
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    list.add("姓名："+name+",号码："+number);
                }
                // 刷新adapter进行展示
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null){
                cursor.close();
            }
        }
    }
}
