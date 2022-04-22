package com.example.cameraalbum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 创建一个程序测试调用摄像头进行拍照的功能
 * 将拍到的照片进行展示
 */
public class MainActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1; //请求码
    File outputImage;
    Uri imageUri;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 绑定ImageView对象
        imageView = findViewById(R.id.image_view);

        // 绑定按钮对象
        Button takePhotoButton = findViewById(R.id.take_photo_button);
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 创建一个File对象，创建一个应用关联缓存目录
                outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    // 创建新文件
                    outputImage.createNewFile();
                }catch (Exception e){
                    e.printStackTrace();
                }

                // 将File对象转换为Uri对象，这个对象标志着照片的真实路径
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    imageUri = FileProvider.getUriForFile(MainActivity.this,"com.example.cameraalbum.fileprovider",outputImage);
                }else{
                    imageUri = Uri.fromFile(outputImage);
                }

                // 构建一个隐式Intent对象，并传入能响应的Action,然后将照片Uri对象放入
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                // 启动照相机程序，并将拍下的照片存储到output_image.jpg中
                startActivityForResult(intent,TAKE_PHOTO);  // 执行结束后会调用onActivityResult()方法
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    try{
                        //将照片解析成BigMap对象
                        Bitmap bitMap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        // 将照片绑定到ImageView对象上
                        imageView.setImageBitmap(bitMap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
