package cs2340.gatech.edu.lab4.controller;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.controller.FirebaseController;
import cs2340.gatech.edu.lab4.model.Account;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.Shelter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abigail Cliche on 3/26/2018.
 */

public class ReserveActivity extends AppCompatActivity {
    Model model = Model.getInstance();
    private TextView shelterName;
    private TextView availableBeds;
    private Spinner resNumberSpinner;
    private static Shelter shelter = Model.getInstance().getCurrentShelter();
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        shelterName = findViewById(R.id.shelterName);
        shelterName.setText(shelter.toString());
        availableBeds = findViewById(R.id.bedNumber);
        int numAvailableBeds = shelter.getAvailableBeds();
        availableBeds.setText(numAvailableBeds);
        resNumberSpinner = findViewById(R.id.bedSpinner);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
        adapter.addAll(1,2,3,4,5);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (adapter != null) {
            resNumberSpinner.setAdapter(adapter);
        }

    }
    /**send to firebase and activity*/
    public void onOkayPressed(View view){
        int numBeds = (int)resNumberSpinner.getSelectedItem();
        int newNumBeds = (shelter.getAvailableBeds() - numBeds);
        if (newNumBeds < 0) {
            Snackbar.make(view, "There are not enough beds available.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            FirebaseController.updateAvailableBeds(shelter, newNumBeds);
            shelter.setAvailableBeds(newNumBeds);
            Intent intent = new Intent(getBaseContext(), OnMyWayActivity.class);
            intent.putExtra("numReserved", numBeds);
            startActivity(intent);
        }
    }

    public void onCancelPressed(View view) {
        finish();
    }

}

