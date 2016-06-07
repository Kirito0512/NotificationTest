package com.example.administrator.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button sendNotice;
    private Button sendBroad;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //用来测试git status
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotice = (Button) findViewById(R.id.send_notice);
        sendBroad = (Button) findViewById(R.id.send_broadCast);
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification.Builder mBuilder = new Notification.Builder(this)
                                                    .setContentTitle("notice")
                                                    .setContentText("Hello ya world")
                                                    .setSmallIcon(R.drawable.ic_launcher);

        //Intent对象
        Intent resultIntent = new Intent(this, NotificationActivity.class);
        //TaskStackBuilder对象用来为新启动的活动创建一个人工返回栈
        //保证了从这个活动点击返回，会回到主屏幕
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        //获取pendingintent对象
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        //另一种pendingintent创建方式
        // PendingIntent resultPendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        //将pendingintent加入
        mBuilder.setContentIntent(resultPendingIntent);

        final Notification notice = mBuilder.build();
//        设置震动和静止时间
//        奇数项表示静止时长，偶数项表示震动时长
        long []vibrates = {0,3000,1000,3000};
        notice.vibrate = vibrates;
        //设置亮灯颜色和时长
        notice.ledARGB = Color.BLUE;
        //亮起时长
        notice.ledOnMS = 1000;
        //暗去时长
        notice.ledOffMS = 1000;;
        //一闪一闪的效果
        notice.flags = Notification.FLAG_SHOW_LIGHTS;



        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1为requestcode（因为多次点击时requestcode相同，所以只能看到一条通知）
                // build()方法返回Notification对象
                //notify发送通知
                mNotificationManager.notify(1, notice);
                Log.d(TAG, "onClick: ------->>>>>>>>notify");
            }
        });

        sendBroad.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent("com.net.broadcast");
                sendBroadcast(intent);
            }
        });


        //动态注册监控网络状态变化的广播的接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        Net_Receiver net_receiver = new Net_Receiver();
        registerReceiver(net_receiver,intentFilter);
    }
    class Net_Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "MainActivity NetReceiver");
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
}
