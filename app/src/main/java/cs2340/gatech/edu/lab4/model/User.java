package cs2340.gatech.edu.lab4.model;

/**
 * Created by Zumong on 2/14/18.
 */

public class User extends Account{
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String user, String pass, AccountType t) {
        username = user;
        password = pass;
        type = t;
    }

    public AccountType getAccountType() {
        return type;
    }
    public String toString() {
        return username + " " + password + " " + type;
    }
}
