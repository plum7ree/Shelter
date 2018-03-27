package cs2340.gatech.edu.lab4.model;

import java.util.ArrayList;

import cs2340.gatech.edu.lab4.controller.FirebaseController;


public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance;}
    static FirebaseController controller = FirebaseController.getInstance();

    private Model() {

    }
    private static ArrayList<Account> _accounts = new ArrayList<Account>();
    private static ArrayList<Shelter> _shelters = new ArrayList<>();
    private static int _numAccounts = _accounts.size();
    private Shelter _currentShelter;
    private final Shelter theNullShelter = new Shelter(0,"no such shelter","capacity",0,"restriction",0,0,"address","note","phone");

    public static ArrayList getAccountList() {
        return _accounts;
    }

    public static ArrayList getUserList() {
        ArrayList<Account> result = new ArrayList<Account>();
        for(Account a: _accounts) {
            if(a.getAccountType().equals(AccountType.USER)) {
                result.add(a);
            }
        }
        return result;
    }

    public static ArrayList getAdminList() {
        ArrayList<Account> result = new ArrayList<Account>();
        for(Account a: _accounts) {
            if(a.getAccountType().equals(AccountType.ADMIN)) {
                result.add(a);
            }
        }
        return result;
    }

    public static void getAccountsFromDatabase(Account a){
        _accounts.add(a);
        updateNumAccounts();
    }

    public static void addNewAccount(String user, String pass, AccountType type) {
        _accounts.add(new Account(user,pass,type));
        updateNumAccounts();
        String id = String.valueOf(_numAccounts - 1);
        FirebaseController.postAccount(id,user,pass,type);

    }

    /**
     * This method is called in LoginActivity searching username and password are in the ArrayList
     * @param user
     * @param password
     * @return
     */
    public static boolean isValidUserAndPassword(String user, String password) {
        user = user.replaceAll("\\s", "");
        for (Object a : getAccountList()) {
                System.out.println("account from array" + a);
                if(((Account)a).getUsername().equals(user)
                        && ((Account)a).getPassword().equals(password)) { return true;}


        }
        return false;
    }

    public static void addShelter(int key, String name, String cap,int aBed, String restr, float longi, float lati, String addr, String note, String phoneNum) {
        boolean shelterFound = false;
        if (!_shelters.isEmpty()) {
            for (Shelter shelter: _shelters) {
                if (shelter.getKey() == key) {
                    shelterFound = true;
                }
            }
            if (!shelterFound) {
                _shelters.add(new Shelter(key, name, cap, aBed, restr,longi,lati,addr,note,phoneNum));
            }
        } else {
            _shelters.add(new Shelter(key, name, cap, aBed, restr,longi,lati,addr,note,phoneNum));
        }

    }
    public static void addShelter(Shelter newShelter) {
        boolean shelterFound = false;
        if (!_shelters.isEmpty()) {
            for (Shelter shelter : _shelters) {
                System.out.println("Shelter from array: " + shelter);
                if (shelter.getKey() == newShelter.getKey()) {
                    shelterFound = true;
                }
            }
            if (!shelterFound) {
                _shelters.add(newShelter);
            }
        } else {
            _shelters.add(newShelter);
        }

    }

    public static ArrayList<Shelter> getShelters() {
        return _shelters;
    }
    private static void updateNumAccounts() {
        _numAccounts = _accounts.size();
    }
    public Shelter getCurrentShelter() {
        return _currentShelter;
    }
    public void setCurrentShelter(Shelter shelter) {
        _currentShelter = shelter;
    }
    public Shelter getShelterById(int id) {
        for (Shelter s: _shelters) {
            if (s.getKey() == id) {
                return s;
            }
        }
        return theNullShelter;
    }
    public void setShelterById(int id, Shelter newShelter) {
        for (int i = 0; i < _shelters.size(); i++) {
            if (_shelters.get(i).getKey() == id) {
                _shelters.set(i, newShelter);
            }
        }
    }


}
