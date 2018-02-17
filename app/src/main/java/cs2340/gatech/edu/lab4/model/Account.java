package cs2340.gatech.edu.lab4.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zumong on 2/16/18.
 */

public abstract class Account {
    public static List<AccountType> legalAccountTypes = Arrays.asList(AccountType.USER,AccountType.ADMIN);

    String username;
    String password;
    AccountType type;

    @Override
    public boolean equals(Object o) {
        Account account = (Account) o;
        return account.username.equals(((Account) o).username);
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
