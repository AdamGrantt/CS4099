package uk.co.adamgrant.cs4099;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class DataCollectionService extends Service {
    private BroadcastReceiver mReceiver;
    private File dirPath;
    public DataCollectionService() {
        mReceiver = new ScreenReceiver();
        dirPath = new File("/data/data/uk.co.adamgrant.cs4099/files");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // Init file
        File file = new File(dirPath, "lockData.txt");

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

        registerReceiver(mReceiver, filter);
    }

    // ****************************** FOR HANDLING STORAGE OF LOCK/UNLOCK DATA ******************************
    public class ScreenReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Calendar c = Calendar.getInstance();


            long time = c.getTimeInMillis();

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
            {
                Log.v("$$$$$$", "In Method: ACTION_SCREEN_OFF");
                writeToFile("Locked " + time);
//                writeToFile("Locked " + year + ", " + month + ", " + day + ", " + hour + ", " + minute + ", " + second);
                // onPause() will be called.
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
            {
                Log.v("$$$$$$", "In Method: ACTION_SCREEN_ON");

                writeToFile("Unlocked " + time);
//                writeToFile("Unlocked " + year + ", " + month + ", " + day + ", " + hour + ", " + minute + ", " + second);

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
        File file = new File(dirPath, "lockData.txt");
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
    public void onDestroy()
    {
        super.onDestroy();
        Log.v("$$$$$$", "In Method: onDestroy()");

        if(mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }
}
