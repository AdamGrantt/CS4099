package uk.co.adamgrant.cs4099;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Service which runs in the background sending out a notification
 * at 17:00 every day, reminding the user to enter their sleep data
 * from the previous night.
 */
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

        // Initialise the calendar to be used for timing the notifications
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Convert the current time represented as a calendar, to epoch format.
        long currentTime = ((startingTime.get(Calendar.HOUR_OF_DAY)*60*60) + (startingTime.get(Calendar.MINUTE)*60) + (startingTime.get(Calendar.SECOND))) * 1000;
        // Convert the target notification time represented as a calendar, to epoch format.
        long targetTime = ((calendar.get(Calendar.HOUR_OF_DAY)*60*60) + (calendar.get(Calendar.MINUTE)*60) + (calendar.get(Calendar.SECOND))) * 1000;

        long difference = targetTime - currentTime;

        // Calculate time until next notification
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
        Log.v("$''''$", "notifyService onDestroy");
        timer.cancel();
    }

    /**
     * Method which sends a notification to the user reminding them to
     * enter last night's sleep data
     */
    public void showNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.icon)
                        .setContentTitle("Sleep Entry Reminder")
                        .setContentText("Remember to enter last nights sleep!");

        // Creates an explicit intent for the UserEntryActivity
        Intent resultIntent = new Intent(this, UserEntryActivity.class);

        // This ensures that when the user navigates to the app using the notification,
        // that the backward navigation from the activity is correct.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(UserEntryActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_ONE_SHOT
                );
        // Allows the user to click the notification and be brought straight
        // to the data entry activity
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setAutoCancel(true);

        NotificationManager mManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mManager.notify(1, mBuilder.build());
    }
}
