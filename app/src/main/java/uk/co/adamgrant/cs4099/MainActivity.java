package uk.co.adamgrant.cs4099;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/**
 * Main app activity, initialises Notification Service,
 * Data Collection Service and allows the user to navigate
 * to the home screen.
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialises Notification
        initNotifications();
        // Initialises Data Collection
        initDataCollection();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Initialises the Notification Service.
     */
    private void initNotifications() {
        Intent notifyIntent = new Intent(this, NotifyService.class);
        startService(notifyIntent);
    }

    /**
     * Initialises the Data Collection Service
     */
    private void initDataCollection() {
        Intent dataIntent = new Intent(this, DataCollectionService.class);
        startService(dataIntent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        Log.v("$''''$", "In Method: onSaveInstanceState()");
    }

    @Override
    public void onRestoreInstanceState(Bundle outState)
    {
        Log.v("$''''$", "In Method: onRestoreInstanceState()");
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

    /**
     * Starts the app and opens the Home Activity
     * @param v
     */
    public void start(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
