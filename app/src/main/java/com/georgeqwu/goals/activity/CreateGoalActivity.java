package com.georgeqwu.goals.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.georgeqwu.goals.R;
import com.georgeqwu.goals.utility.DateUtility;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by glookogeorge on 5/29/17.
 */

public class CreateGoalActivity extends AppCompatActivity {

    private EditText mEndDateEditText;
    private TextInputLayout mEndDateTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.create_goal);

        setUpButtonsAndListeners();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpButtonsAndListeners() {
        mEndDateEditText = (EditText) findViewById(R.id.end_date_edit_text);
        mEndDateTextInputLayout = (TextInputLayout) findViewById(R.id.input_layout_end_date);
        mEndDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date today = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(today);
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateGoalActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar setCalendar = Calendar.getInstance();
                        setCalendar.set(year, month, dayOfMonth);

                        mEndDateEditText.setText(DateUtility.formatLocalDateAmerican(setCalendar.getTime()));
                        mEndDateTextInputLayout.setHint(getResources().getString(R.string.goal_finish_date));
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(today.getTime());
                datePickerDialog.show();
            }
        });
    }

}
