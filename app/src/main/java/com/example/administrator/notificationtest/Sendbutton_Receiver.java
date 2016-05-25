package com.example.administrator.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/21.
 */
public class Sendbutton_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            Notification.Builder build = new Notification.Builder(context).setContentText("这里是notification内容").setContentTitle("手动发送的nitice").setSmallIcon(R.drawable.ic_launcher);
            Intent jump = new Intent(context,NotificationActivity.class);

            jump.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

            PendingIntent result = PendingIntent.getActivity(context,0,jump,PendingIntent.FLAG_CANCEL_CURRENT);
            build.setContentIntent(result);
            Notification notice = build.build();
            //使用默认提示方式
            notice.defaults = Notification.DEFAULT_ALL;

            manager.notify(2,notice);
            Toast.makeText(context,"网啊",Toast.LENGTH_SHORT).show();
    }
}
