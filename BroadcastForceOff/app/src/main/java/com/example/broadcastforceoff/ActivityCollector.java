package com.example.broadcastforceoff;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个Activity的帮助类
 * 主要功能是提供一个Activity容器
 * 通过容器去控制Activity的强制关闭
 */
public class ActivityCollector {

    static List<Activity> list = new ArrayList<>(); // 定义Activity容器
    public static void addActivity(Activity activity){
        list.add(activity); // 容器中添加Activity
    }

    public static void removeActivity(Activity activity){
        if(!list.isEmpty()){
            list.remove(activity);  // 容器中移除Activity
        }
    }

    // 调用这个类将所有的Activity关闭
    public static void removeAll(){
        for(Activity activity:list){
            activity.finish();
        }
    }
}
