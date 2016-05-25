package com.example.administrator.notificationtest;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/5/22.
 */
public class NetNotificationActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_notification_layout);

        NotificationManager netmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        netmanager.cancel(2);
    }
}
