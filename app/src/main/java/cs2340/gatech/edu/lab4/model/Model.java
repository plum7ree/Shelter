package cs2340.gatech.edu.lab4.model;

import android.util.Log;

import java.util.HashMap;


public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance;}

    private Model() {

    }

    private static HashMap<String, String> userAndPassword = new HashMap<>();
    static
    {    userAndPassword.put("user", "pass");
    }

    public static HashMap getUserAndPassword() { return userAndPassword;}
    public static void setUserAndPassword(String user, String pass) {
        userAndPassword.put(user, pass);
    }

    public static boolean isValidUserAndPassword(String user, String password) {
        user = user.replaceAll("\\s", "");
        if(userAndPassword.containsKey(user)) {
            Log.d("Edit","it has contain the key");
            if(userAndPassword.get(user).equals(password)) { return true;}
        }
        return false;
    }

}
