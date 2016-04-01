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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserEntryActivity extends AppCompatActivity {

    EditText sleptAt;
    EditText sleptUntil;
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

        // CHECK TO SEE IF ALREADY ENTERED DATA - IF SO....
//        initForm();
        TextView date = (TextView)findViewById(R.id.yesterday_date);
        date.setTextSize(16);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        date.setText("Sleep data for: " + format.format(c.getTime()));
    }

    public void initForm() {
//        TextView date = (TextView)findViewById(R.id.yesterday_date);

        initListeners();
    }

    public void initListeners() {
        sleptFor = (TextView)findViewById(R.id.slept_for);
//        sleptAt = (EditText)findViewById(R.id.edit_sleep_at);
//        sleptUntil = (EditText) findViewById(R.id.edit_sleep_until);

        sleptAt.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
//                sleptFor.setText("You slept forString.valueOf(i) + " / " + String.valueOf(charCounts));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        sleptUntil.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
//                sleptFor.setText(String.valueOf(i) + " / " + String.valueOf(charCounts));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    public void onSubmit(View v){
        Toast.makeText(this, "Data Submitted", Toast.LENGTH_LONG).show();
    }
    // IF ENTRY FOR TODAY - REMOVE FORM FROM PAGE
    // ON SUBMIT BUTTON - CLEARS FORM FROM PAGE

}
