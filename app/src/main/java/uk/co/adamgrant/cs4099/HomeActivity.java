package uk.co.adamgrant.cs4099;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Activity for the Home Screen of the app, providing navigation
 * throughout the entire app
 */
public class HomeActivity extends AppCompatActivity {

    /**
     * On HomeActivity creation, initialises layout and toolbar.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to start the User Entry Activity
     * @param v
     */
    public void startUserEntry(View v) {
        Intent intent = new Intent(this, UserEntryActivity.class);
        startActivity(intent);
    }

    /**
     * Method to start the View Data Activity
     * @param v
     */
    public void startViewDataHome(View v) {
        Intent intent = new Intent(this, ViewDataHomeActivity.class);
        startActivity(intent);
    }

    /**
     * Method to start the Personal Details Activity
     * @param v
     */
    public void startPersonalDetails(View v) {
        Intent intent = new Intent(this, PersonalDetailsActivity.class);
        startActivity(intent);
    }

}
