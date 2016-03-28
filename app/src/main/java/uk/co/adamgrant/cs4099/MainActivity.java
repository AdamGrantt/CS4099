package uk.co.adamgrant.cs4099;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    PendingIntent pendingIntent;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init file
        File path = MainActivity.this.getFilesDir();
        File file = new File(path, "lockData.txt");

        if(file.exists()){
            file.delete();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);

        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);

        mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);

        // ******************* NOTIFICATION **********************
        handleNotification();
        // ******************* NOTIFICATION **********************

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void handleNotification() {
        Intent myIntent = new Intent(this , NotifyService.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        //set repeating every 24 hours
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000 , pendingIntent);
    }

    // ****************************** FOR HANDLING STORAGE OF LOCK/UNLOCK DATA ******************************
    public class ScreenReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Calendar c = Calendar.getInstance();
            int second = c.get(Calendar.SECOND);
            int minute = c.get(Calendar.MINUTE);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
            {
                Log.v("$$$$$$", "In Method: ACTION_SCREEN_OFF");

                writeToFile("Locked " + year + ", " + month + ", " + day + ", " + hour + ", " + minute + ", " + second);
                // onPause() will be called.
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
            {
                Log.v("$$$$$$", "In Method: ACTION_SCREEN_ON");

                writeToFile("Unlocked " + year + ", " + month + ", " + day + ", " + hour + ", " + minute + ", " + second);

                // on Resume will be called.

                // Better check for whether the screen was already locked
                // If locked, do not take any resuming action in onResume()
                // Suggest you, not to take any resuming action here.
            }
            else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT))
            {
                Log.v("$$$$$$", "In Method: ACTION_USER_PRESENT");
                // Handle resuming events.
            }
        }
    }

    private void writeToFile(String data) {
        File path = MainActivity.this.getFilesDir();
        File file = new File(path, "lockData.txt");
        try {
            OutputStreamWriter out = new OutputStreamWriter(this.openFileOutput("lockData.txt", Context.MODE_APPEND));
            out.write(data);
            out.write('\n');
            out.close();
            Log.v("$$$$$$", "In Method: writeToFile(), " + data);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        Log.v("$''''$", "In Method: onSaveInstanceState()");
        // if necessary, set a flag to check whether we have to restore or not
        // handle necessary savings...
    }

    @Override
    public void onRestoreInstanceState(Bundle outState)
    {
        Log.v("$''''$", "In Method: onRestoreInstanceState()");
        // if any saved state, restore from it...
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.v("$$$$$$", "In Method: onDestroy()");

        if(mReceiver != null) {

            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }

    // ****************************** FOR HANDLING STORAGE OF LOCK/UNLOCK DATA ******************************

    // ************************************** NOTIFICATIONS *************************************************



    // ************************************** NOTIFICATIONS *************************************************

    public void start(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
