package uk.co.adamgrant.cs4099;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 */
public class LockData {
    ArrayList<LockDataEntry> data;

    public LockData() {
        data = new ArrayList<>();
        loadData();
    }

    public void addEntry(LockDataEntry entry) {
        data.add(entry);
    }

    public ArrayList<LockDataEntry> getData() {
        return data;
    }

    public int getNoEntries() {
        return data.size();
    }

    private void loadData() {
        File dirPath = new File("/data/data/uk.co.adamgrant.cs4099/files");
        File file = new File(dirPath, "lockData.txt");

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
            String line;

            while (((line = br.readLine()) != null) && !line.equals("")) {
                String[] split = line.split(",");
                data.add(new LockDataEntry(Integer.parseInt(split[0]), Long.parseLong(split[1])));
            }
            br.close();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }
}
