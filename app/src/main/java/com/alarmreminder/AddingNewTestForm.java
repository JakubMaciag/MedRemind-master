package com.alarmreminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class AddingNewTestForm extends AppCompatActivity {

    //baza danych badań
    private TestDatabaseRepository dbrepo;


    //region declaration of tags and IDs
    private static final String TAG = "AddMedRemind";
    private static final int DIALOG_ID = 0;
    private static final int DIALOG_ID3 = 2;
    //endregion

    //region declaration of dates and hours variables
    private int year_start, month_start, day_start;
    private int year_c, month_c, day_c, hours_c, minutes_c;
    //hours
    private int hours, minutes;


    //region declaration of text variables
    private EditText nameOfMedicine, typeOfMedicine, amountOfMedicine, descriptionOfTest;
    private TextView textStartDate, texthours;
    //endregion

    //region declaration of buttons
    Button backToMainWindow, addReminder, startDate, remindHour;
    //endregion

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_test_screen);

        dbrepo = new TestDatabaseRepository(getBaseContext());

        //region connection buttons with GUI
        // Back button
        backToMainWindow = (Button) findViewById(R.id.button_back1);
        // Add reminder button
        addReminder = (Button) findViewById(R.id.button_addMedicineReminder);
        // Start date button
        startDate = (Button) findViewById(R.id.button_startDate);
        //End date button

        //hour button
        remindHour = (Button) findViewById(R.id.button_MedicineHour);
        //endregion

        //region connection text variables with GUI

        //date of test
        textStartDate = (TextView) findViewById(R.id.tv_dateOfTest2);
        //hour of test
        texthours = (TextView) findViewById(R.id.text_medicineHour);
        //name of test
        nameOfMedicine = (EditText) findViewById(R.id.editText_nameOfMedicine);
        //type of test
        typeOfMedicine = (EditText) findViewById(R.id.editText_typeOfMedicine);
        //amount of test
        amountOfMedicine = (EditText) findViewById(R.id.editText_amountOfMedicine);
        //description of test
        descriptionOfTest = (EditText) findViewById(R.id.editText_descriptionOfTest);
        //endregion

        //region Start Date button
        //start day button listener - showing calender
        startDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
        // get the current date
        final Calendar c = Calendar.getInstance();
        year_start = c.get(Calendar.YEAR);
        month_start = c.get(Calendar.MONTH);
        day_start = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
//endregion
        //region hour button
        //hour button listener - showing clock
        remindHour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showDialog(DIALOG_ID3);
            }
        });

        // get the current hour
        final Calendar c3 = Calendar.getInstance();
        hours = c3.get(Calendar.HOUR);
        minutes = c3.get(Calendar.MINUTE);
        updateDisplay3();
//endregion

        //region Add reminder
        addReminder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //updateDisplay();
                try {
                    String name = String.valueOf(nameOfMedicine.getText());
                    String portion = String.valueOf(amountOfMedicine.getText());
                    String type = String.valueOf(typeOfMedicine.getText());
                    String hour = String.valueOf(texthours.getText());
                    String descr = String.valueOf(descriptionOfTest.getText());
                    String date = String.valueOf(textStartDate.getText());

                    dbrepo.addTest(new Test(name, type, portion, date, hour, descr));
                } catch (Exception e) {
                }
                Toast.makeText(getApplicationContext(), "Przypomnienie dodane!", Toast.LENGTH_SHORT).show();

                Intent intent_backToMainWindow1 = new Intent(AddingNewTestForm.this, HistoryOfTests.class);
                startActivity(intent_backToMainWindow1);
            }
        });
        //endregion

        //region Back button listener
        backToMainWindow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent_backToMainWindow = new Intent(AddingNewTestForm.this, MainActivity.class);
                startActivity(intent_backToMainWindow);
            }
        });
        //endregion
    }
    //region update display for start day
    private void updateDisplay() {
        textStartDate.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(year_start).append("-")
                .append(month_start + 1).append("-")
                .append(day_start).append(""));
    }
    //endregion

    //region update display for hours
    private void updateDisplay3() {
        texthours.setText(new StringBuilder()
                .append(hours).append(":")
                .append(minutes).append(""));
    }
    //endregion
    //region return date picker dialog
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, year_start, month_start, day_start);
            case DIALOG_ID3:
                return new TimePickerDialog(this, mTimeSetListener3, hours, minutes, true);
        }
        return null;
    }
//endregion

    // the call back received when the user "sets" the date in the dialog
    //region DatePickerDialog for START DATE
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    year_start = year;
                    month_start = monthOfYear;
                    day_start = dayOfMonth;


                    Calendar cx = Calendar.getInstance();
                    year_c = cx.get(Calendar.YEAR);
                    month_c = cx.get(Calendar.MONTH);
                    day_c = cx.get(Calendar.DAY_OF_MONTH);
                    hours_c = cx.get(Calendar.HOUR);
                    minutes_c = cx.get(Calendar.MINUTE);


                    updateDisplay();
                }
            };
    //endregion

    //region TimePickerDialog for hours
    private TimePickerDialog.OnTimeSetListener mTimeSetListener3 = new TimePickerDialog.OnTimeSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            hours = i;
            minutes = i1;

            Calendar cx = Calendar.getInstance();
            year_c = cx.get(Calendar.YEAR);
            month_c = cx.get(Calendar.MONTH);
            day_c = cx.get(Calendar.DAY_OF_MONTH);
            hours_c = cx.get(Calendar.HOUR);
            minutes_c = cx.get(Calendar.MINUTE);

            updateDisplay3();
        }
    };
    //endregion
}