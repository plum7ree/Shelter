package cs2340.gatech.edu.lab4.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zumong on 2/16/18.
 */

public class Account {

    private String username;
    private String password;
    AccountType type;
    private boolean hasReservation = false;

    public Account(String user, String pass, AccountType t) {
        username = user;
        password = pass;
        type = t;
    }
    public static List<AccountType> legalAccountTypes = Arrays.asList(AccountType.USER,AccountType.ADMIN);

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean getHasReservation() {return hasReservation;}

    public AccountType getAccountType() {
        return type;
    }
    public String toString() {
        return username + " " + password + " " + type;
    }
    public void setHasReservation(Boolean has) {hasReservation = has;}

    @Override
    public boolean equals(Object o) {
        Account account = (Account) o;
        return account.username.equals(((Account) o).getUsername());
    }

}
