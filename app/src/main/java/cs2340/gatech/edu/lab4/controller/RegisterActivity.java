package cs2340.gatech.edu.lab4.controller;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;

public class RegisterActivity extends AppCompatActivity {

    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void onRegisterPressed(View view) {
        TextView username = (TextView) findViewById(R.id.username_field);
        TextView password = (TextView) findViewById(R.id.password_field);
        TextView confirmPassword = (TextView) findViewById(R.id.confirm_password_field);
        if(isUsernameWrong(username.getText().toString())) {
            Snackbar.make(view, "Username is wrong.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if(!isUserExists(username.getText().toString())) {
            if(isValidPassword(password.getText().toString(), confirmPassword.getText().toString())) {
                model.setUserAndPassword(username.getText().toString(),password.getText().toString());
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

    private boolean isUserExists(String str){
        return model.getUserAndPassword().containsKey(str);
    }

    private boolean isUsernameWrong(String str) {
        return str.replaceAll("\\s+","").equals("");
    }

    private boolean isValidPassword(String pass, String confirm_pass) {
        return pass.equals(confirm_pass);
    }
}
