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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleNotification();
        initDataCollection();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void handleNotification() {
        Intent notifyIntent = new Intent(this, NotifyService.class);
        startService(notifyIntent);
    }

    private void initDataCollection() {
        Intent dataIntent = new Intent(this, DataCollectionService.class);
        startService(dataIntent);
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
    }

    // ****************************** FOR HANDLING STORAGE OF LOCK/UNLOCK DATA ******************************

    public void start(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
