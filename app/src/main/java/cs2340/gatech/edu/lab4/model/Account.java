package cs2340.gatech.edu.lab4.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zumong on 2/16/18.
 */

public class Account {

    private int key;
    private String username;
    private String password;
    AccountType type;
    private boolean isBanned = false;
    private boolean hasReservation = false;

    /**
     * account constructor
     * @param user username
     * @param pass password
     * @param t USER or ADMIN account type
     */
    public Account(int k, String user, String pass, AccountType t, boolean ban) {
        key = k;
        username = user;
        password = pass;
        type = t;
        isBanned = ban;
    }
    public static List<AccountType> legalAccountTypes = Arrays.asList(AccountType.USER,AccountType.ADMIN);


    public int getKey() {return key;}
    /**
     * getter for username
     * @return username of Account
     */
    public String getUsername() { return username; }

    /**
     * getter for password
     * @return password of Account
     */
    public String getPassword() { return password; }

    /**
     * getter for reservation status
     * @return boolean for whether or not the account has a reservation
     */
    public boolean getHasReservation() {return hasReservation;}

    /**
     * getter for account type
     * @return USER or ADMIN depending on account type
     */
    public AccountType getAccountType() {
        return type;
    }

    public boolean getBanState() {return isBanned; }

    /**
     * toString for Account
     * @return string representation of the account
     */
    public String toString() {
        return username ;
    }

    /**
     * Sets the boolean status of whether or not a user has a reservation
     * @param has boolean status of reservation
     */
    public void setHasReservation(Boolean has) {hasReservation = has;}
    public void setBanState(boolean ban) {isBanned = ban;}
    /**
     * equals override for accounts, checks if username is equivalent
     * @param o Object that is cast into an Account
     * @return boolean true if the same account, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        Account account = (Account) o;
        return account.username.equals(((Account) o).getUsername());
    }

}
