package integration.usercreation;

import database.Database;
import database.IDatabase;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import view.Main;

import java.sql.SQLException;
import java.util.Collections;

public class UserCreationIntegrationTest {
    FrameFixture window;

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
    @Tag("ui")
    void newUserCreatedGivenValidInputs() throws SQLException {
        Mockito.when(mockDatabase.getTickets()).thenReturn(Collections.emptyList());

        window.menuItem("userMenuItem").click();
        window.menuItem("userCreationScreenButton").click();

        window.textBox("userCreationFirstNameInput").setText("username");
        window.textBox("userCreationLastNameInput").setText("lastname");
        window.textBox("userCreationUserNameInput").setText("username");
        window.textBox("userCreationPasswordInput").setText("password");
        window.button("addUserButton").click();

        Mockito.verify(mockDatabase, Mockito.timeout(500)).saveUser(Mockito.any());
    }

    /**
     * Integration test that creates navigates to the UserCreation screen and attempts to create a user without
     * providing a valid input, which should fail due to userCreation.isValidUser requiring a valid username, password,
     * first name, and last name.
     */
    @Test
    @Tag("ui")
    void noUserCreatedGivenInvalid() throws SQLException {
        window.menuItem("userMenuItem").click();
        window.menuItem("userCreationScreenButton").click();

        window.button("addUserButton").click();
        Mockito.verify(mockDatabase, Mockito.never()).saveUser(Mockito.any());
    }

    @AfterEach
    void cleanup() {
        window.cleanUp();
    }

}
