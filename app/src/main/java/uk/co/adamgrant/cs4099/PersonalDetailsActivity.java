package uk.co.adamgrant.cs4099;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

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

    public void editable() {
        editable = true;

        pageTitle.setText(R.string.personal_details_title);
        title.setEnabled(true);
        name.setEnabled(true);
        email.setEnabled(true);
        button.setVisibility(View.VISIBLE);
    }

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

    public void onSubmit(View v) {
        if(checkEntryValid()) {
            // STORE FORM DATA TO FILE
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

    private boolean checkEntryValid() {
        return !name.getText().toString().equals("") && !email.getText().toString().equals("");
    }

    private void detailsToFile() {
        writeToFile(Integer.toString(title.getSelectedItemPosition()));
        writeToFile(name.getText().toString());
        writeToFile(email.getText().toString());
    }

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

    private void emptyFile() {
        File dirPath = new File("/data/data/uk.co.adamgrant.cs4099/files");
        // Init file
        File file = new File(dirPath, "personalDetails.txt");
        file.delete();
        initFile();
    }

    private void initFile() {
        File dirPath = new File("/data/data/uk.co.adamgrant.cs4099/files");
        // Init file
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
