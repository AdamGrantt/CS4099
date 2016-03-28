package uk.co.adamgrant.cs4099;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.Timer;
import java.util.TimerTask;

public class NotifyService extends Service {
    // Notification interval.
    private static final long UPDATE_INTERVAL = 1000*20;

    private Timer timer = new Timer();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // we shedule task "showNotification" to run everyday.
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        showNotification();
                    }
                },
                0,
                UPDATE_INTERVAL);
    }

    @Override
    public void onDestroy() {
    }
    

    public void showNotification() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

        mBuilder.setContentIntent(null);

        NotificationManager mManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mManager.notify(1, mBuilder.build());
    }
}
