package cs2340.gatech.edu.lab4.controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import cs2340.gatech.edu.lab4.model.Account;
import cs2340.gatech.edu.lab4.model.AccountType;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.Shelter;

/**
 * Created by mike on 2/20/18.
 */

public class FirebaseController {
    public static FirebaseController getInstance() { return _instance; }
    private static final FirebaseController _instance = new FirebaseController();
    static FirebaseDatabase database;
    static DatabaseReference myRef;

    private FirebaseController() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    public static void init() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long numAccounts = dataSnapshot.child("accounts").getChildrenCount();
                RegisterActivity reg = new RegisterActivity();
                if (numAccounts != 0) {
                    for (int i = 0; i < numAccounts; i++) {
                        if (dataSnapshot.child("accounts/" + i + "/accountType").getValue() != null) {
                            String type = dataSnapshot.child("accounts/" + i + "/accountType").getValue().toString();
                            String aStr = dataSnapshot.child("accounts/" + i).getValue().toString();
                            Gson g = new Gson();
                                Account a = g.fromJson(aStr, Account.class);
                                if (!reg.isUserExists(a.getUsername())) {
                                    Model.getAccountsFromDatabase(a);
                                }
                            }
                        }
                    }

                long numShelters = dataSnapshot.child("shelters").getChildrenCount();
                if (numShelters != 0) {
                    for (int i = 0; i < numShelters; i++) {
                        if (dataSnapshot.child("shelters/" + i).getValue() != null) {
                            String aShelter = dataSnapshot.child("shelters/" + i).getValue().toString();
                            Gson g = new Gson();
                            Shelter shelter = g.fromJson(aShelter, Shelter.class);
                            Model.addShelter(shelter);
                        }
                    }
                }

                System.out.println("Shelter from array: " + Model.getShelters());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void postAccount(String id, String username, String password, AccountType type) {
        Account a = new Account(username,password,type);
        myRef.child("accounts/" + id).setValue(a);

    }
    public static void updateAvailableBeds(Shelter currentShelter, int availableBeds) {
        int key = currentShelter.getKey();
        myRef.child("shelter/" + key + "/availableBeds").setValue(availableBeds);

    }

}
