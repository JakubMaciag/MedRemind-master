package com.alarmreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.alarmreminder.Test;

import java.io.File;
import java.util.ArrayList;

// tworzenie bazy danych

public class TestDatabaseRepository {

    private final SQLiteDatabase database;
//określenie odniesienia do bazy danych za pomocą zmiennych database i określonego kontekstu w aplikacji
    TestDatabaseRepository(Context context) {
        File DatabaseFile = context.getDatabasePath("tests.db").getAbsoluteFile();
        database = SQLiteDatabase.openOrCreateDatabase(DatabaseFile, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Tests(name VARCHAR PRIMARY KEY, type VARCHAR, result VARCHAR, hour VARCHAR, description VARCHAR, date VARCHAR);");


    }
//tworzenie kursora łączącego baze danych z aplikacją
    public ArrayList<Test> getAllTests() {

        Cursor cursor = database.rawQuery("select * from Tests", null);
        ArrayList<Test> listResult = new ArrayList<>();

        if (cursor.moveToFirst()) {
// tworzenie kolejki określającej bazę danych
            while (cursor.isAfterLast() == false) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                String result = cursor.getString(cursor.getColumnIndex("result"));
                String hour = cursor.getString(cursor.getColumnIndex("hour"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String date = cursor.getString(cursor.getColumnIndex("date"));

//tworzenie listy wyjściowej
                listResult.add(new Test(name, type, result, hour, description, date ));
                cursor.moveToNext();
            }
        }

        return listResult;
    }
//wrzucanie danych do bazy
    public void addTest(Test test){
        ContentValues insertValues = new ContentValues();
        insertValues.put("name", test.getName());
        insertValues.put("type", test.getType());
        insertValues.put("result", test.getResult());
        insertValues.put("hour", test.getHour());
        insertValues.put("description", test.getDescription());
        insertValues.put("date", test.getDate());

        database.insert("Tests", null, insertValues);
    }
//usuwanie danych z bazy
    public void removeTest(Test testToRemove){
        database.delete("Tests", "name = ?", new String[]{testToRemove.getName()});
    }
//odświeżenie danych w bazie
    public void updateTest(Test test, String testName) {
        ContentValues insertValues = new ContentValues();
        insertValues.put("name", test.getName());
        insertValues.put("type", test.getType());
        insertValues.put("result", test.getResult());
        insertValues.put("hour", test.getHour());
        insertValues.put("date", test.getDate());
        insertValues.put("description", test.getDescription());

        database.update("Tests", insertValues, "name = ?", new String[]{testName});

//
    }


}
