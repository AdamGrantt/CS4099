package uk.co.adamgrant.cs4099;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

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

    public void start(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
