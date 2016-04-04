package uk.co.adamgrant.cs4099;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Activity allowing the submission/viewing/editing of user Personal Details.
 */
public class PersonalDetailsActivity extends AppCompatActivity {
    private boolean editable;
    private PersonalDetails details;

    private TextView pageTitle;
    private Spinner title;
    private EditText name;
    private EditText email;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Initialises the Floating Action Button, on click makes the form editable.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editable) {
                    editable();
                    Toast.makeText(PersonalDetailsActivity.this, "Editable", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PersonalDetailsActivity.this, "Already Editable", Toast.LENGTH_LONG).show();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    /**
     * Method which initialises the form, either leaving empty or loading user data
     */
    public void init() {
        details = new PersonalDetails();

        pageTitle = (TextView) findViewById(R.id.personal_details_title);
        title = (Spinner) findViewById(R.id.spinner_edit_title);
        name = (EditText) findViewById(R.id.edit_name);
        email = (EditText) findViewById(R.id.edit_email);
        button = (Button) findViewById(R.id.button_personal_details_submit);

        if(details.isEntered()){
            editable = false;
            loadForm();
        } else {
            editable = true;
        }
    }

    /**
     * Method which makes the form editable, enabling each part of the form,
     * and making the save button visible.
     */
    public void editable() {
        editable = true;

        pageTitle.setText(R.string.personal_details_title);
        title.setEnabled(true);
        name.setEnabled(true);
        email.setEnabled(true);
        button.setVisibility(View.VISIBLE);
    }

    /**
     * Method which loads the data from file into the form.
     * Makes the form view only also.
     */
    public void loadForm() {
        pageTitle.setText("Your Personal Details:");

        title.setSelection(details.getTitle());
        title.setEnabled(false);

        name.setText(details.getName());
        name.setEnabled(false);

        email.setText(details.getEmail());
        email.setEnabled(false);

        button.setVisibility(View.INVISIBLE);
    }

    /**
     * Method called on saving of the form. Ensures all info has been filled in,
     * writes data to file, then refreshes the Activity.
     * @param v
     */
    public void onSubmit(View v) {
        if(checkEntryValid()) {
            // Either refreshes the file, or initialises if first data entry
            if (details.isEntered()) {
                emptyFile();
            } else {
                initFile();
            }
            detailsToFile();

            // Restart Activity - Refresh Form
            Intent intent = getIntent();
            finish();
            startActivity(intent);

            Toast.makeText(PersonalDetailsActivity.this, "Saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(PersonalDetailsActivity.this, "Please Complete Form", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Returns the result of checking if all form sections have been completed.
     * @return form completion boolean
     */
    private boolean checkEntryValid() {
        return !name.getText().toString().equals("") && !email.getText().toString().equals("");
    }

    /**
     * Method which writes the user's Personal Details to file.
     */
    private void detailsToFile() {
        writeToFile(Integer.toString(title.getSelectedItemPosition()));
        writeToFile(name.getText().toString());
        writeToFile(email.getText().toString());
    }

    /**
     * Method which writes data passed in to file.
     * @param data data to write to file
     */
    private void writeToFile(String data) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(this.openFileOutput("personalDetails.txt", Context.MODE_APPEND));
            out.write(data);
            out.write('\n');
            out.close();
            Log.v("$$$$$$", "In Method: writeToFile(), " + data);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Method which deletes and re initialises the personalDetails.txt file
     */
    private void emptyFile() {
        File dirPath = new File("/data/data/uk.co.adamgrant.cs4099/files");
        File file = new File(dirPath, "personalDetails.txt");
        file.delete();
        initFile();
    }

    /**
     * Method which initialises the Personal Details file.
     */
    private void initFile() {
        File dirPath = new File("/data/data/uk.co.adamgrant.cs4099/files");
        File file = new File(dirPath, "personalDetails.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
