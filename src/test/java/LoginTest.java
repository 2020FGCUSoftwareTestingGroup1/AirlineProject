import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import database.Database;
import database.IDatabase;
import org.junit.jupiter.api.Test;


/**
 * This LoginTest test case contains the tests to ensure a valid user is logging into the software product.
 * This test class implements the Decision Table with the following given set of inputs:
 *
 * - Username is Valid (T) && Password is Valid (T) - Log into the System
 * - Username is Valid (T) && Password is Invalid (F) - Fail to login; show error message (password is incorrect)
 * - Username is Invalid (F) && Password is Valid (T) - Fail to login; show error message (User does not exist)
 * - Username is Invalid (F) && Password is Invalid (F) - Fail to login; show error message (incorrect username and pw)
 *
 * The following method
 * @see #userNameAndPasswordReturnsTrue()
 */
public class LoginTest {

    @BeforeAll
    static void setup() {
        Database.setTesting();
    }

    IDatabase database = Database.getDatabase();

    /**
     * This method checks to see if the username and password match in the database, thus prompting a successful login.
     * We do this to ensure security to our software system.
     */
    @Test
    void userNameAndPasswordReturnsTrue() {
        String name = "username";
        String pw = "password";
        Assertions.assertTrue(database.loginUser(name, pw));

    }

    @Test
    void userNameReturnsTrueButPasswordReturnsFalse() {
        String name = "username";
        String pw = "123";
        Assertions.assertFalse(database.loginUser(name, pw));

    }

    @Test
    void userNameReturnsFalseButPasswordReturnsTrue() {
        String name = "Lui";
        String pw = "password";
        Assertions.assertFalse(database.loginUser(name, pw));

    }

    @Test
    void userNameAndPasswordReturnsFalse() {
        String name = "Lui";
        String pw = "123";
        Assertions.assertFalse(database.loginUser(name, pw));

    }
}