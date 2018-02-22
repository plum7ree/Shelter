package cs2340.gatech.edu.lab4.model;

import android.util.Log;

import java.util.ArrayList;

import cs2340.gatech.edu.lab4.controller.FirebaseController;


public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance;}
    static FirebaseController controller = FirebaseController.getInstance();

    private Model() {

    }
    private static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    private static ArrayList<Shelter> shelters = new ArrayList<>();
    private static int _numAccounts = users.size() + admins.size();

    public static ArrayList getAccountList(AccountType type) {
        if(type.equals(AccountType.USER)) return users;
        else if(type.equals(AccountType.ADMIN)) return admins;
        return null;
    }

    public static void addNewAccount(String user, String pass, AccountType type) {
        if (type.equals(AccountType.ADMIN)) {
            admins.add(new Admin(user,pass,type));
            updateNumAccounts();
            String id = String.valueOf(_numAccounts - 1);
            FirebaseController.postAccount(id,user,pass,type);
        }
        else if (type.equals(AccountType.USER)) {
            users.add(new User(user,pass,type));
            updateNumAccounts();
            String id = String.valueOf(_numAccounts - 1);
            FirebaseController.postAccount(id,user,pass,type);
        }
        else {
            Log.d("Edit","Error in addNewAccount: neither admin nor user added");
        }

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
            for (Object o : getAccountList(t)){
                System.out.println("account from array" + o);
                if(((Account)o).getUsername().equals(user)
                        && ((Account)o).getPassword().equals(password)) { return true;}
            }

        }
        return false;
    }

    public static void addShelter(int key, String name, String cap, String restr, float longi, float lati, String addr, String note, String phoneNum) {
        boolean shelterFound = false;
        if (!shelters.isEmpty()) {
            for (Shelter shelter: shelters) {
                if (shelter.getKey() == key) {
                    shelterFound = true;
                }
            }
            if (!shelterFound) {
                shelters.add(new Shelter(key,name,cap,restr,longi,lati,addr,note,phoneNum));
            }
        } else {
            shelters.add(new Shelter(key,name,cap,restr,longi,lati,addr,note,phoneNum));
        }

    }
    public static void addShelter(Shelter newShelter) {
        boolean shelterFound = false;
        if (!shelters.isEmpty()) {
            for (Shelter shelter : shelters) {
                System.out.println("Shelter from array: " + shelter);
                if (shelter.getKey() == newShelter.getKey()) {
                    shelterFound = true;
                }
            }
            if (!shelterFound) {
                shelters.add(newShelter);
            }
        } else {
            shelters.add(newShelter);
        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
    public static ArrayList<Admin> getAdmins() {
        return admins;
    }
    public static ArrayList<Shelter> getShelters() {
        return shelters;
    }
    private static void updateNumAccounts() {
        _numAccounts = users.size() + admins.size();
    }
    public static void addToUsers(User user) {
        users.add(user);
    }
    public static void addToAdmins(Admin admin) {
        admins.add(admin);
    }
    public static void addToShelters(Shelter shelter) {
        shelters.add(shelter);
    }

}
