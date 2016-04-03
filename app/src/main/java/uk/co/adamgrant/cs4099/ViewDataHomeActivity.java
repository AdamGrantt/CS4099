package uk.co.adamgrant.cs4099;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ViewDataHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startViewRawLockData(View v) {
        Intent intent = new Intent(this, ViewRawLockDataActivity.class);
        startActivity(intent);
    }

    public void startViewGraphLockData(View v) {
        Intent intent = new Intent(this, ViewGraphLockDataActivity.class);
        startActivity(intent);
    }

    public void startViewRawUserSleepData(View v) {
        Intent intent = new Intent(this, ViewRawUserSleepDataActivity.class);
        startActivity(intent);
    }
}
