import database.Database;
import database.IDatabase;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.Login;

import static org.assertj.swing.finder.WindowFinder.findFrame;


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

    IDatabase database = Mockito.mock(IDatabase.class);

    @BeforeEach
    void setup() {
        Database.setDatabase(database);
    }

    /**
     * This method checks to see if the username and password match in the database, thus prompting a successful login.
     * We do this to ensure security to our software system.
     */
    @Test
    void userNameAndPasswordReturnsTrue() {
        Mockito.when(database.loginUser("username", "password")).thenReturn(true);

        String name = "username";
        String pw = "password";
        Assertions.assertTrue(database.loginUser(name, pw));
    }

    @Test
    void userNameReturnsTrueButPasswordReturnsFalse() {
        Mockito.when(database.loginUser("username", "password")).thenReturn(true);

        String name = "username";
        String pw = "123";
        Assertions.assertFalse(database.loginUser(name, pw));

    }

    @Test
    void userNameReturnsFalseButPasswordReturnsTrue() {
        Mockito.when(database.loginUser("username", "password")).thenReturn(true);

        String name = "Lui";
        String pw = "password";
        Assertions.assertFalse(database.loginUser(name, pw));

    }

    @Test
    void userNameAndPasswordReturnsFalse() {
        Mockito.when(database.loginUser("username", "password")).thenReturn(true);


        String name = "Lui";
        String pw = "123";
        Assertions.assertFalse(database.loginUser(name, pw));

    }

    @Test
    void mainWindowOpenedOnSuccessfulLogin() {
        Login frame = GuiActionRunner.execute(() -> new Login());
        FrameFixture window = new FrameFixture(frame);
        window.show();

        Mockito.when(database.loginUser("username", "password")).thenReturn(true);

        window.textBox("usernameInput").setText("username");
        window.textBox("passwordInput").setText("password");
        window.button("loginButton").click();

        findFrame("mainFrame").withTimeout(1000).using(window.robot());
        window.cleanUp();

        Mockito.verify(database, Mockito.times(1)).loginUser("username", "password");
    }

    @Test
    void errorDialogDisplayedOnLoginFailure() {
        Login frame = GuiActionRunner.execute(() -> new Login());
        FrameFixture window = new FrameFixture(frame);
        window.show();

        Mockito.when(database.loginUser("username", "password")).thenReturn(true);

        window.textBox("usernameInput").setText("username");
        window.textBox("passwordInput").setText("wrong_password");
        window.button("loginButton").click();

        var dialogText = window.optionPane().requireMessage("UserName or Password do not Match");

        Assertions.assertNotNull(dialogText);
        window.cleanUp();
    }

    @Test
    void showsErrorMessageOnMissingUsernameOrPassword() {
        testUsernameAndPasswordEntry("username", "");
        testUsernameAndPasswordEntry("", "password");
        testUsernameAndPasswordEntry("", "");
    }

    private void testUsernameAndPasswordEntry(String username, String password) {
        Login frame = GuiActionRunner.execute(() -> new Login());
        FrameFixture window = new FrameFixture(frame);
        window.show();

        Mockito.when(database.loginUser("username", "password")).thenReturn(true);

        window.textBox("usernameInput").setText(username);
        window.textBox("passwordInput").setText(password);
        window.button("loginButton").click();

        var dialogText = window.optionPane().requireMessage("UserName or Password Blank");

        Assertions.assertNotNull(dialogText);
        window.cleanUp();
    }
}