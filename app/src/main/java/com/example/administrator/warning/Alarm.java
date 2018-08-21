package com.example.administrator.warning;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2018/8/20.
 */

public class Alarm extends Activity {
    MainActivity mainActivity;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //创建通知管理类
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //创建通知建设类
        Notification.Builder builder = new Notification.Builder(Alarm.this);
        Intent intent = new Intent();//只显示通知，无页面跳转
        //设置跳转的页面
        PendingIntent intentPend = PendingIntent.getActivity(Alarm.this,
               100, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //设置通知栏标题
        builder.setContentTitle("提示");
        //设置通知栏内容
        builder.setContentText("体力快满了！！！");
        //设置跳转
        //builder.setContentIntent(intent);
        //设置图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //设置
        builder.setDefaults(Notification.DEFAULT_ALL);
        //创建通知类
        Notification notification = builder.build();
        //显示在通知栏
        manager.notify(0, notification);
        this.finish();
    }
}
