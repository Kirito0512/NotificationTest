package com.example.administrator.notificationtest;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/5/22.
 */
public class Return_notification {
    public Notification get_notice(Context context, String title, String content, Class activity){
        Notification.Builder build = new Notification.Builder(context).setContentText(content).setContentTitle(title).setSmallIcon(R.drawable.ic_launcher);
        Intent jump = new Intent(context,activity);
        PendingIntent result = PendingIntent.getActivity(context,0,jump,PendingIntent.FLAG_CANCEL_CURRENT);
        build.setContentIntent(result);
        Notification notice = build.build();
        //使用默认提示方式
        notice.defaults = Notification.DEFAULT_ALL;
        return notice;
    }
}
