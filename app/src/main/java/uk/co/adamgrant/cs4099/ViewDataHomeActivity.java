package uk.co.adamgrant.cs4099;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Activity which allows the navigation to all of the data view Activities
 */
public class ViewDataHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Method which activates the Activity to view the Raw Lock Data
     * @param v
     */
    public void startViewRawLockData(View v) {
        Intent intent = new Intent(this, ViewRawLockDataActivity.class);
        startActivity(intent);
    }

    /**
     * Method which activates the Activity to view the Graphed Lock Data
     * @param v
     */
    public void startViewGraphLockData(View v) {
        Intent intent = new Intent(this, ViewGraphLockDataActivity.class);
        startActivity(intent);
    }

    /**
     * Method which activates the Activity to view the Raw Sleep Data
     * @param v
     */
    public void startViewRawUserSleepData(View v) {
        Intent intent = new Intent(this, ViewRawUserSleepDataActivity.class);
        startActivity(intent);
    }
}
