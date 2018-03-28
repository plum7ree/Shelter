package cs2340.gatech.edu.lab4.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.SearchCategory.Age;
import cs2340.gatech.edu.lab4.model.SearchCategory.Gender;
import cs2340.gatech.edu.lab4.model.Shelter;

import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.ARG_AGE;
import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.ARG_GENDER;
import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.currentAgeSearchOption;
import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.currentGenderSearchOption;

/**
 * Created by Zumong on 3/4/18.
 */

public class ShelterSearchPopup extends Activity {

    private static List<Gender> genderCategory = Arrays.asList(Gender.ALL, Gender.MALE,Gender.FEMALE);
    private static List<Age> ageCategory = Arrays.asList(Age.ALL, Age.CHILDREN, Age.NEWBORN, Age.YOUNG_ADULTS);
    static SearchController sc = SearchController.getInstance();


    private Spinner genderSpinner;
    private Spinner ageSpinner;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelter_search_option_popup);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        genderSpinner = findViewById(R.id.gender_spinner);
        ageSpinner = findViewById(R.id.age_spinner);
        searchBar = findViewById(R.id.search_bar);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, genderCategory);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        ArrayAdapter<String> ageAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ageCategory);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        if (getIntent().hasExtra(ARG_GENDER)) {
            genderSpinner.setSelection(findPosition(currentGenderSearchOption));
        }
        if (getIntent().hasExtra(ARG_AGE)) {
            ageSpinner.setSelection(findPosition(currentAgeSearchOption));
        }
    }

    public void onSearchPressed(View view) {
        currentGenderSearchOption = (Gender) genderSpinner.getSelectedItem();
        currentAgeSearchOption = (Age) ageSpinner.getSelectedItem();
        sc.search(searchBar.getText().toString(), (Gender)genderSpinner.getSelectedItem(), (Age)ageSpinner.getSelectedItem());
        finish();
    }

    public void onCancelPressed(View veiw) {
        finish();
    }

    public static int findPosition(Enum code) {
        if(code instanceof Gender) {
            int i = 0;
            while (i < genderCategory.size()) {
                if (code.equals(genderCategory.get(i))) return i;
                ++i;
            }
        }
        return 0;
    }
    public static SearchController getSearchController(){
        return sc;
    }

}
