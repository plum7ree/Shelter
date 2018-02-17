package cs2340.gatech.edu.lab4.model;

/**
 * Created by Zumong on 2/16/18.
 */

public class Admin extends Account {
    public Admin(String user, String pass, AccountType t) {
        username = user;
        password = pass;
        type = t;

    }
}
