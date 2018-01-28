package com.alarmreminder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/*
klasa służąca do zaadaptoewania tabelki z informacją o badaniu doi listy z badaniami
 */
public class TestAdapter extends ArrayAdapter<Test> {

    ArrayList<Test> testsList = new ArrayList<>();
    private Context mycontext;

    public TestAdapter(Context context, int textViewResourceId, ArrayList<Test> objects) {
        super(context, textViewResourceId, objects);
        testsList = objects;

        mycontext = context;
    }
    public int getCount() {
        return super.getCount();
    }

//dodanie informacji do okna o badaniu
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.test_row_layout, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_name2);
        TextView textView2 = (TextView) v.findViewById(R.id.tv_type2);
        TextView textView3 = (TextView) v.findViewById(R.id.tv_result2);
        TextView textView4 = (TextView) v.findViewById(R.id.tv_hourTest2);
        TextView textView5 = (TextView) v.findViewById(R.id.tv_date2);
        TextView textView6 = (TextView) v.findViewById(R.id.tv_descr2);





        Button editButton = (Button) v.findViewById(R.id.bt_editTestRow);


        textView.setText(testsList.get(position).getName());
        textView2.setText(testsList.get(position).getType());
        textView3.setText(testsList.get(position).getResult());
        textView4.setText(testsList.get(position).getHour());
        textView5.setText(testsList.get(position).getDate());
        textView6.setText(testsList.get(position).getDescription());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//przekazanie danych do okna edycji
                Intent intent = new Intent(mycontext, EditActivityTest.class);

                intent.putExtra("name", testsList.get(position).getName());
                intent.putExtra("type", testsList.get(position).getType());
                intent.putExtra("result", testsList.get(position).getResult());
                intent.putExtra("hour", testsList.get(position).getHour());
                intent.putExtra("date", testsList.get(position).getDate());
                intent.putExtra("description", testsList.get(position).getDescription());

                mycontext.startActivity(intent);
            }
        });


        return v;

    }

}