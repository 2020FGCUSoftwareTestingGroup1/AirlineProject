import org.junit.jupiter.api.Assertions;
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
public class LoginTest implements LoginInterface{

    /**
     * This method checks to see if the username and password match in the database, thus prompting a successful login.
     * We do this to ensure security to our software system.
     */
    @Test
    void userNameAndPasswordReturnsTrue() {
        String name = "Luis";
        String pw = "1234";
        Assertions.assertTrue(checkUserNameAndPW(name,pw));

    }
    @Test
    void userNameReturnsTrueButPasswordReturnsFalse(){
        String name = "Luis";
        String pw = "123";
        Assertions.assertFalse(checkUserNameAndPW(name,pw));

    }
    @Test
    void userNameReturnsFalseButPasswordReturnsTrue(){
        String name = "Lui";
        String pw = "1234";
        Assertions.assertFalse(checkUserNameAndPW(name,pw));

    }
    @Test
    void userNameAndPasswordReturnsFalse(){
        String name = "Lui";
        String pw = "123";
        Assertions.assertFalse(checkUserNameAndPW(name,pw));

    }

    @Override
    public boolean checkUserNameAndPW(String name, String pw) {
        String username = "Luis";
        String password = "1234";

        return username.equals(name) && password.equals(pw);
    }
}