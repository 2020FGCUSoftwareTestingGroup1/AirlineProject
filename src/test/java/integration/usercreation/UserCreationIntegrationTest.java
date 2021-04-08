package integration.usercreation;

import database.Database;
import database.IDatabase;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import view.Main;

import java.sql.SQLException;

public class UserCreationIntegrationTest {
    FrameFixture window;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Mock
    IDatabase mockDatabase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        Database.setDatabase(mockDatabase);

        Main frame = GuiActionRunner.execute(() -> new Main());
        window = new FrameFixture(frame);
        window.show();
    }

    /**
     * Integration test that creates a window and navigates to the UserCreation screen, enters a series of valid inputs
     * then calls IDatabase.saveUser. Instead of using a real database, Mockito is used to create a stub database that
     * doesn't actually save a user but instead allows us to verify that the IDatabase.saveUser method is called.
     * @throws SQLException
     */
    @Test
    void newUserCreatedGivenValidInputs() throws SQLException {
        window.menuItem("userMenuItem").click();
        window.menuItem("userCreationScreenButton").click();

        window.textBox("userCreationFirstNameInput").enterText("username");
        window.textBox("userCreationLastNameInput").enterText("lastname");
        window.textBox("userCreationUserNameInput").enterText("username");
        window.textBox("userCreationPasswordInput").enterText("password");
        window.button("addUserButton").click();

        Mockito.verify(mockDatabase).saveUser(Mockito.any());
    }

    /**
     * Integration test that creates navigates to the UserCreation screen and attempts to create a user without
     * providing a valid input, which should fail due to userCreation.isValidUser requiring a valid username, password,
     * first name, and last name.
     */
    @Test
    void noUserCreatedGivenInvalid() {
        window.menuItem("userMenuItem").click();
        window.menuItem("userCreationScreenButton").click();

        window.button("addUserButton").click();
        Mockito.verifyNoInteractions(mockDatabase);
    }

    @AfterEach
    void cleanup() {
        window.cleanUp();
    }

}
