package uk.co.adamgrant.cs4099;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserEntryActivity extends AppCompatActivity {
    int hours = 0;
    int minutes = 0;

    EditText sleptAtDay;
    EditText sleptAtMonth;
    EditText sleptAtYear;
    EditText sleptAtHour;
    EditText sleptAtMinute;
    EditText sleptUntilDay;
    EditText sleptUntilMonth;
    EditText sleptUntilYear;
    EditText sleptUntilHour;
    EditText sleptUntilMinute;

    TextView sleptFor;

    private File dirPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initFile();
        initForm();
    }

    public void initFile() {
        dirPath = new File("/data/data/uk.co.adamgrant.cs4099/files");
        // Init file
        File file = new File(dirPath, "userSleepData.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(this.openFileOutput("userSleepData.txt", Context.MODE_APPEND));
            out.write(data);
            out.write('\n');
            out.close();
            Log.v("$$$$$$", "In Method: writeToFile(), " + data);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void initForm() {
        TextView date = (TextView)findViewById(R.id.yesterday_date);
        date.setTextSize(16);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        date.setText("Todays date: " + format.format(c.getTime()));

        initListeners();
    }

    public void initListeners() {
        sleptFor = (TextView)findViewById(R.id.slept_for);
        sleptAtDay = (EditText)findViewById(R.id.edit_sleep_at_day);
        sleptAtMonth = (EditText)findViewById(R.id.edit_sleep_at_month);
        sleptAtYear = (EditText)findViewById(R.id.edit_sleep_at_year);
        sleptAtHour = (EditText)findViewById(R.id.edit_sleep_at_hour);
        sleptAtMinute = (EditText)findViewById(R.id.edit_sleep_at_minute);
        sleptUntilDay = (EditText) findViewById(R.id.edit_sleep_until_day);
        sleptUntilMonth = (EditText) findViewById(R.id.edit_sleep_until_month);
        sleptUntilYear = (EditText) findViewById(R.id.edit_sleep_until_year);
        sleptUntilHour = (EditText) findViewById(R.id.edit_sleep_until_hour);
        sleptUntilMinute = (EditText) findViewById(R.id.edit_sleep_until_minute);

        sleptUntilDay.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });

        sleptUntilMonth.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });

        sleptUntilYear.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });

        sleptUntilHour.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });

        sleptUntilMinute.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });

        sleptAtDay.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });

        sleptAtMonth.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });

        sleptAtYear.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });

        sleptAtHour.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });

        sleptAtMinute.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });
    }

    public boolean checkEntryValid() {
        TextView sleptUntilFlag = (TextView) findViewById(R.id.sleep_until_valid);
        TextView sleptAtFlag = (TextView) findViewById(R.id.sleep_at_valid);

        // Check SleptDay
        if(!sleptUntilDay.getText().toString().equals("")) {
            if (Integer.parseInt(sleptUntilDay.getText().toString()) > 31) {
                sleptUntilFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }

        // Check SleptMonth
        if(!sleptUntilMonth.getText().toString().equals("")) {
            if (Integer.parseInt(sleptUntilMonth.getText().toString()) > 12) {
                sleptUntilFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }

        // Check SleptYear
        if(!sleptUntilYear.getText().toString().equals("")) {
            if (Integer.parseInt(sleptUntilYear.getText().toString()) > Calendar.getInstance().get(Calendar.YEAR)) {
                sleptUntilFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }

        // Check SleptHour
        if(!sleptUntilHour.getText().toString().equals("")) {
            if (Integer.parseInt(sleptUntilHour.getText().toString()) > 24) {
                sleptUntilFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }

        // Check SleptMinute
        if(!sleptUntilMinute.getText().toString().equals("")) {
            if(Integer.parseInt(sleptUntilMinute.getText().toString()) > 60) {
                sleptUntilFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }

        sleptUntilFlag.setVisibility(View.INVISIBLE);

        // Check WokeDay
        if(!sleptAtDay.getText().toString().equals("")) {
            if(Integer.parseInt(sleptAtDay.getText().toString()) > 31) {
                sleptAtFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }

        // Check WokeMonth
        if(!sleptAtMonth.getText().toString().equals("")) {
            if(Integer.parseInt(sleptAtMonth.getText().toString()) > 12) {
                sleptAtFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }

        // Check WokeYear
        if(!sleptAtYear.getText().toString().equals("")) {
            if(Integer.parseInt(sleptAtYear.getText().toString()) > Calendar.getInstance().get(Calendar.YEAR)) {
                sleptAtFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }

        // Check WokeHour
        if(!sleptAtHour.getText().toString().equals("")) {
            if(Integer.parseInt(sleptAtHour.getText().toString()) > 24) {
                sleptAtFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }
        // Check WokeMinute
        if(!sleptAtMinute.getText().toString().equals("")) {
            if(Integer.parseInt(sleptAtMinute.getText().toString()) > 60) {
                sleptAtFlag.setVisibility(View.VISIBLE);
                return false;
            }
        }

        sleptAtFlag.setVisibility(View.INVISIBLE);

        return true;
    }

    public void calcTimeSlept() {
        hourSlept();
        minutesSlept();
    }

    public void hourSlept() {
        hours = 0;
        int wokeHour;
        int sleptHour;
        if(sleptUntilHour.getText().toString().equals("")){
            wokeHour = 0;
        } else {
            wokeHour = Integer.parseInt(sleptUntilHour.getText().toString());
        }

        if(sleptAtHour.getText().toString().equals("")){
            sleptHour = 0;
        } else {
            sleptHour = Integer.parseInt(sleptAtHour.getText().toString());
        }

        if((wokeHour - sleptHour) > 0){
            hours = wokeHour - sleptHour;
        } else if ((wokeHour - sleptHour) < 0) {
            hours = (24-sleptHour) + wokeHour;
        }
    }

    public void minutesSlept() {
        minutes = 0;
        int wokeMinute;
        int sleptMinute;
        if(sleptUntilMinute.getText().toString().equals("")){
            wokeMinute = 0;
        } else {
            wokeMinute = Integer.parseInt(sleptUntilMinute.getText().toString());
        }

        if(sleptAtMinute.getText().toString().equals("")){
            sleptMinute = 0;
        } else {
            sleptMinute = Integer.parseInt(sleptAtMinute.getText().toString());
        }

        if((wokeMinute - sleptMinute) > 0){
            minutes = wokeMinute - sleptMinute;
        } else if ((wokeMinute - sleptMinute) < 0) {
            hours --;
            minutes = (60-sleptMinute) + wokeMinute;
        }
    }

    public void formatEditText(EditText editText, String no) {
        if(editText.getText().toString().equals("")) {
            editText.setText(no);
        } else if (no.equals("0000") && editText.length() == 2) {
            String temp = 20 + editText.getText().toString();
            editText.setText(temp);
        } else if (editText.length()==1) {
            String temp = 0 + editText.getText().toString();
            editText.setText(temp);
        }
    }

    public void format() {
        formatEditText(sleptAtDay, "00");
        formatEditText(sleptAtMonth, "00");
        formatEditText(sleptAtYear, "0000");
        formatEditText(sleptAtHour, "00");
        formatEditText(sleptAtMinute, "00");
        formatEditText(sleptUntilDay, "00");
        formatEditText(sleptUntilMonth, "00");
        formatEditText(sleptUntilYear, "0000");
        formatEditText(sleptUntilHour, "00");
        formatEditText(sleptUntilMinute, "00");
    }

    public void onSubmit(View v){
        if(checkEntryValid()) {
            format();
            Toast.makeText(this, "Data Submitted", Toast.LENGTH_LONG).show();

            String toFile = sleptAtDay.getText() + "/" + sleptAtMonth.getText() + "/" + sleptAtYear.getText() + ": " + sleptAtHour.getText() + ":" + sleptAtMinute.getText() + " - " + sleptUntilDay.getText() + "/" + sleptUntilMonth.getText() + "/" + sleptUntilYear.getText() + ": " + sleptUntilHour.getText() + ":" + sleptUntilMinute.getText() + ", " + hours + ":" + minutes;
            writeToFile(toFile);

            // Restart Activity - Refresh Form
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Entry Invalid - Fix Entry", Toast.LENGTH_LONG).show();
        }
    }

    public void startViewData(View v) {
        Intent intent = new Intent(this, ViewRawUserSleepDataActivity.class);
        startActivity(intent);
    }
}
