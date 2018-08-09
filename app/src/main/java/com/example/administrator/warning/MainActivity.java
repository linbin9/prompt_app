package com.example.administrator.warning;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sure = (Button)findViewById(R.id.sure);
    }
    public void showmessage(){
        //创建通知管理类
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //创建通知建设类
        Notification.Builder builder = new Notification.Builder(MainActivity.this);
        //设置跳转的页面
        PendingIntent intent = PendingIntent.getActivity(MainActivity.this,
                100, new Intent(MainActivity.this, MainActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT);
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
    }
    private static String midStr1 =  "";
    private static String midStr2 =  "";
    public static String getStrDate(Calendar calendar){
        int year;
        int month;
        int day;
        int hour;
        int minute;
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        if (minute<10){
            midStr2 = "0";
        }
        if (hour<10){
            midStr1 = "0";
        }
        return year + "年" + month + "月" + day + "日 " +midStr1 + hour + ":"+ midStr2 + minute;
    }
    public void waittime(View vw){
        EditText value = (EditText)findViewById(R.id.value);
        EditText minutes = (EditText)findViewById(R.id.minutes);
        Calendar calendar = Calendar.getInstance();
        int hour;
        int minute;
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        int addhour = Integer.valueOf(value.getText().toString());
        int addminutes = Integer.valueOf(minutes.getText().toString());
        calendar.add(Calendar.HOUR_OF_DAY,addhour);
        calendar.add(Calendar.MINUTE,addminutes);
        while(true){
            Calendar target = Calendar.getInstance();
            Log.d("time",String.valueOf(target.get(Calendar.MINUTE)));
            if(target.equals(calendar))break;
        }
        showmessage();
    }
}
