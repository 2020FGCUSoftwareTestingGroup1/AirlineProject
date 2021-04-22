import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import view.userCreation;

import static org.junit.jupiter.api.Assertions.*;

class UserCreationTest {
    /**
     * Test:
     * Satisfies Requirement: #7
     * Test Method: Pairwise Testing
     * <p>
     * Inputs (options):
     * - Username   (only characters of the alphabet, or anything else)
     * - Password   (only characters of the alphabet, or anything else)
     * - First Name (only characters of the alphabet, or anything else)
     * - Last Name  (only characters of the alphabet, or anything else)
     * <p>
     * | Username | Password | First Name | Last Name |
     * |----------|----------|------------|-----------|
     * | Valid    | Valid    | Valid      | Valid     | = true
     * | Valid    | Valid    | Valid      | Invalid   | = false
     * | Valid    | Valid    | Invalid    | Valid     | = false
     * | Valid    | Valid    | Invalid    | Invalid   | = false
     * | Valid    | Invalid  | Valid      | Valid     | = false
     * | Valid    | Invalid  | Valid      | Invalid   | = false
     * | Valid    | Invalid  | Invalid    | Valid     | = false
     * | Valid    | Invalid  | Invalid    | Invalid   | = false
     * | Invalid  | Valid    | Valid      | Valid     | = false
     * | Invalid  | Valid    | Valid      | Invalid   | = false
     * | Invalid  | Valid    | Invalid    | Valid     | = false
     * | Invalid  | Valid    | Invalid    | Invalid   | = false
     * | Invalid  | Invalid  | Valid      | Valid     | = false
     * | Invalid  | Invalid  | Valid      | Invalid   | = false
     * | Invalid  | Invalid  | Invalid    | Valid     | = false
     * | Invalid  | Invalid  | Invalid    | Invalid   | = false
     *
     * @see #canSuccessfullyCreateUserGivenAllParameters()
     * @see #isValidUserFailsWhenMissingParameters()
     */

    @Test
    @Tag("unit")
    @DisplayName("Given a valid first name, last name, username, password, isValidUser returns true")
    void canSuccessfullyCreateUserGivenAllParameters() {
        var userCreation = new userCreation();

        var username = "username";
        var lastname = "lastname";
        var firstname = "first-name";
        var password = "password";

        assertTrue(userCreation.isValidUser(firstname, lastname, username, password));
    }

    @Test
    @Tag("unit")
    @DisplayName("Given an invalid combination of first name, last name, username, and password, returns false.")
    void isValidUserFailsWhenMissingParameters() {
        var userCreation = new userCreation();

        assertFalse(userCreation.isValidUser("first-name", "lastname", "username", ""        ));
        assertFalse(userCreation.isValidUser("first-name", "lastname", ""        , "password"));
        assertFalse(userCreation.isValidUser("first-name", "lastname", ""        , ""        ));
        assertFalse(userCreation.isValidUser("first-name", ""        , "username", "password"));
        assertFalse(userCreation.isValidUser("firstname" , ""        , "username", ""        ));
        assertFalse(userCreation.isValidUser("firstname" , ""        , ""        , "password"));
        assertFalse(userCreation.isValidUser("firstname" , ""        , ""        , ""        ));
        assertFalse(userCreation.isValidUser(""          , "lastname", "username", "password"));
        assertFalse(userCreation.isValidUser(""          , "lastname", "username", ""        ));
        assertFalse(userCreation.isValidUser(""          , "lastname", ""        , "password"));
        assertFalse(userCreation.isValidUser(""          , "lastname", ""        , ""        ));
        assertFalse(userCreation.isValidUser(""          , ""        , "username", "password"));
        assertFalse(userCreation.isValidUser(""          , ""        , "username", ""        ));
        assertFalse(userCreation.isValidUser(""          , ""        , ""        , "password"));
        assertFalse(userCreation.isValidUser(""          , ""        , ""        , ""        ));
    }
}