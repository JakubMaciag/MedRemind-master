package com.alarmreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
//okno główne do wyświetlania zapisanych badań
public class HistoryOfTests extends AppCompatActivity {

//deklaracja zmiennych
    private ListView testsList;
    private Button buttonAdd,buttonBack;
    private TestAdapter adapter;
    private TestDatabaseRepository dbrepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of_tests);
//powiązanie zmiennych z GUI
        buttonAdd = (Button)findViewById(R.id.bt_addNewTest);
        buttonBack= (Button)findViewById(R.id.bt_backHist);



        testsList = (ListView) findViewById(R.id.list_view2);
//powiązanie z bazą danych
        dbrepo = new TestDatabaseRepository(getBaseContext());
//powiazanie z adapterem okien badań
        adapter = new TestAdapter(this, R.layout.test_row_layout, dbrepo.getAllTests());
//listenery do poszczególnych funkcjonalności danego okna
        testsList.setAdapter(adapter);
        testsList.setLongClickable(true);
        testsList.setOnItemLongClickListener(itemLongClickListener);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ekranMain = new Intent(getApplicationContext(), AddingNewTestForm.class);
                startActivity(ekranMain);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ekranMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(ekranMain);
            }
        });


    }
// obsługa adaptera - czyszczenie/dodawanie/zapis zmian
    private void updateData() {
        ArrayList<Test> tests = dbrepo.getAllTests();

        adapter.clear();
        adapter.addAll(tests);
        adapter.notifyDataSetChanged();
    }

//usuwanie badania po dłuższym naciśnięciu na obszar informacji o badaniu
    private final AdapterView.OnItemLongClickListener itemLongClickListener
            = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Test item = adapter.getItem(position);
            dbrepo.removeTest(item);

            ArrayList<Test> tests = dbrepo.getAllTests();
            adapter.clear();
            adapter.addAll(tests);
            adapter.notifyDataSetChanged();

            return true;
        }
    };



}
