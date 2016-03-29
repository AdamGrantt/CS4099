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
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyService extends Service {

    private static final String TAG = "MyService";

    // Notification interval.
    private static final long UPDATE_INTERVAL = 1000*60*60*24;

    private Timer timer = new Timer();
    boolean created = false;

    Calendar startingTime = Calendar.getInstance();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Log.d(TAG, "onCreate");
//        Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        long currentTime = ((startingTime.get(Calendar.HOUR_OF_DAY)*60*60) + (startingTime.get(Calendar.MINUTE)*60) + (startingTime.get(Calendar.SECOND))) * 1000;

        long targetTime = ((calendar.get(Calendar.HOUR_OF_DAY)*60*60) + (calendar.get(Calendar.MINUTE)*60) + (calendar.get(Calendar.SECOND))) * 1000;

        long difference = targetTime - currentTime;

        long delay;

        if(difference>0){
            delay = difference;
        } else if(difference < 0){
            delay = Math.abs(UPDATE_INTERVAL + difference);
        } else {
            delay = 0;
        }

        // we shedule task "showNotification" to run everyday.
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        showNotification();
                    }
                },
                delay,
                UPDATE_INTERVAL);

    }

    @Override
    public void onDestroy() {
//        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        Log.v("$''''$", "service onDestroy");

        timer.cancel();
    }


    public void showNotification() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Sleep Entry Reminder")
                        .setContentText("Have you entered your sleep for last night yet?");

        mBuilder.setContentIntent(null);

        NotificationManager mManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mManager.notify(1, mBuilder.build());
    }
}
