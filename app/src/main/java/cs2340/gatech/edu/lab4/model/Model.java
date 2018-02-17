package cs2340.gatech.edu.lab4.model;

import android.util.Log;

import java.util.ArrayList;


public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance;}

    private Model() {

    }
    private static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<Admin> admins = new ArrayList<Admin>();

    public static ArrayList getAccountList(AccountType type) {
        if(type.equals(AccountType.USER)) return users;
        else if(type.equals(AccountType.ADMIN)) return admins;
        return null;
    }

    public static void addNewAccount(String user, String pass, AccountType type) {
        if(type.equals(AccountType.ADMIN)) admins.add(new Admin(user,pass,type));
        else if(type.equals(AccountType.USER)) users.add(new User(user,pass,type));
        else Log.d("Edit","Error in addNewAccount: neither admin nor user added");
    }

    /**
     * This method is called in LoginActivity searching username and password are in the ArrayList
     * @param user
     * @param password
     * @return
     */
    public static boolean isValidUserAndPassword(String user, String password) {
        user = user.replaceAll("\\s", "");
        for (AccountType t : AccountType.values()) {
            for (Object o : getInstance().getAccountList(t)){
                if(((Account)o).getUsername().equals(user)
                        && ((Account)o).getPassword().equals(password)) { return true;}
            }

        }
        return false;
    }

}
