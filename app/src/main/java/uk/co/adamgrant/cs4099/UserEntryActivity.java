package uk.co.adamgrant.cs4099;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserEntryActivity extends AppCompatActivity {
    int hours = 0;
    int minutes = 0;

    EditText sleptAtHour;
    EditText sleptAtMinute;
    EditText sleptUntilHour;
    EditText sleptUntilMinute;

    TextView sleptFor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // IF(!dataEntered())....
        initForm();
        // ELSE ... change content to "ALREADY ENTERED FOR TODAY"
    }

    public void initForm() {
        TextView date = (TextView)findViewById(R.id.yesterday_date);
        date.setTextSize(16);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        date.setText("Sleep data for: " + format.format(c.getTime()));

        initListeners();
    }

    public void initListeners() {
        sleptFor = (TextView)findViewById(R.id.slept_for);
        sleptAtHour = (EditText)findViewById(R.id.edit_sleep_at_hour);
        sleptAtMinute = (EditText)findViewById(R.id.edit_sleep_at_minute);
        sleptUntilHour = (EditText) findViewById(R.id.edit_sleep_until_hour);
        sleptUntilMinute = (EditText) findViewById(R.id.edit_sleep_until_minute);

        sleptAtHour.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");            }
        });

        sleptAtMinute.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");            }
        });

        sleptUntilHour.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");            }
        });

        sleptUntilMinute.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkEntryValid())
                    calcTimeSlept();
                sleptFor.setText("You slept for " + hours + " hours and " + minutes + " minutes");
            }
        });
    }

    public boolean checkEntryValid() {
        TextView sleptUntilFlag = (TextView) findViewById(R.id.sleep_until_valid);
        TextView sleptAtFlag = (TextView) findViewById(R.id.sleep_at_valid);

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

    public void onSubmit(View v){
        if(checkEntryValid()) {
            Toast.makeText(this, "Data Submitted", Toast.LENGTH_LONG).show();
            // SUBMIT DATA
        } else {
            Toast.makeText(this, "Entry Invalid - Fix Entry", Toast.LENGTH_LONG).show();
        }
        // CHANGE ACTIVITY CONTENT TO "YOU HAVE ALREADY ENTERED TODAYS DATA" / REFRESH ACTIVITY
    }
    // If entry single digit, add 0 to start of string
    // IF ENTRY FOR TODAY - REMOVE FORM FROM PAGE
    // ON SUBMIT BUTTON - CLEARS FORM FROM PAGE & SUBMITS DATA

}
