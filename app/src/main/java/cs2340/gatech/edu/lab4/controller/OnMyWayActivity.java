package cs2340.gatech.edu.lab4.controller;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.Shelter;

public class OnMyWayActivity extends AppCompatActivity {
    private static Shelter shelter = Model.getInstance().getCurrentShelter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_my_way);
    }
    public void onCancelRes(View view){
        int numBeds = Integer.parseInt(getIntent().getStringExtra("numReserved"));
        int newNumBeds = (shelter.getAvailableBeds() + numBeds);
        if (newNumBeds > Integer.parseInt(shelter.getCapacity()) ) {
            newNumBeds = Integer.parseInt(shelter.getCapacity());
        }
        FirebaseController.updateAvailableBeds(shelter, newNumBeds);
        shelter.setAvailableBeds(newNumBeds);
        finish();
    }
    public void onArrivedPressed(View view) {
        finish();
    }

}
