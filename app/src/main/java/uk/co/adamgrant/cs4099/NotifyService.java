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

    Calendar startingTime = Calendar.getInstance();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Log.d(TAG, "onCreate");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

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
        Log.v("$''''$", "service onDestroy");

        timer.cancel();
    }

    public void showNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.sleep)
                        .setContentTitle("Sleep Entry Reminder")
                        .setContentText("Remember to enter last nights sleep!");

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, UserEntryActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(UserEntryActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_ONE_SHOT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setAutoCancel(true);

        NotificationManager mManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mManager.notify(1, mBuilder.build());
    }
}
