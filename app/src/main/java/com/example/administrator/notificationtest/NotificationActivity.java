package com.example.administrator.notificationtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/5/18.
 */
public class NotificationActivity extends Activity {
    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_notification_layout);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
    }
}
