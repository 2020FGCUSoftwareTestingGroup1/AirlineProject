package integration.usercreation;

import database.Database;
import database.IDatabase;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import view.Main;

/**
 * Class for each Integration Testing Method in the "Add Customer" screen.
 */
public class AddCustomerIntegrationTest {
    private FrameFixture window;

    @Mock
    IDatabase mockDatabase;

    /**
     * The setup method is ran before each test is executed. It opens a mock of the test class,
     * sets the database to the mock database, and executes and opens a window frame which shows
     * the main screen of the project.
     */
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        Database.setDatabase(mockDatabase);

        Main frame = GuiActionRunner.execute(() -> new Main());
        window = new FrameFixture(frame);
        window.show();
    }

    /**
     * The teardown method is a method that is ran after each test is executed. It cleans up the
     * window that is currently running through the test to prepare for the next test to be properly
     * executed.
     */
    @AfterEach
    void teardown() {
        window.cleanUp();
    }

    /**
     * An integration testing method which ensures that it displays the next customer based on their
     * ID.
     */
    @Test
    @Tag("ui")
    void displaysNextCustomerIdOnOpen() {
        // Mockito Test to find a customer.
        Mockito.when(mockDatabase.getNextCustomerId()).thenReturn("CS001");

        // Mockito - Executing the test: Displaying the next customer on file.
        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();
        window.label("nextCustomerIdLabel").requireText("CS001");
    }

    /**
     * An integration testing method which shows an error when there is a missing field that the
     * user did not fill out.
     */
    @Test
    @Tag("ui")
    void errorShownWhenMissingField() {
        // Mockito Test to find a customer.
        Mockito.when(mockDatabase.getNextCustomerId()).thenReturn("CS001");

        //Mockito - Executing the test: Adding a customer with missing fields.
        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();
        window.button("addCustomerAddButton").click();
        window.dialog().requireModal().optionPane().requireMessage("Invalid customer:\n" +
                "- First Name cannot be empty\n" +
                "- Last Name cannot be empty\n" +
                "- Nic No must be a number\n" +
                "- Passport ID cannot be a number\n" +
                "- A Date of birth must be specified\n" +
                "- A gender must be selected\n" +
                "- Contact number cannot be empty\n" +
                "- A photo must be specified");
    }
}
