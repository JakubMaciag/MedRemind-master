package com.alarmreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/*
Klasa do edycji zapisanego badania
 */
public class EditActivityTest extends AppCompatActivity {

    TestDatabaseRepository dbrepo;
    Button btOk;
    EditText name, type, result, hour, date, descr ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_test);

//przypisanie zmiennych do GUI
        btOk = (Button) findViewById(R.id.bt_okTest2);
        name = (EditText) findViewById(R.id.et_nameEditTest2);
        type = (EditText) findViewById(R.id.et_typeEditTest2);
        result = (EditText) findViewById(R.id.et_resultTest2);
        hour = (EditText) findViewById(R.id.et_hourEditTest2);
        date = (EditText) findViewById(R.id.et_dateTest2);
        descr = (EditText) findViewById(R.id.et_descrTest2);
//pobranie danych z danego zapisanego badania
        final String medname = getIntent().getStringExtra("name");
        final String medtype = getIntent().getStringExtra("type");
        final String medresult = getIntent().getStringExtra("result");
        final String medhour = getIntent().getStringExtra("hour");
        final String metdate = getIntent().getStringExtra("date");
        final String metdescr = getIntent().getStringExtra("description");
//odwołanie do bazy danych
        dbrepo = new TestDatabaseRepository(this);

        name.setText(medname);
        type.setText(medtype);
        result.setText(medresult);
        hour.setText(medhour);
        date.setText(metdate);
        descr.setText(metdescr);



        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name1 = String.valueOf(name.getText());
                String type1 = String.valueOf(type.getText());
                String result1 = String.valueOf(result.getText());
                String hour1 = String.valueOf(hour.getText());
                String date1 = String.valueOf(date.getText());
                String descr1 = String.valueOf(descr.getText());

                //blokada złej godziny
                String[] part = hour1.split(":");
                if (part.length==2&&(Integer.valueOf(part[0])>=0)&&Integer.valueOf(part[0])<=23&&Integer.valueOf(part[1])>=0&&Integer.valueOf(part[1])<=59)
                {
                     hour1 = String.valueOf(hour.getText());
                }
                else
                {
                    hour.setText(medhour);
                    hour1 = medhour;
                }

//zapisanie danych do bazy danych
                dbrepo.updateTest(new Test(name1, type1, result1, date1, hour1 , descr1), medname);

                Intent ekranList = new Intent(getApplicationContext(), HistoryOfTests.class);
                startActivity(ekranList);


            }
        });

    }
}