package com.example.samtech.homemobile2;

/**
 * Created by Samtech on 08-05-2017.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.Random;

public class GCMPushRecieverService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data){
        sendNotification(data);
    }

    public void sendNotification(Bundle data){
        String title = data.getString("title","");
        String message = data.getString("message","");
        String dato = data.getString("data","");

        int uniqueInt = (int) (System.currentTimeMillis() & 0xfffffff);
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("data",dato );
        intent.putExtra("inter",uniqueInt );
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK );

        PendingIntent pendingIntent = PendingIntent.getActivity(this, uniqueInt, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true).setContentTitle(title)
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(title))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setVibrate(new long[] { 1000, 1000 })
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setLights(Color.RED, 3000, 3000);

        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notify = new Notification();
        notify.flags = Notification.FLAG_SHOW_LIGHTS | Notification.FLAG_ONLY_ALERT_ONCE;
        Random random = new Random();
        notificationmanager.notify(random.nextInt(), noBuilder.build());

    }
}