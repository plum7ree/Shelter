import org.junit.Test;

import cs2340.gatech.edu.lab4.model.Shelter;

import static org.junit.Assert.*;

/**
 * Created by Abigail Cliche on 4/16/2018.
 */
public class ShelterTest {
    private Shelter shelter1 = new Shelter(9, "Open Hands", "20", 10, "", 30, 30, "Atlanta", "", "");
    private Shelter shelter2 = new Shelter(9, "Open Hands", "30", 20, "", 30, 30, "Atlanta", "Hi.", "");
    private Shelter shelter3 = new Shelter(9, "Home", "20", 10, "", 40, 30, "New York", "", "");
    private String aString = "Open Hands";

    @Test
    public void equals() throws Exception {
        //Same object.
        assertEquals(true, shelter1.equals(shelter1));
        //toStrings match.
        assertEquals(true, shelter1.equals(shelter2));
        //toStrings don't match, and not same shelter.
        assertEquals(false, shelter1.equals(shelter3));
        //Not a shelter object passed in.
        assertEquals(false, shelter1.equals(aString));
    }

}