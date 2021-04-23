import database.Database;
import database.IDatabase;
import java.util.Date;
import model.Customer;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import view.Main;
import view.addCustomer;

import java.nio.file.Path;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This AddCustomerTest test case contains the tests to ensure a valid customer is added to the
 * database. This test class implements equivalence class testing. The subsequent classes are as follows
 *
 * Equivalence classes:
 * - Only letters.
 * - Not only letters.
 *
 * The following methods
 * @see #canSubmitNameReturnsTrue()
 * tests for valid inputs, and
 * @see #canSubmitNameReturnsFalse()
 * handles all of the invalid inputs for the first name and last name inputs in order to create a new customer.
 */

@Tag("unittest")
public class AddCustomerTest {

    /**
     * The setup method is ran before each test is executed. It opens a mock of the test class,
     * sets the database to the mock database, and executes and opens a window frame which shows
     * the main screen of the project.
     */
    @BeforeEach
    void setup() {
        Database.setDatabase(Mockito.mock(IDatabase.class));
    }

    /**
     * This method checks to see if the first and last name contain only letters for the new customer
     * submission. We are doing this by making sure that no spaces, special characters, and numbers exists.
     *
     * Equivalence classes:
     *
     * - Only letters.
     *
     * @Returns Bool
     */
    @Test
    @Tag("unit")
    void canSubmitNameReturnsTrue() {

        addCustomer customerScreen = new addCustomer();

        // Just letters.
        boolean isValidAlphaCase = customerScreen.canSubmitName("bob");

        Assertions.assertTrue(isValidAlphaCase);
    }


    /**
     * The canSubmitNameReturnsFalse method handles all of the invalid inputs that an end user can input
     * when inputting their first and last name while creating a new customer.
     *
     * Equivalence Classes:
     * - Just letters with numbers.
     * - Just special characters.
     * - Just letters with special characters.
     * - Just numbers with special characters.
     * - Empty string.
     * - Just letters with spaces.
     */
    @Test
    @Tag("unit")
    void canSubmitNameReturnsFalse() {
        addCustomer customerScreen = new addCustomer();

        // Just Letters with numbers.
        boolean isInvalidNumberCase = customerScreen.canSubmitName("bob1");

        // Just special characters.
        boolean isInvalidSpecialCharaCase = customerScreen.canSubmitName("-@#$%^&*");

        // Just Letters with special Characters.
        boolean isInvalidSpecialCharaCaseAndAlpha = customerScreen.canSubmitName("bob!@#$");

        // Just Numbers with special characters.
        boolean isInvalidSpecialCharaAndNumberCase = customerScreen.canSubmitName("123%^&*");

        // Empty String.
        boolean isInvalidEmptyStringCase = customerScreen.canSubmitName("");

        // Just letters with spaces.
        boolean isInvalidAlphaWithSpaceCase = customerScreen.canSubmitName("bo  b");

        Assertions.assertFalse(isInvalidNumberCase);

        Assertions.assertFalse(isInvalidSpecialCharaCaseAndAlpha);
        Assertions.assertFalse(isInvalidSpecialCharaCase);

        Assertions.assertFalse(isInvalidEmptyStringCase);
        Assertions.assertFalse(isInvalidSpecialCharaAndNumberCase);
        Assertions.assertFalse(isInvalidAlphaWithSpaceCase);
    }

    /**
     * Unit testing method which handles all the valid cases that the user can input for the
     * passport ID.
     */
    @Test
    @Tag("unit")
    void canSubmitPassportIDReturnsTrue() {
        addCustomer customerScreen = new addCustomer();

        boolean validPassportIDLowerBVA = customerScreen.canSubmitPassportID("abcefg1");
        boolean validPassportIDHigherBVA = customerScreen.canSubmitPassportID("abc123de");

        Assertions.assertTrue(validPassportIDLowerBVA);
        Assertions.assertTrue(validPassportIDHigherBVA);
    }

    /**
     * Unit testing method which handles all the invalid cases that a user can input for the
     * passport ID.
     */
    @Tag("unit")
    @Test
    void canSubmitPassportIDReturnsFalse() {
        addCustomer customerScreen = new addCustomer();

        // Characters are too low
        boolean inValidPassportIDLowerBVA = customerScreen.canSubmitPassportID("abc22");
        // Characters are too high
        boolean inValidPassportIDHigherBVA = customerScreen.canSubmitPassportID("123abc4567");

        Assertions.assertFalse(inValidPassportIDLowerBVA);
        Assertions.assertFalse(inValidPassportIDHigherBVA);
    }

    /**
     * Integration testing method which verifies that a new customer's fields are all inputted
     * correctly and that a new customer has been uploaded.
     * @throws SQLException In the event that any of the customer's fields are invalid.
     */
    @Test
    @Tag("ui")
    void saveCustomerIsCalledGivenValidCustomer() throws SQLException {
        // Create and set mock database.
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

        // Mock customer ID.
        Mockito.when(database.getNextCustomerId()).thenReturn("CS001");

        // Navigate to Add Customer screen.
        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();

        // Enter test data in screen.
        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerLastNameInput").setText("Smith");
        window.textBox("addCustomerNicInput").setText("11111");
        window.textBox("addCustomerPassportInput").setText("aaaaa123");
        window.textBox("addCustomerContactInput").setText("55555555");
        window.textBox("addCustomerAddressInput").setText("USA");

        window.radioButton("addCustomerMaleGenderLabel").click();

        window.button("addCustomerBrowseButton").click();

        // Select test customer image.
        var root = Path.of("").toAbsolutePath();
        var testFilePath = root.resolve(Path.of("src", "test", "resources", "CustomerExampleImage.jpg"));
        var testFile = testFilePath.toFile();

        window.fileChooser("addCustomerPicChooser").selectFile(testFile);
        window.fileChooser("addCustomerPicChooser").approve();

        window.panel("addCustomerDateInput").textBox().setText("Apr 22, 2021");

        // Submit customer.
        window.button("addCustomerAddButton").click();

        Mockito.verify(database).saveCustomer(Mockito.any());

        window.cleanUp();
    }

    /**
     * Integration testing method that tests for a user adding a new customer with an invalid date
     * of birth.
     *
     * Customer's are checked using an instance of addCustomer, and we use the isValid() method
     * within that class to test for a valid customer. The one that should be invalid is the
     * customer whose date of birth entered is null.
     */
    @Test
    @Tag("ui")
    void addCustomerWithInvalidDOB() {

        // Create and set mock database.
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        addCustomer customerScreen = new addCustomer();

        customerScreen.isValid(
                "John",
                "Smith",
                "11111",
                "111111",
                "1111111",
                true,
                false,
                new Date(),
                new byte[10]
        );

        customerScreen.isValid(
                "",
                "Smith",
                "11111",
                "111111",
                "1111111",
                true,
                false,
                new Date(),
                new byte[10]
        );


        customerScreen.isValid(
                "John",
                "Smith",
                "",
                "111111",
                "1111111",
                true,
                false,
                new Date(),
                new byte[10]
        );

        customerScreen.isValid(
                "John",
                "",
                "11111",
                "111111",
                "1111111",
                true,
                false,
                new Date(),
                new byte[10]
        );

        customerScreen.isValid(
                "John",
                "Smith",
                "11111",
                "",
                "1111111",
                true,
                false,
                new Date(),
                new byte[10]
        );

        customerScreen.isValid(
                "John",
                "Smith",
                "11111",
                "111111",
                "",
                true,
                false,
                new Date(),
                new byte[10]
        );

        customerScreen.isValid(
                "John",
                "Smith",
                "11111",
                "111111",
                "1111111",
                false,
                true,
                new Date(),
                new byte[10]
        );

        customerScreen.isValid(
                "John",
                "Smith",
                "11111",
                "111111",
                "1111111",
                false,
                false,
                new Date(),
                new byte[10]
        );

        customerScreen.isValid(
                "John",
                "Smith",
                "11111",
                "111111",
                "1111111",
                true,
                true,
                new Date(),
                new byte[10]
        );

        //Invalid Customer - DOB is null
        customerScreen.isValid(
                "John",
                "Smith",
                "11111",
                "111111",
                "1111111",
                true,
                false,
                null,
                new byte[10]
        );

        customerScreen.isValid(
                "John",
                "Smith",
                "11111",
                "111111",
                "1111111",
                true,
                false,
                new Date(),
                new byte[0]
        );

        customerScreen.isValid(
                "John",
                "Smith",
                "11111",
                "111111",
                "1111111",
                true,
                false,
                new Date(),
                null
        );

    }

    private void enterCustomerInfo(FrameFixture window, Customer customer) throws ParseException {
        // Enter test data in screen.
        window.textBox("addCustomerFirstNameInput").setText(customer.getFirstName());
        window.textBox("addCustomerLastNameInput").setText(customer.getLastName());
        window.textBox("addCustomerNicInput").setText(customer.getNicNo());
        window.textBox("addCustomerPassportInput").setText(customer.getPassportId());
        window.textBox("addCustomerContactInput").setText(String.valueOf(customer.getContactNumber()));
        window.textBox("addCustomerAddressInput").setText(customer.getAddress());

        var genderLabel = customer.getGender().equals("Female") ? "addCustomerFemaleGenderLabel" : "addCustomerMaleGenderLabel";
        window.radioButton(genderLabel).click();

        window.button("addCustomerBrowseButton").click();

        // Select test customer image.
        var root = Path.of("").toAbsolutePath();
        var testFilePath = root.resolve(Path.of("src", "test", "resources", "CustomerExampleImage.jpg"));
        var testFile = testFilePath.toFile();

        window.fileChooser("addCustomerPicChooser").selectFile(testFile);
        window.fileChooser("addCustomerPicChooser").approve();

        window.panel("addCustomerDateInput").textBox().setText("Apr 01, 2020");
    }

    /**
     * An integration testing method which tests for a valid female customer. This testing method
     * is to ensure that the female radio box works as intended.
     * @throws SQLException In the event that an invalid customer is being added.
     * @throws ParseException In the event that the customer's info is being added incorrectly.
     */
    @Test
    @Tag("ui")
    void saveCustomerIsCalledGivenValidFemaleCustomer() throws SQLException, ParseException {
        // Create and set mock database.
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

        // Mock customer ID.
        Mockito.when(database.getNextCustomerId()).thenReturn("CS001");

        // Navigate to Add Customer screen.
        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();

        var customer = new Customer(
                "CS001",
                "John",
                "Smith",
                "11111",
                "aaaaa123",
                "USA",
                "2021-04-22",
                "Female",
                55555555,
                null
        );

        enterCustomerInfo(window, customer);

        // Submit customer.
        window.button("addCustomerAddButton").click();

        Mockito.verify(database).saveCustomer(Mockito.any());

        window.cleanUp();
    }

    /**
     * Integration testing method that tests that the cancel button is properly working as
     * intended. The cancel button should close the current screen that is opened.
     */
    @Test
    @Tag("ui")
    void cancelButtonDismissesPanel() {
        // Create and set mock database.
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

        // Navigate to Add Customer screen.
        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();

        // Click the Cancel Button.
        window.button("cancelButton").click();

        window.cleanUp();
    }
}


