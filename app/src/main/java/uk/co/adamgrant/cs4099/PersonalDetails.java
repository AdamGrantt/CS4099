package uk.co.adamgrant.cs4099;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to store the user's Personal Details committed to file.
 */
public class PersonalDetails {
    private File dirPath;
    private File file;

    private String email;
    private int title;
    private String name;
    private boolean entered;

    /**
     * Constructor initialises directory path and file.
     */
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

    /**
     * Loads user data from file to private variables
     */
    private void loadData() {
        BufferedReader br;

        // Initialises BufferedReader and retrieves user Personal Details
        // from the File. Assuming the writing format:
        // Title
        // Name
        // Email
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

    /**
     * Method to return whether or not the user has committed their data to file.
     * @return boolean
     */
    public boolean isEntered() {
        return entered;
    }

    /**
     * Getter for the int representing the position in the title spinner
     * @return title spinner position
     */
    public int getTitle() {
        return title;
    }

    /**
     * Getter for the user's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the user's email
     * @return email
     */
    public String getEmail() {
        return email;
    }
}
