package uk.co.adamgrant.cs4099;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to store the Lock Data retrieved from file.
 */
public class LockData {
    ArrayList<LockDataEntry> entries;

    public LockData() {
        entries = new ArrayList<>();
        loadData();
    }

    /**
     * Adds a LockDataEntry to the arrayList
     * @param entry
     */
    public void addEntry(LockDataEntry entry) {
        entries.add(entry);
    }

    /**
     *  Getter for the entries ArrayList
     * @return arrayList entries
     */
    public ArrayList<LockDataEntry> getEntries() {
        return entries;
    }

    /**
     * Getter for the entries size
     * @return size of entries ArrayList
     */
    public int getNoEntries() {
        return entries.size();
    }

    /**
     * Loads Data from file into the ArrayList of LockDataEntrys
     */
    private void loadData() {
        // Initialise the directory path and the file.
        File dirPath = new File("/data/data/uk.co.adamgrant.cs4099/files");
        File file = new File(dirPath, "lockData.txt");

        BufferedReader br;

        // Initialises the BufferedReader with the File, and retrieves the
        // data from file, adding entries to the ArrayList
        try {
            br = new BufferedReader(new FileReader(file));
            String line;

            // Iterates through the entire file, line by line, parsing
            // each line and creating a new LockDataEntry with the data,
            // adding it to the entries ArrayList
            while (((line = br.readLine()) != null) && !line.equals("")) {
                String[] split = line.split(",");
                entries.add(new LockDataEntry(Integer.parseInt(split[0]), Long.parseLong(split[1])));
            }
            br.close();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }
}
