package cs2340.gatech.edu.lab4.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;

public class ShelterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.listview,Model.getShelters());

        ListView listView = (ListView) findViewById(R.id.shelterList);
        listView.setAdapter(adapter);
    }
}
