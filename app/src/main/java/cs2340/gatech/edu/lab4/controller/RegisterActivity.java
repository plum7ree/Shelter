package cs2340.gatech.edu.lab4.controller;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Account;
import cs2340.gatech.edu.lab4.model.AccountType;
import cs2340.gatech.edu.lab4.model.Model;

public class RegisterActivity extends AppCompatActivity {

    Model model = Model.getInstance();
    private TextView username;
    private TextView password;
    private TextView confirmPassword;
    private Spinner accountTypeSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = findViewById(R.id.username_field);
        password = findViewById(R.id.password_field);
        confirmPassword = findViewById(R.id.confirm_password_field);
        accountTypeSpinner = findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Account.legalAccountTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (adapter != null) {
            accountTypeSpinner.setAdapter(adapter);

        }

    }

    /**
     * according to class,
     * @param view
     */
    public void onRegisterPressed(View view) {

        if(accountTypeSpinner.getSelectedItem() == null) {
            Snackbar.make(view, "Please select Account Type.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if(isUsernameWrong(username.getText().toString())) {
            Snackbar.make(view, "Username is wrong.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if(!isUserExists(username.getText().toString())) {
            if(isValidPassword(password.getText().toString(), confirmPassword.getText().toString())) {
                Model.addNewAccount(username.getText().toString(),password.getText().toString(),
                        (AccountType) accountTypeSpinner.getSelectedItem());
                finish();
            }
            else {
                Snackbar.make(view, "Password does not match the confirm password.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }else {
            Snackbar.make(view, "User already exists.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void onCancelPressed(View view) { finish();}

    /**
     * Check if user exists in ArrayList accounts in Model class.
     * if any account of ArrayList has same username from parameter str, return true
     * @param str
     * @return
     */
    public boolean isUserExists(String str){
        for (Object o: Model.getAccountList()) {
            if (((Account) o).getUsername().equals(str)) return true;
        }
        return false;
    }

    /**
     * Check for invalid username such as empty space " "
     * @param str
     * @return
     */
    private boolean isUsernameWrong(String str) {
        return str.replaceAll("\\s+","").equals("");
    }

    /**
     * Check password and confirm_password is same
     * @param pass
     * @param confirm_pass
     * @return
     */
    private boolean isValidPassword(String pass, String confirm_pass) {
        return pass.equals(confirm_pass);
    }

}
