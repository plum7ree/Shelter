package cs2340.gatech.edu.lab4.controller;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cs2340.gatech.edu.lab4.model.Account;
import cs2340.gatech.edu.lab4.model.AccountType;
import cs2340.gatech.edu.lab4.model.Admin;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.Shelter;
import cs2340.gatech.edu.lab4.model.User;

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
                            if (type.equals("ADMIN".toString())) {
                                Admin u = g.fromJson(aStr, Admin.class);
                                if (!reg.isUserExists(u.getUsername())) {
                                    Model.addToAdmins(u);
                                }
                            } else if (type.equals("USER".toString())) {
                                User u = g.fromJson(aStr, User.class);
                                if (!reg.isUserExists(u.getUsername())) {
                                    Model.addToUsers(u);
                                }
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
                            Model.addToShelters(shelter);
                        }
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void postAccount(String id, String username, String password, AccountType type) {
        User user = new User(username,password,type);
        myRef.child("accounts/" + id).setValue(user);

    }
}
