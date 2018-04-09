package cs2340.gatech.edu.lab4;

import org.junit.Before;
import org.junit.Test;

import cs2340.gatech.edu.lab4.model.AccountType;
import cs2340.gatech.edu.lab4.model.Model;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Zumong on 4/8/18.
 */

public class ModelTest {
    private Model testModel;
    @Before
    public void setUp() {
        testModel = Model.getInstance();
    }

    //implemented by Jumong Lee
    @Test
    public void testIsValidUserAndPassword(){
        testModel.addNewAccount("abcd","abcd", AccountType.USER);
        String user = "abcd";
        String pass = "abcd";
        assertEquals("return with already existing account is incorrect", true, testModel.isValidUserAndPassword(user,pass));

        pass = "wrongPassword";
        assertEquals("result from wrong password is incorrect", false, testModel.isValidUserAndPassword(user,pass));

        user = "aaa";
        assertEquals("result from non-existing user is incorrect", false, testModel.isValidUserAndPassword(user, pass));
    }
}
