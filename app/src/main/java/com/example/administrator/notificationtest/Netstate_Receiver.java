package com.example.administrator.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/22.
 */
public class Netstate_Receiver extends BroadcastReceiver {
//依然是在测试git
    private static final String TAG = "Netstate_Receiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Net_onReceive: 监测到网络变化！！！！");
        ConnectivityManager netmanager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo net = netmanager.getActiveNetworkInfo();

        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Return_notification re_notice = new Return_notification();
        //获取到noticce对象
        Notification notice = re_notice.get_notice(context,"网络状态","有网络用了哈哈",NetNotificationActivity.class);
//        有网络的话
        if(net != null && net.isAvailable()){
            manager.notify(3,notice);
            Toast.makeText(context,"有网络了！！！！",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Net_Receive: 监听到网络~~ ");
        }
        else{
            Toast.makeText(context,"没有网络！！！！",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Net_Receive: 没有网络！！！！");
        }
    }
}
