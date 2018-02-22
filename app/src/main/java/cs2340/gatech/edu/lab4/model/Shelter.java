package cs2340.gatech.edu.lab4.model;

/**
 * Created by mike on 2/21/18.
 */

public class Shelter {

    /**the shelter's id number*/
    private int uniqueKey;

    /**the shelter's name*/
    private String shelterName;

    /**the shelter's capacity*/
    private String capacity;

    /**the shelter's restrictions*/
    private String restrictions;

    /**the shelter's longitude*/
    private float longitude;

    /**the shelter's latitude*/
    private float latitude;

    /**the shelter's address*/
    private String address;

    /**the shelter's special notes*/
    private String specialNotes;

    /**the shelter's phone number*/
    private String phoneNumber;

    /******************************
     * Getters
     */
    public int getKey() {
        return uniqueKey;
    }
    public String getName() {
        return shelterName;
    }
    public String getCapacity() {
        return capacity;
    }
    public String getRestrictions() {
        return restrictions;
    }
    public float getLongitude() {
        return longitude;
    }
    public float getLatitude() {
        return latitude;
    }
    public String getAddress() {
        return address;
    }
    public String getSpecialNotes () {
        return specialNotes;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Shelter(int key, String name, String cap, String restr, float longi, float lati, String addr, String note, String phoneNum) {
        uniqueKey = key;
        shelterName = name;
        capacity = cap;
        restrictions = restr;
        longitude = longi;
        latitude = lati;
        address = addr;
        specialNotes = note;
        phoneNumber = phoneNum;
    }
    public Shelter() {

    }

    public String toString() {
        return shelterName;
    }

}
