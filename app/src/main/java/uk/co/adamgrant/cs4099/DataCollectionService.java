package uk.co.adamgrant.cs4099;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

/**
 * Service which runs in the background storing data relating to the locking
 * and unlocking of the phone.
 */
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
                writeToFile("0," + time);
                // onPause() will be called.
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
            {
                Log.v("$$$$$$", "In Method: ACTION_SCREEN_ON");

                writeToFile("1," + time);
            }
            else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT))
            {
                Log.v("$$$$$$", "In Method: ACTION_USER_PRESENT");
            }
        }
    }

    private void writeToFile(String data) {
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
