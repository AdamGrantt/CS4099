package uk.co.adamgrant.cs4099;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 */
public class PersonalDetails {
    private File dirPath;
    private File file;

    private String email;
    private int title;
    private String name;
    private boolean entered;

    public PersonalDetails() {
        dirPath = new File("/data/data/uk.co.adamgrant.cs4099/files");
        file = new File(dirPath, "personalDetails.txt");
        if(file.exists()){
            entered = true;
            loadData();
        } else {
            entered = false;
        }
    }

    private void loadData() {

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));

            title = Integer.parseInt(br.readLine());
            name = br.readLine();
            email = br.readLine();

            br.close();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    public boolean isEntered() {
        return entered;
    }

    public int getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
