package cs2340.gatech.edu.lab4.model;

/**
 * Created by Zumong on 2/14/18.
 */

public class User extends Account{
    public User(String user, String pass, AccountType t) {
        username = user;
        password = pass;
        type = t;
    }
}
