package uk.co.adamgrant.cs4099;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Activity to allow the viewing of raw lock/unlock data
 */
public class ViewRawLockDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_lock_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialises the FloatingActionButton to refresh the data when clicked.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayData();
                Toast.makeText(ViewRawLockDataActivity.this, "Refreshed", Toast.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Displays the data from file
        displayData();
    }

    /**
     * Method which creates the TextView for the lock data and displays it.
     */
    public void displayData() {
        TextView textView = (TextView) findViewById(R.id.raw_lock_data);
        textView.setText(readFile());
    }

    /**
     * Method which reads the lock data from file and returns it as String
     * @return lockData.txt contents
     */
    public String readFile()
    {
        File path = ViewRawLockDataActivity.this.getFilesDir();
        File file = new File(path, "lockData.txt");
        String contents;
        int length = (int) file.length();
        byte[] bytes = new byte[length];

        FileInputStream in;
        // Initialises FileInputStream using File and reads all bytes from file.
        try {
            in = new FileInputStream(file);

            in.read(bytes);

            contents = new String(bytes);

            in.close();
        } catch (FileNotFoundException e) {
            contents = "File not found.";
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            contents = "File cannot be read.";
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        // If file was empty, set text to display this.
        if(contents.equals(""))
            contents = "No data found.";

        return contents;
    }

}
