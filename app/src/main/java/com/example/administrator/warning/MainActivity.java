package com.example.administrator.warning;

import android.app.AlarmManager;
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
        EditText restminutes = (EditText)findViewById(R.id.restminutes);

        Calendar calendar = Calendar.getInstance();

        int a = Integer.valueOf(value.getText().toString());
        int b = Integer.valueOf(restminutes.getText().toString());

        Log.d("a", String.valueOf(a));
        Log.d("b", String.valueOf(b));

        int allminute = (139 - Integer.valueOf(minutes.getText().toString())) * 5 + Integer.valueOf(restminutes.getText().toString())
                - Integer.valueOf(value.getText().toString());
        int hour = allminute / 60;
        int minute = allminute % 60;

        Log.d("hour", String.valueOf(hour));
        Log.d("minute", String.valueOf(minute));
        calendar.add(Calendar.HOUR_OF_DAY,hour);
        calendar.add(Calendar.MINUTE,minute);


        Intent intent = new Intent(MainActivity.this,
                Alarm.class);
        // 创建PendingIntent对象
        PendingIntent pi = PendingIntent.getActivity(
                MainActivity.this, 0, intent, 0);
        AlarmManager aManager = (AlarmManager)
                getSystemService(ALARM_SERVICE);
        // 设置AlarmManager将在Calendar对应的时间启动指定组件
        aManager.set(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), pi);
        // 显示闹铃设置成功的提示信息
        Toast.makeText(MainActivity.this, "提醒设置成功啦"
                , Toast.LENGTH_SHORT).show();
    }
}
