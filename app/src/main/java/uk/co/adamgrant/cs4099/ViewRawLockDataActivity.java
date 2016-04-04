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

public class ViewRawLockDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_lock_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayData();
                Toast.makeText(ViewRawLockDataActivity.this, "Refreshed", Toast.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        displayData();
    }

    public void displayData() {
        // Create the text view for the lock data
        TextView textView = (TextView) findViewById(R.id.raw_lock_data);
        textView.setText(readFile());
    }

    public String readFile()
    {
        File path = ViewRawLockDataActivity.this.getFilesDir();
        File file = new File(path, "lockData.txt");
        String contents;
        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in;
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

        if(contents.equals(""))
            contents = "No data found.";

        return contents;
    }

}
