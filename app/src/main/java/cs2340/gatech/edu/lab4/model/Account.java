package cs2340.gatech.edu.lab4.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zumong on 2/16/18.
 */

public class Account {

    private String _username;
    private String _password;
    AccountType _type;

    public Account(String username, String pass, AccountType t) {
        _username = username;
        _password = pass;
        _type = t;
    }
    public static List<AccountType> legalAccountTypes = Arrays.asList(AccountType.USER,AccountType.ADMIN);

    public String getUsername() { return _username; }
    public String getPassword() { return _password; }


    public AccountType getAccountType() {
        return _type;
    }
    public String toString() {
        return _username + " " + _password + " " + _type;
    }

    @Override
    public boolean equals(Object o) {
        Account account = (Account) o;
        return account._username.equals(((Account) o).getUsername());
    }

}
