package com.example.week3listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String collegeList[] = {};
    String selectedColleges[] = {};
    Spinner collegeSpinner;
    Button add;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collegeSpinner = (Spinner)findViewById(R.id.spinner);
        add = (Button)findViewById(R.id.add);
        selectedColleges = new String[10];
        Arrays.fill(selectedColleges, "");

        ListView listview = (ListView) findViewById(R.id.simpleListView);
        collegeList = new String[]{"Seneca", "Humber", "George Brown", "Centennial", "Niagra", "Sheirdan", "Durham"};

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_view_item, R.id.textview, selectedColleges);
        listview.setAdapter(arrayAdapter);

        ArrayAdapter<String> collegeAdapter = new ArrayAdapter<String>(this, R.layout.list_view_item, R.id.textview, collegeList);
        collegeSpinner.setAdapter(collegeAdapter);
        listview.setOnItemClickListener(messageClickedHandler);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String selectedCollege =  collegeSpinner.getSelectedItem().toString();

                boolean contains = false;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    contains = Arrays.stream(selectedColleges).anyMatch(s -> {return s.equals(selectedCollege);});
                }
                System.out.println(contains);



                if(!Arrays.asList(selectedColleges).contains(selectedCollege)) {
                  selectedColleges[index] = selectedCollege;
                  Toast.makeText(getApplicationContext(), selectedCollege + "College Inserted", Toast.LENGTH_SHORT).show();
                  arrayAdapter.notifyDataSetChanged();
                  index++;
              }else {
                  Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
              }
            }
        });
    }

    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            String value = (String)parent.getItemAtPosition(position);
            Toast toast=Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT);
            toast.show();
        }
    };

}
