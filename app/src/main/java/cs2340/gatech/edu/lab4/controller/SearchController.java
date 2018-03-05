package cs2340.gatech.edu.lab4.controller;

import android.util.Log;

import java.util.ArrayList;

import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.SearchCategory.Age;
import cs2340.gatech.edu.lab4.model.SearchCategory.Gender;
import cs2340.gatech.edu.lab4.model.Shelter;
/*
 * Created by Zumong on 3/3/18.
 */

public class SearchController {

    private static ArrayList<Shelter> _searchResult = new ArrayList<>(Model.getShelters());;

    public static SearchController getInstance() { return _instance; }
    private static SearchController _instance = new SearchController();


    private SearchController() {
    }

    public ArrayList<Shelter> getSearchResult() { return _searchResult; }
    /**
     *
     * @param g
     * @param a
     */
    public void search(Gender g, Age a) {
        _searchResult.clear();
        filterByRestriction(g,a);

    }


    private void filterByRestriction(Gender pg, Age pa) {
        ArrayList<Shelter> temp = new ArrayList<>();
        //filter Model.getShelter by pg
        if(!pg.equals(Gender.ALL)){
            for (Shelter s : Model.getShelters()) { //Here Model
                Gender g = findGenderOfThis(s);
                if(pg.equals(g)) {
                    Log.d("Edit", "pg : " + pg.toString() + ",,,g: " + g.toString());
                    temp.add(s);
                }
            }
        } else {
            for (Shelter s : Model.getShelters()) {
                temp.add(s);
            }
        }
        //filter "temp" by pa, and store to _searchResult
        if(!pa.equals(Age.ALL)) {
            for (Shelter s : temp) {                //Here temp!!!
                Age a = findAgeOfThis(s);
                if(pa.equals(a)) {
                    Log.d("Edit", "pa : " + pg.toString() + ",,,a: " + a.toString());
                    _searchResult.add(s);
                }
            }
        } else {
            for (Shelter s: temp) {
                _searchResult.add(s);
            }
        }
        System.out.println(_searchResult);
    }

    /**
     * return the Enum Gender of given shelter.
     * @param s
     * @return
     */
    private Gender findGenderOfThis(Shelter s) {
        Gender ret = Gender.ALL;
        String r_shelter = s.getRestrictions(); // Women/Children
        String[] r_factored = r_shelter.split("/"); // -> ["Women", "Children"]
        for (String r: r_factored) { //"Women"
            r = r.trim().toLowerCase(); // "women"
            for(Gender g: Gender.values()) { // g will be an element of [ALL("Anyone"), MALE("Men"), FEMALE("Women")]
                if (r.equals(g.toString().toLowerCase())) { // does "women" equals "anyone" ?
                    ret = Gender.getEnum(g.toString());
                }
            }
        }
        return ret;
    }

    private Age findAgeOfThis(Shelter s) {
        Age ret = Age.ALL;
        String r_shelter = s.getRestrictions(); // Women/Children
        String[] r_factored = r_shelter.split("/"); // -> ["Women", "Children"]
        for (String r: r_factored) {
            r = r.trim().toLowerCase(); // "children"
            for(Age g: Age.values()) { // g will be ALL("Anyone"), NEWBORN, CHILDREN, YOUNG_ADULTS
                if (r.equals(g.toString().toLowerCase())) { // "children" equals "anyone" ?
                    ret = Age.getEnum(g.toString());
                }
            }
        }
        Log.d("Edit","Age ret:    " + ret.toString());
        return ret;
    }




}
